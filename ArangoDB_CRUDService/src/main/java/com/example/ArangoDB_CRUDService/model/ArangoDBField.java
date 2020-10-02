package com.example.ArangoDB_CRUDService.model;

import com.example.ArangoDB_CRUDService.enums.ArangoDBAttributeType;

public class ArangoDBField {
	
	private String name;
	private String value;
	private ArangoDBAttributeType type;
	
	
	public ArangoDBField(String name, String value, ArangoDBAttributeType type) {
		super();
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


	public ArangoDBAttributeType getType() {
		return type;
	}


	public void setType(ArangoDBAttributeType type) {
		this.type = type;
	}

}
