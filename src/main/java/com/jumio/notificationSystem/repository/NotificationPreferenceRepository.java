package com.jumio.notificationSystem.repository;

import com.jumio.notificationSystem.entity.NotificationPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationPreferenceRepository extends JpaRepository<NotificationPreference, Long> {

}
