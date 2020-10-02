package com.example.ArangoDB_CRUDService.model;

import java.util.ArrayList;

public class ArangoDBAddVertex {
	
	private String collectionName;
	private ArrayList<ArangoDBField> fields;
	
	public ArangoDBAddVertex(String name, ArrayList<ArangoDBField> fields) {
		super();
		this.collectionName = collectionName;
		this.fields = fields;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String name) {
		this.collectionName = name;
	}
	public ArrayList<ArangoDBField> getFields() {
		return fields;
	}
	public void setFields(ArrayList<ArangoDBField> fields) {
		this.fields = fields;
	}

}
