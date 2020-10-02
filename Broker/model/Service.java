package com.Tim401_6.Broker.model;

import javax.persistence.*;

@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String serviceName;
    private String path;
    private int userId;
    private boolean isAbstract;
    @Column(name = "service_id")
    private Integer serviceId;

    public Service(){}

    public Service(String serviceName, String path, int userId, boolean isAbstract, int serviceId) {
        this.serviceName = serviceName;
        this.path = path;
        this.userId = userId;
        this.isAbstract = isAbstract;
        this.serviceId = serviceId;
    }

    public Service(String serviceName, String path, int userId, boolean isAbstract) {
        this.serviceName = serviceName;
        this.path = path;
        this.userId = userId;
        this.isAbstract = isAbstract;
    }

    public Service(String serviceName, String path, int userId, boolean isAbstract, Integer serviceId) {
        this.serviceName = serviceName;
        this.path = path;
        this.userId = userId;
        this.isAbstract = isAbstract;
        this.serviceId = serviceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isAbstract(){ return this.isAbstract; }

    public void setAbstract(boolean isAbstract){ this.isAbstract = isAbstract; }
}
