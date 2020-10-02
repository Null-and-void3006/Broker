package com.example.ArangoDB_CRUDService.model;

import java.util.ArrayList;

public class ArangoDBVertex {
	
	private String name;
	private ArrayList<ArangoDBField> fields;
	
	
	public ArangoDBVertex(String name, ArrayList<ArangoDBField> fields) {
		super();
		this.name = name;
		this.fields = fields;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<ArangoDBField> getFields() {
		return fields;
	}
	public void setFields(ArrayList<ArangoDBField> fields) {
		this.fields = fields;
	}

}
