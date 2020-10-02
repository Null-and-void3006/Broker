package com.Tim401_6.Broker.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    private int endpointId;
    private LocalDateTime accessTime;
    private boolean success;

    public Logs(){}

    public Logs(int userId, int endpointId, boolean success) {
        this.userId = userId;
        this.endpointId = endpointId;
        this.accessTime = LocalDateTime.now();
        this.success = success;
    }

    public Logs(int userId, int endpointId, LocalDateTime accessTime, boolean success) {
        this.userId = userId;
        this.endpointId = endpointId;
        this.accessTime = accessTime;
        this.success = success;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(int endpointId) {
        this.endpointId = endpointId;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime access_time) {
        this.accessTime = access_time;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
