package com.jumio.notificationSystem.service;

import com.jumio.notificationSystem.entity.Notification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PushChannel implements NotificationChannel {

    @Override
    public void send(Notification notification) {
        log.info("Sending Push Notification with id : {} ",  notification.getNotificationId());
    }
}
