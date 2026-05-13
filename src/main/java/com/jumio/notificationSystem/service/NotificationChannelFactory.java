package com.jumio.notificationSystem.service;

import com.jumio.notificationSystem.enums.ChannelType;
import org.springframework.stereotype.Component;

@Component
public class NotificationChannelFactory {

    private final EmailChannel emailChannel;
    private final SMSChannel smsChannel;
    private final PushChannel pushChannel;

    public NotificationChannelFactory(EmailChannel emailChannel, SMSChannel smsChannel, PushChannel pushChannel) {
        this.emailChannel = emailChannel;
        this.smsChannel = smsChannel;
        this.pushChannel = pushChannel;
    }

    public NotificationChannel getChannel(ChannelType channelType) {
        return switch (channelType) {
            case EMAIL -> new EmailChannel();
            case SMS -> new SMSChannel();
            case PUSH -> new PushChannel();
            default -> throw new IllegalArgumentException("Invalid channel type");
        };
    }

}
