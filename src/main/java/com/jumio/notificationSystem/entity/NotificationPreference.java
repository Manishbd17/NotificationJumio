package com.jumio.notificationSystem.entity;

import com.jumio.notificationSystem.enums.ChannelType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notification_preferences")
@Getter
@Setter
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
