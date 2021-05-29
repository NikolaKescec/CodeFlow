package com.zavrsnirad.CodeFlow.dto.json;

public class NotificationDtoJson {


    private Long notificationId;

    private String notificationText;

    private String type;

    private String notifiedUsername;

    private String notificatorUsername;

    public NotificationDtoJson(Long notificationId, String notificationText, String type, String notifiedUsername, String notificatorUsername) {
        this.notificationId = notificationId;
        this.notificationText = notificationText;
        this.type = type;
        this.notifiedUsername = notifiedUsername;
        this.notificatorUsername = notificatorUsername;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotifiedUsername() {
        return notifiedUsername;
    }

    public void setNotifiedUsername(String notifiedUsername) {
        this.notifiedUsername = notifiedUsername;
    }

    public String getNotificatorUsername() {
        return notificatorUsername;
    }

    public void setNotificatorUsername(String notificatorUsername) {
        this.notificatorUsername = notificatorUsername;
    }
}
