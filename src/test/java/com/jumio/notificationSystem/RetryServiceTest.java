package com.jumio.notificationSystem;

import com.jumio.notificationSystem.entity.Notification;
import com.jumio.notificationSystem.enums.NotificationStatus;
import com.jumio.notificationSystem.repository.NotificationRepository;
import com.jumio.notificationSystem.service.RetryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RetryServiceTest {

    @Mock
    private NotificationRepository repository;

    private RetryService retryService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        retryService = new RetryService(repository);
    }

    @Test
    void shouldMarkNotificationAsRetrying() {

        Notification notification = new Notification();
        notification.setRetryCount(0);

        retryService.handleRetry(
                notification,
                new RuntimeException("Network failure")
        );

        assertEquals(NotificationStatus.RETRYING,
                notification.getStatus());

        assertEquals(1,
                notification.getRetryCount());
    }

    @Test
    void shouldMarkNotificationAsFailedAfterMaxRetries() {

        Notification notification = new Notification();
        notification.setRetryCount(2);

        retryService.handleRetry(
                notification,
                new RuntimeException("Permanent failure")
        );

        assertEquals(NotificationStatus.FAILED,
                notification.getStatus());
    }

}
