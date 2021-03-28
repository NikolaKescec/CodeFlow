package com.zavrsnirad.CodeFlow.dto.mappers;

import com.zavrsnirad.CodeFlow.domain.Notification;
import com.zavrsnirad.CodeFlow.dto.json.NotificationDtoJson;

public class MapperNotification {

    public static NotificationDtoJson NotificatioToJson(Notification notification) {
        return new NotificationDtoJson(
                notification.getNotificationId(),
                notification.getNotificationText(),
                notification.getType(),
                notification.getNotified().getUsername(),
                notification.getNotificator().getUsername()
        );
    }
}
