package com.example.ArangoDB_CRUDService.model;

import java.util.ArrayList;

public class ArangoDBCEdge {
	
	private String name;
	private ArrayList<ArangoDBField> fields;
	private ArangoDBVertex _from;
	private ArangoDBVertex _to;
	
	
	public ArangoDBCEdge(String name, ArrayList<ArangoDBField> fields, ArangoDBVertex _from, ArangoDBVertex _to) {
		super();
		this.name = name;
		this.fields = fields;
		this._from = _from;
		this._to = _to;
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
	public ArangoDBVertex get_from() {
		return _from;
	}
	public void set_from(ArangoDBVertex _from) {
		this._from = _from;
	}
	public ArangoDBVertex get_to() {
		return _to;
	}
	public void set_to(ArangoDBVertex _to) {
		this._to = _to;
	}

}
