package com.example.ArangoDB_CRUDService.model;



import com.example.ArangoDB_CRUDService.enums.AttributeType;
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
