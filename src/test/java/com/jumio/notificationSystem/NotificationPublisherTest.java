package com.jumio.notificationSystem;

import com.jumio.notificationSystem.configs.RabbitMQConfig;
import com.jumio.notificationSystem.enums.NotificationPriority;
import com.jumio.notificationSystem.queue.NotificationPublisher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationPublisherTest {

    @Test
    void shouldReturnHighPriorityQueue() {

        NotificationPublisher publisher = new NotificationPublisher(null);

        String queue = publisher.getQueueName(NotificationPriority.HIGH);

        assertEquals(RabbitMQConfig.HIGH_PRIORITY_QUEUE, queue);
    }

    @Test
    void shouldReturnMediumPriorityQueue() {

        NotificationPublisher publisher = new NotificationPublisher(null);

        String queue = publisher.getQueueName(NotificationPriority.MEDIUM);

        assertEquals(RabbitMQConfig.MEDIUM_PRIORITY_QUEUE, queue);
    }

    @Test
    void shouldReturnLowPriorityQueue() {

        NotificationPublisher publisher = new NotificationPublisher(null);

        String queue = publisher.getQueueName(NotificationPriority.LOW);

        assertEquals(RabbitMQConfig.LOW_PRIORITY_QUEUE, queue);
    }

}
