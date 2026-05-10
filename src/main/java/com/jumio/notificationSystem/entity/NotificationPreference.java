package com.jumio.notificationSystem.entity;

import com.jumio.notificationSystem.enums.ChannelType;
import jakarta.persistence.*;

@Entity
@Table(name = "notification_preferences")
public class NotificationPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationPreferenceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private ChannelType channelType;
    private Boolean enabled;


}
