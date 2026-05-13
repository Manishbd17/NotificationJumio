package com.jumio.notificationSystem.service;

import com.jumio.notificationSystem.entity.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailChannel implements NotificationChannel{

    @Override
    public void send(Notification notification) {
        log.info("Sending Email Notification with id : {} ",  notification.getNotificationId());
    }
}
