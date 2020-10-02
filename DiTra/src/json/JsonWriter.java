package json;

import java.io.FileWriter;


import javax.swing.JTextArea;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JsonWriter {
	
	private static JsonWriter instance = null;
	private static JSONObject jsonObject;

	private JsonWriter( JSONObject jsonObject) {
		this.setJsonObject(jsonObject);
	}

	public static JSONObject getJsonObject() {
		return jsonObject;
	}

	public static void setJsonObject(JSONObject jsonObject) {
		JsonWriter.jsonObject = jsonObject;
	}

	public static void writeToTextArea(JTextArea textArea, JSONObject jo)
	{
		String prettyJson = toPrettyFormat(jo);
		textArea.setText(prettyJson);
	}
	
	
	public static JsonWriter getInstance(JSONObject jsonObject) {
		if (instance == null) {
			instance = new JsonWriter(jsonObject);
		}
		return instance;
	}
	

	
	public static String toPrettyFormat(JSONObject jsonObject) {
	   
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    String prettyJson = gson.toJson(jsonObject);

	    return prettyJson;
	}
}
