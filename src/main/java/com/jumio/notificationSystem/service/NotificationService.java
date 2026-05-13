package com.jumio.notificationSystem.service;

import com.jumio.notificationSystem.dto.SendNotificationRequest;
import com.jumio.notificationSystem.entity.Notification;
import com.jumio.notificationSystem.entity.User;
import com.jumio.notificationSystem.enums.NotificationStatus;
import com.jumio.notificationSystem.exception.NotificationNotFoundException;
import com.jumio.notificationSystem.exception.UserNotFoundException;
import com.jumio.notificationSystem.queue.NotificationPublisher;
import com.jumio.notificationSystem.repository.NotificationRepository;
import com.jumio.notificationSystem.repository.UserRepository;
import com.jumio.notificationSystem.util.NotificationScheduler;
import com.jumio.notificationSystem.util.NotificationTemplateUtil;
import jakarta.validation.Valid;
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

        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found"));

        Notification notification =  new Notification();
        notification.setUserID(user.getUserId());
        notification.setTitle(request.getTitle());
        notification.setContent(request.getContent());
        notification.setChannelType(request.getChannelType());
        notification.setPriority(request.getNotificationPriority());
        notification.setRetryCount(0);

        String personalizedContent = NotificationTemplateUtil.personalizeContent(request.getContent(), user);
        notification.setContent(personalizedContent);

        if (request.getScheduledTime() != null && request.getScheduledTime().isAfter(LocalDateTime.now())) {
            notification.setStatus(NotificationStatus.PENDING);
            notification.setScheduledTime(request.getScheduledTime());
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
        return requests.stream().map(this::sendNotification).toList();
    }

    public Notification trackNotification(Long id) {
        return notificationRepository.findById(id).orElseThrow(() -> new NotificationNotFoundException("Notification not found"));
    }

    public Notification scheduleNotification(@Valid SendNotificationRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        Notification notification = new Notification();

        notification.setUserID(user.getUserId());
        notification.setTitle(request.getTitle());
        notification.setContent(request.getContent());
        notification.setChannelType(request.getChannelType());
        notification.setPriority(request.getNotificationPriority());
        notification.setStatus(NotificationStatus.PENDING);
        notification.setScheduledTime(request.getScheduledTime());
        notification.setRetryCount(0);

        return notificationRepository.save(notification);
    }
}
