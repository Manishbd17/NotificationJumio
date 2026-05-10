package com.jumio.notificationSystem.service;

import com.jumio.notificationSystem.configs.RabbitMQConfig;
import com.jumio.notificationSystem.entity.Notification;
import com.jumio.notificationSystem.enums.NotificationPriority;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publish(Notification notification) {

        String queueName = getQueueName(notification.getPriority());

        rabbitTemplate.convertAndSend(queueName, notification.getNotificationId());
    }

    public String getQueueName(NotificationPriority priority) {

        return switch (priority) {
            case HIGH -> RabbitMQConfig.HIGH_PRIORITY_QUEUE;
            case MEDIUM -> RabbitMQConfig.MEDIUM_PRIORITY_QUEUE;
            case LOW -> RabbitMQConfig.LOW_PRIORITY_QUEUE;
        };
    }
}
