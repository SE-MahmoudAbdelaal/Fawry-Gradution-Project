package com.e_commerce.notification_api.repo;

import com.e_commerce.notification_api.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByStatus(String status);

}
