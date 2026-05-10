package com.jumio.notificationSystem.service;

import com.jumio.notificationSystem.entity.Notification;

public interface NotificationChannel{
    void send(Notification notification);
}
