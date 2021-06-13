package com.zavrsnirad.CodeFlow.repository;

import com.zavrsnirad.CodeFlow.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
