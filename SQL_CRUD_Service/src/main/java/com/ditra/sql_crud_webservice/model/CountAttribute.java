package com.ditra.sql_crud_webservice.model;

import com.ditra.sql_crud_webservice.enums.AttributeType;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class CountAttribute extends Attribut {

    private int count = 0;

    public CountAttribute() {
        super("Count", 50, AttributeType.CountAttribute, false, true, 1);
        this.count=count;
    }

    public void incConut() {
        count++;
    }

    @JsonBackReference
    public int getCount() {
        return count;
    }
}
