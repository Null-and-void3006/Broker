package com.Tim401_6.Broker.model;

import javax.persistence.*;

@Entity
@Table(name = "endpoint_complex_order")
public class EndpointComplexOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int parentEndpointId;
    private int currentEndpointId;
    private int endOrder;
    private String paramValue;
    private String paramName;

    public EndpointComplexOrder(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentEndpointId() {
        return parentEndpointId;
    }

    public void setParentEndpointId(int parentEndpointId) {
        this.parentEndpointId = parentEndpointId;
    }

    public int getCurrentEndpointId() {
        return currentEndpointId;
    }

    public void setCurrentEndpointId(int currentEndpointId) {
        this.currentEndpointId = currentEndpointId;
    }

    public int getEndOrder() {
        return endOrder;
    }

    public void setEndOrder(int endOrder) {
        this.endOrder = endOrder;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamName(){ return this.paramName; }

    public void setParamName(){ this.paramName = paramName; }
}
