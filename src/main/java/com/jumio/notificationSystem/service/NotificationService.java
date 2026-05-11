package com.jumio.notificationSystem.service;

import com.jumio.notificationSystem.dto.SendNotificationRequest;
import com.jumio.notificationSystem.entity.Notification;
import com.jumio.notificationSystem.entity.User;
import com.jumio.notificationSystem.enums.NotificationStatus;
import com.jumio.notificationSystem.queue.NotificationPublisher;
import com.jumio.notificationSystem.repository.NotificationRepository;
import com.jumio.notificationSystem.repository.UserRepository;
import com.jumio.notificationSystem.util.NotificationTemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final NotificationPublisher notificationPublisher;

    public Notification sendNotification(SendNotificationRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Notification notification =  new Notification();

        notification.setUser(user);
        notification.setTitle(request.getTitle());
        notification.setContent(request.getContent());
        notification.setChannelType(request.getChannelType());
        notification.setPriority(request.getNotificationPriority());
        notification.setRetryCount(0);

        String personalizedContent =
                NotificationTemplateUtil.personalizeContent(
                        request.getContent(),
                        user
                );

        notification.setContent(personalizedContent);

        if (request.getScheduledDateTime() != null &&
                request.getScheduledDateTime().isAfter(LocalDateTime.now())) {

            notification.setStatus(NotificationStatus.PENDING);
            notification.setScheduledTime(request.getScheduledDateTime());

        } else {
            notification.setStatus(NotificationStatus.QUEUED);
        }

        Notification savedNotification = notificationRepository.save(notification);

        if (savedNotification.getStatus() == NotificationStatus.QUEUED) {
            notificationPublisher.publish(savedNotification);
        }

        return savedNotification;
    }

    public List<Notification> sendBulkNotifications(List<SendNotificationRequest> requests) {

        return requests.stream()
                .map(this::sendNotification)
                .toList();
    }

    public Notification getNotification(Long id) {

        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }
}
