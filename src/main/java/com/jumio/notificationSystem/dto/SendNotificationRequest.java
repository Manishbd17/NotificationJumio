package com.jumio.notificationSystem.dto;

import com.jumio.notificationSystem.enums.ChannelType;
import com.jumio.notificationSystem.enums.NotificationPriority;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SendNotificationRequest {

    @NotNull
    private Long userId;

    @NotNull
    private ChannelType channelType;

    @NotNull
    private NotificationPriority notificationPriority;

    private String title;

    @NotNull
    private String content;

    private LocalDateTime localDateTime;
    private LocalDateTime scheduledTime;

}
