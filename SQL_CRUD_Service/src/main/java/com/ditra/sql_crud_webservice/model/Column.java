package com.ditra.sql_crud_webservice.model;

import com.ditra.sql_crud_webservice.enums.AttributeType;

import java.io.Serializable;

public class Column implements Serializable {
    private String name;
    private String value;
    private AttributeType type;

    public Column(String name, String value, AttributeType type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AttributeType getType() {
        return type;
    }

    public void setType(AttributeType type) {
        this.type = type;
    }

    public String toString() {
        return name + " - " + value;
    }
}
