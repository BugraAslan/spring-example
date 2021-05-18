package com.example.springrabbitmq.model;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable {

    private int id;
    private Date createdAt;
    private NotificationMessage message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public NotificationMessage getMessage() {
        return message;
    }

    public void setMessage(NotificationMessage message) {
        this.message = message;
    }
}
