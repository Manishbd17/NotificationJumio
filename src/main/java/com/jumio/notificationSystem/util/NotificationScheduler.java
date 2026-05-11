package com.jumio.notificationSystem.util;


import com.jumio.notificationSystem.entity.Notification;
import com.jumio.notificationSystem.enums.NotificationStatus;
import com.jumio.notificationSystem.queue.NotificationPublisher;
import com.jumio.notificationSystem.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class NotificationScheduler {

    private final NotificationRepository notificationRepository;
    private final NotificationPublisher notificationPublisher;

    @Scheduled(fixedRate = 60000)
    public void processScheduledNotifications() {

        List<Notification> notifications =
                notificationRepository.findByStatusAndScheduledTimeBefore(
                        NotificationStatus.PENDING,
                        LocalDateTime.now()
                );

        for (Notification notification : notifications) {

            notification.setStatus(NotificationStatus.QUEUED);

            notificationRepository.save(notification);

            notificationPublisher.publish(notification);

            log.info("Scheduled notification queued: {}", notification.getNotificationId());
        }
    }

    @Scheduled(fixedRate = 60000)
    public void processRetries() {

        List<Notification> notifications =
                notificationRepository.findByStatusAndNextRetryTimeBefore(
                        NotificationStatus.RETRYING,
                        LocalDateTime.now()
                );

        for (Notification notification : notifications) {

            notification.setStatus(NotificationStatus.QUEUED);

            notificationRepository.save(notification);

            notificationPublisher.publish(notification);

            log.info("Retry notification queued: {}", notification.getNotificationId());
        }
    }
}
