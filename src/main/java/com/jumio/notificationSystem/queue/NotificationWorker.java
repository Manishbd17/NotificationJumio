package com.jumio.notificationSystem.queue;

import com.jumio.notificationSystem.entity.Notification;
import com.jumio.notificationSystem.enums.NotificationStatus;
import com.jumio.notificationSystem.repository.NotificationRepository;
import com.jumio.notificationSystem.service.NotificationChannel;
import com.jumio.notificationSystem.service.NotificationChannelFactory;
import com.jumio.notificationSystem.service.RetryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class NotificationWorker {

    private final NotificationRepository notificationRepository;
    private final NotificationChannelFactory channelFactory;
    private final RetryService retryService;

    @RabbitListener(queues = {
            "high.priority.queue",
            "medium.priority.queue",
            "low.priority.queue"
    })
    public void processNotification(Long notificationId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));


        try {

            NotificationChannel channel =
                    channelFactory.getChannel(notification.getChannelType());

            channel.send(notification);

            notification.setStatus(NotificationStatus.SENT);
            notification.setSentAt(LocalDateTime.now());

            notificationRepository.save(notification);

            log.info("Notification sent successfully: {}", notificationId);

        } catch (Exception exception) {

            log.error("Notification sending failed: {}", notificationId);

            retryService.handleRetry(notification, exception);
        }
    }

}
