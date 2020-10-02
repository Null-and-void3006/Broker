package com.Tim401_6.Broker.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String username;
    private String password;
    private String hostname;
    private String type;
    private int typeId;

    @OneToMany(
            mappedBy = "userId",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Set<Service> service;

    public User() {}

    public User(int id, String name, String username, String password, String type, int typeId) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.type = type;
        this.typeId = typeId;
    }

    public User(int id, String name, String username, String password, String hostname, String type, int typeId) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.hostname = hostname;
        this.type = type;
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
