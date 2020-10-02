package com.ditra.MongoDB_Report_Service.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.client.FindIterable;

public class MongoDataSet {

	private Map<Integer,List<Document>> linkedDocuments;
	

	public MongoDataSet() {
		super();
		linkedDocuments = new HashMap<Integer, List<Document>>();
	}

	public MongoDataSet(Map<Integer, List<Document>> linkedDocuments) {
		super();
		linkedDocuments = new HashMap<Integer, List<Document>>();
		this.linkedDocuments = linkedDocuments;
	}

	public Map<Integer, List<Document>> getLinkedDocuments() {
		return linkedDocuments;
	}

	public void setLinkedDocuments(Map<Integer, List<Document>> linkedDocuments) {
		this.linkedDocuments = linkedDocuments;
	} 
	
	
	
}
