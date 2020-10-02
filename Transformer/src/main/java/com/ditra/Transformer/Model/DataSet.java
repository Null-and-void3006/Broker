package com.ditra.Transformer.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataSet {
	
	boolean isVertex;
	private HashMap<String, ArrayList<String>> data;
	
	public DataSet() {
		// TODO Auto-generated constructor stub
	}

	public HashMap<String, ArrayList<String>> getData() {
		return data;
	}

	public void setData(HashMap<String, ArrayList<String>> data) {
		this.data = data;
	}

	public boolean isVertex() {
		return isVertex;
	}

	public void setVertex(boolean isVertex) {
		this.isVertex = isVertex;
	}
	
	

}
