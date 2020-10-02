package com.example.ArangoDB_CRUDService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;

public class Relation implements Serializable {
    // fromEntity = OD koga se pokazuje - relacija se nalazi u ovom entitiju
    // toEntity = KA kome se pokazuje - nalazi se u samoj relaciji
    // fromAttribute = atribut iz fromEntity-ja koji povezuje
    // toAttribute = atribut iz toEntity-ja koji povezuje

    private String name;
    private Entity fromEntity;
    private Entity toEntity;
    private Attribut fromAttribute;
    private Attribut toAttribute;

    public Relation(String name, Entity fromEntity, Entity toEntity,
                    Attribut fromAttribute, Attribut toAttribute) {
        this.name = name;
        this.fromEntity = fromEntity;
        this.toEntity = toEntity;
        this.fromAttribute = fromAttribute;
        this.toAttribute = toAttribute;
    }

    @JsonBackReference
    public Entity getToEntity() {
        return toEntity;
    }

    public Attribut getFromAttribute() {
        return fromAttribute;
    }

    public Attribut getToAttribute() {
        return toAttribute;
    }

    public String getName() {
        return name;
    }

    @JsonBackReference
    public Entity getFromEntity() {
        return fromEntity;
    }

    public void setFromEntity(Entity fromEntity) {
        this.fromEntity = fromEntity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToEntity(Entity toEntity) {
        this.toEntity = toEntity;
    }

    public void setFromAttribute(Attribut fromAttribute) {
        this.fromAttribute = fromAttribute;
    }

    public void setToAttribute(Attribut toAttribute) {
        this.toAttribute = toAttribute;
    }

    @Override
    public String toString() {
        return "Relation [name=" + name + ", fromEntity=" + fromEntity + ", toEntity=" + toEntity + ", fromAttribute="
                + fromAttribute + ", toAttribute=" + toAttribute + "]";
    }
}
