package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.Notification;

public interface NotificationService {

    Notification findById(Long notificationId);

    void removeNotification(Long notificationId);

    Notification saveNotification(Notification notification);

}
