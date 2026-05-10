package com.jumio.notificationSystem.repository;

import com.jumio.notificationSystem.entity.Notification;
import com.jumio.notificationSystem.enums.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByStatusAndScheduledTimeBefore(
            NotificationStatus status,
            LocalDateTime time
    );

    List<Notification> findByStatusAndNextRetryTimeBefore(
            NotificationStatus status,
            LocalDateTime time
    );

}
