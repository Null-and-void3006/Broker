package com.example.ArangoDB_CRUDService.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//maps column name to data
/** COL_NAME1  COL NAME2 ...
 *   data11      data12
 *   data21      data22
 *   data31      data32
 *   data41      data42
 *     .           .
 *     .           .
 *     .           .
 * @author Nikola Tasic
 *
 */
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
