package com.jumio.notificationSystem.service;

import com.jumio.notificationSystem.entity.Notification;
import com.jumio.notificationSystem.enums.NotificationStatus;
import com.jumio.notificationSystem.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RetryService {

    private static final int MAX_RETRIES = 3;
    private static final int BASE_DELAY_MINUTES = 1;

    private final NotificationRepository notificationRepository;

    public void handleRetry(Notification notification, Exception exception) {

        int retryCount = Optional.ofNullable(notification.getRetryCount()).orElse(0);
        retryCount++;
        notification.setRetryCount(retryCount);
        notification.setFailureReason(exception.getMessage());

        if (retryCount >= MAX_RETRIES) {
            notification.setStatus(NotificationStatus.FAILED);
        } else {
            long delay = (long) (BASE_DELAY_MINUTES * Math.pow(2, retryCount));
            notification.setStatus(NotificationStatus.RETRYING);
            notification.setNextRetryTime(LocalDateTime.now().plusMinutes(delay));
        }
        notificationRepository.save(notification);
    }
}
