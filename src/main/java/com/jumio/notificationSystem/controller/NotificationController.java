package com.jumio.notificationSystem.controller;


import com.jumio.notificationSystem.dto.SendNotificationRequest;
import com.jumio.notificationSystem.entity.Notification;
import com.jumio.notificationSystem.service.NotificationService;
import com.jumio.notificationSystem.util.NotificationScheduler;
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
    public Notification sendNotification(@Valid @RequestBody SendNotificationRequest request) {
        return notificationService.sendNotification(request);
    }

    @PostMapping("/schedule")
    public Notification scheduleNotification(@Valid  @RequestBody SendNotificationRequest request) {
        return notificationService.scheduleNotification(request);
    }

    @PostMapping("/bulk")
    public List<Notification> sendBulkNotifications(@RequestBody List<SendNotificationRequest> requests) {
        return notificationService.sendBulkNotifications(requests);
    }

    @GetMapping("/id/{id}")
    public Notification trackNotification(@PathVariable Long id) {
        return notificationService.trackNotification(id);
    }

}
