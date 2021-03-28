package com.zavrsnirad.CodeFlow.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Notification extends TimeAndUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @Column(name = "notification_text",nullable = false)
    private String notificationText;

    @Column(nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "notified_id", referencedColumnName = "programmer_id")
    private Programmer notified;

    @ManyToOne
    @JoinColumn(name = "notificator_id", referencedColumnName = "programmer_id")
    private Programmer notificator;

    public Notification() {
    }

    public Notification(String notificationText, String type, Programmer notified, Programmer notificator) {
        this.notificationText = notificationText;
        this.type = type;
        this.notified = notified;
        this.notificator = notificator;
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

    public Programmer getNotified() {
        return notified;
    }

    public void setNotified(Programmer notified) {
        this.notified = notified;
    }

    public Programmer getNotificator() {
        return notificator;
    }

    public void setNotificator(Programmer notificator) {
        this.notificator = notificator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(notificationId, that.notificationId) && Objects.equals(notificationText, that.notificationText) && Objects.equals(type, that.type) && Objects.equals(notified, that.notified) && Objects.equals(notificator, that.notificator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationId, notificationText, type, notified, notificator);
    }
}
