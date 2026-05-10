package com.jumio.notificationSystem.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String HIGH_PRIORITY_QUEUE = "high.priority.queue";
    public static final String MEDIUM_PRIORITY_QUEUE = "medium.priority.queue";
    public static final String LOW_PRIORITY_QUEUE = "low.priority.queue";

    @Bean
    public Queue highPriorityQueue() {
        return new Queue(HIGH_PRIORITY_QUEUE);
    }

    @Bean
    public Queue mediumPriorityQueue() {
        return new Queue(MEDIUM_PRIORITY_QUEUE);
    }

    @Bean
    public Queue lowPriorityQueue() {
        return new Queue(LOW_PRIORITY_QUEUE);
    }
}
