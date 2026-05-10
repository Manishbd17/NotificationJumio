package com.jumio.notificationSystem;

import com.jumio.notificationSystem.enums.ChannelType;
import com.jumio.notificationSystem.service.EmailChannel;
import com.jumio.notificationSystem.service.NotificationChannelFactory;
import com.jumio.notificationSystem.service.PushChannel;
import com.jumio.notificationSystem.service.SMSChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class NotificationChannelFactoryTest {

    private NotificationChannelFactory factory;

    @BeforeEach
    void setUp() {

        factory = new NotificationChannelFactory(
                new EmailChannel(),
                new SMSChannel(),
                new PushChannel()
        );
    }

    @Test
    void shouldReturnEmailChannel() {

        assertTrue(factory.getChannel(ChannelType.EMAIL)
                instanceof EmailChannel);
    }

    @Test
    void shouldReturnSmsChannel() {

        assertTrue(factory.getChannel(ChannelType.SMS)
                instanceof SMSChannel);
    }

    @Test
    void shouldReturnPushChannel() {

        assertTrue(factory.getChannel(ChannelType.PUSH)
                instanceof PushChannel);
    }
}
