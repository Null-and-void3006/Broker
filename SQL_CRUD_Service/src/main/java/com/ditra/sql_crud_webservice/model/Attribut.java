package com.ditra.sql_crud_webservice.model;

import com.ditra.sql_crud_webservice.enums.AttributeType;

import java.io.Serializable;

public class Attribut implements Serializable {
    private String name;
    private int length;
    private boolean primaryKey;
    private boolean mandatory;
    private AttributeType type;
    private int group;

    public Attribut(String name, int length, AttributeType type,
                    boolean primaryKey, boolean mandatory, int group) {
        this.name = name;
        this.length = length;
        this.type = type;
        this.primaryKey = primaryKey;
        this.mandatory = mandatory;
        this.group=group;
    }

    public Attribut(String name) {
        this.name = name;
    }

    public int getLenght() {
        return length;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public AttributeType getType() {
        return type;
    }

    public int getGroup() {
        return group;
    }

    public String toString(){
        return name;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public void setType(AttributeType type) {
        this.type = type;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }
}

