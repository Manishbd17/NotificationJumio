package com.jumio.notificationSystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "recurring_notifications")
public class RecurringNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recurringNotificationId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_id")
    private Notification notification;

    private String cron;

    private LocalDateTime nextExecutionTime;

}
