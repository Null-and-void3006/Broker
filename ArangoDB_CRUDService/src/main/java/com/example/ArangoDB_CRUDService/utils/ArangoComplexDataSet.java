package com.example.ArangoDB_CRUDService.utils;

import java.util.ArrayList;
import java.util.HashMap;

public class ArangoComplexDataSet {
	
	private HashMap<String, ArrayList<String>> subjectGeneralCompetency; //predmeti i generalne kompetencije
	private HashMap<String, ArrayList<String>> subjectSpecificCompetency; //predmeti i konkretne kompetencije
	public ArangoComplexDataSet(HashMap<String, ArrayList<String>> subjectGeneralCompetency,
			HashMap<String, ArrayList<String>> subjectSpecificCompetency) {
		super();
		this.subjectGeneralCompetency = subjectGeneralCompetency;
		this.subjectSpecificCompetency = subjectSpecificCompetency;
	}
	
	public ArangoComplexDataSet()
	{
		this.subjectGeneralCompetency = new HashMap<>();
		this.subjectSpecificCompetency = new HashMap<>();
	}
	public HashMap<String, ArrayList<String>> getSubjectGeneralCompetency() {
		return subjectGeneralCompetency;
	}
	public void setSubjectGeneralCompetency(HashMap<String, ArrayList<String>> subjectGeneralCompetency) {
		this.subjectGeneralCompetency = subjectGeneralCompetency;
	}
	public HashMap<String, ArrayList<String>> getSubjectSpecificCompetency() {
		return subjectSpecificCompetency;
	}
	public void setSubjectSpecificCompetency(HashMap<String, ArrayList<String>> subjectSpecificCompetency) {
		this.subjectSpecificCompetency = subjectSpecificCompetency;
	}

}
