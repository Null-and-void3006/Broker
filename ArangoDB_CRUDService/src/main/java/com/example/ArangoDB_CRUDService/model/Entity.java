package com.example.ArangoDB_CRUDService.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.ArrayList;

public class Entity implements Serializable {
    private String name;

    protected ArrayList<Attribut> attributes;
    protected CountAttribute countAttribute;
    protected ArrayList<Relation> relations;

    public Entity(String name){
        this.name = name;

        setAttributes(new ArrayList<>());
        setRelations(new ArrayList<>());

        countAttribute = new CountAttribute();
    }

    public void addAttribute(Attribut atr){
        getAttributes().add(atr);
    }

    public void removeAttribute(Attribut atr){
        getAttributes().remove(atr);
    }

    public void addRelation(Relation rel){
        getRelations().add(rel);
    }

    public void removeRelation(Relation rel){
        getRelations().remove(rel);
    }

    @JsonManagedReference
    public ArrayList<Relation> getRelations() {
        return relations;
    }

    public void setRelations(ArrayList<Relation> relations) {
        this.relations = relations;
    }

    public ArrayList<Attribut> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attribut> atributes) {
        this.attributes = atributes;
    }

    @JsonManagedReference
    public CountAttribute getCountAttribute() {
        return countAttribute;
    }

    public void incCountAttribute() {
        countAttribute.incConut();
    }

    @Override
    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }
}
