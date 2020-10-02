package com.example.ArangoDB_CRUDService.model;

import java.util.ArrayList;

public class ArangoDBCompareSubjectsData {
	
	private int numSubjects;
	private ArrayList<String> subjects;
	
	
	public ArangoDBCompareSubjectsData(int numSubjects, ArrayList<String> subjects) {
		super();
		this.numSubjects = numSubjects;
		this.subjects = subjects;
	}
	
	public int getNumSubjects() {
		return numSubjects;
	}
	public void setNumSubjects(int numSubjects) {
		this.numSubjects = numSubjects;
	}
	public ArrayList<String> getSubjects() {
		return subjects;
	}
	public void setSubjects(ArrayList<String> subjects) {
		this.subjects = subjects;
	}

}
