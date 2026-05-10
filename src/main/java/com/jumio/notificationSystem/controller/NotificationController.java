package com.jumio.notificationSystem.controller;


import com.jumio.notificationSystem.dto.SendNotificationRequest;
import com.jumio.notificationSystem.entity.Notification;
import com.jumio.notificationSystem.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send")
    public Notification sendNotification(
            @Valid @RequestBody SendNotificationRequest request
    ) {
        return notificationService.sendNotification(request);
    }

    @PostMapping("/bulk")
    public List<Notification> sendBulkNotifications(
            @RequestBody List<SendNotificationRequest> requests
    ) {
        return notificationService.sendBulkNotifications(requests);
    }

    @GetMapping("/{id}")
    public Notification getNotification(@PathVariable Long id) {
        return notificationService.getNotification(id);
    }

}
