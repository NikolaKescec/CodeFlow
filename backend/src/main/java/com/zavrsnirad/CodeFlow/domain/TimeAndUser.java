package com.zavrsnirad.CodeFlow.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
public class TimeAndUser {

    @Column(name = "ts_created")
    private Timestamp created;

    @Column(name = "ts_modified")
    private Timestamp modified;

    @Column(name = "user_created")
    private String userCreated;

    @Column(name = "user_modified")
    private String userModified;

    public TimeAndUser() {
        this.created = new Timestamp(new Date().getTime());
        this.modified = this.created;
    }

    public TimeAndUser(String userCreated) {
        this();
        this.userCreated = userCreated;
    }

    public void update(String userModified) {
        this.modified = new Timestamp(new Date().getTime());
        this.userModified = userModified;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public String getUserModified() {
        return userModified;
    }

    public void setUserModified(String userModified) {
        this.userModified = userModified;
    }

    public static void updateModified(TimeAndUser timeAndUser, Programmer programmer) {
        timeAndUser.setUserModified(programmer.getUsername());
        timeAndUser.setModified(new Timestamp(new Date().getTime()));
    }
}
