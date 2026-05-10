package com.jumio.notificationSystem.entity;

import com.jumio.notificationSystem.enums.ChannelType;
import com.jumio.notificationSystem.enums.NotificationPriority;
import com.jumio.notificationSystem.enums.NotificationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String title;

    private ChannelType channelType;
    private NotificationPriority priority;
    private NotificationStatus status;

    private LocalDateTime scheduledTime;
    private LocalDateTime sentAt;
    private Integer retryCount = 0 ;
    private LocalDateTime nextRetryTime;

    @Column(columnDefinition = "TEXT")
    private String failureReason;

}
