package com.ditra.Transformer.Services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.ditra.Transformer.Model.DataSet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

@Service
public class TransformService {
	
	
	public String toMongo(String body) {
		
		
		//Checking what we are receiving
		System.out.println("This is what the transformer received");
		System.out.println(body);
		
		
		//We are, indeed, receiving the correct thing!
		ObjectMapper objectMapper = new ObjectMapper();
		String output = "";
		ObjectReader objectReader = objectMapper.reader(DataSet.class);
		try {
			DataSet data = objectReader.readValue(body);
			Map<String, ArrayList<String>> map = data.getData();
			
			System.out.println(map.keySet());
			
			
			ArrayList<String> columns = new ArrayList<String>();
			ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
			for(Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
				columns.add(entry.getKey());
				values.add(entry.getValue());
			}
			
			/*
			for (int i = 0; i < data.getData().size(); i++) {
				output+="{ \n";
				for (int j = 0; j < columns.size(); j++) {
					
				output+="\""+columns.get(j)+"\": \""+values.get(i).get(j)+"\",\n";
				}
				output+="}\n";
			} */
			
			ArrayList<Document> documentList = new ArrayList<>();
			int num_docs = values.get(0).size();
			int num_cols = columns.size();
			
			for (int i = 0; i < num_docs; i++)
			{
				Document d = new Document();
				//we iterate through every row
				for (int j = 0; j < num_cols; j++)
				{
					//now we need to iterate through every column, and create a document with that data
					d.append(columns.get(j), values.get(j).get(i));
				}
				
				documentList.add(d);
			}
			
			//We are writing our list of documents as json
			final ObjectMapper mapper = new ObjectMapper();
			output = mapper.writeValueAsString(documentList);
			
			//sending that output to some mongo service
			System.out.println(output);
			return output;
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			System.out.println("NOT MAPPED CORRECTLY");
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			System.out.println("JSON PROCESSING ERROR");
			e.printStackTrace();
		}
		if(output.compareTo("")==0) System.out.println("NO BODY"); //nobody is guilty for this
		return "LULZ";
	}
	
	public String toArango(String body) {
		//Checking what we are receiving
				System.out.println("This is what the transformer received");
				System.out.println(body);
				
				
				//We are, indeed, receiving the correct thing!
				ObjectMapper objectMapper = new ObjectMapper();
				String output = "";
				ObjectReader objectReader = objectMapper.reader(DataSet.class);
				try {
					DataSet data = objectReader.readValue(body);
					Map<String, ArrayList<String>> map = data.getData();
					
					System.out.println(map.keySet());
					
					
					ArrayList<String> columns = new ArrayList<String>();
					ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
					for(Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
						columns.add(entry.getKey());
						values.add(entry.getValue());
					}
					
					/*
					for (int i = 0; i < data.getData().size(); i++) {
						output+="{ \n";
						for (int j = 0; j < columns.size(); j++) {
							
						output+="\""+columns.get(j)+"\": \""+values.get(i).get(j)+"\",\n";
						}
						output+="}\n";
					} */
					
					ArrayList<Document> documentList = new ArrayList<>();
					int num_docs = values.get(0).size();
					int num_cols = columns.size();
					
					for (int i = 0; i < num_docs; i++)
					{
						Document d = new Document();
						//we iterate through every row
						if(data.isVertex())
							for (int j = 0; j < num_cols; j++)
							{
								//now we need to iterate through every column, and create a document with that data
								d.append(columns.get(j), values.get(j).get(i));
							}
						
						else if (!data.isVertex())
							for (int j = 0; j < num_cols; j++)
							{
								String attr = "";
								
								
								if (j == 0) attr = "_from";
								else if (j == 1) attr = "_to";
								else return "Pogresni podaci. Vise od dve kolone pri kreaciji grane grafa!";
								
								d.append(attr, values.get(j).get(i));
							}
						
						documentList.add(d);
					}
					
					//We are writing our list of documents as json
					final ObjectMapper mapper = new ObjectMapper();
					output = mapper.writeValueAsString(documentList);
					
					//sending that output to some mongo service
					System.out.println(output);
					return output;
					
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					System.out.println("NOT MAPPED CORRECTLY");
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					System.out.println("JSON PROCESSING ERROR");
					e.printStackTrace();
				}
				if(output.compareTo("")==0) System.out.println("NO BODY"); //nobody is guilty for this
				return "LULZ";
	}
	
	
	
	public String toArangoComplex(String body) {
		//Checking what we are receiving
				System.out.println("This is what the transformer received");
				System.out.println(body);
				
				
				//We are, indeed, receiving the correct thing!
				ObjectMapper objectMapper = new ObjectMapper();
				String output = "";
				ObjectReader objectReader = objectMapper.reader(DataSet.class);
				try {
					DataSet data = objectReader.readValue(body);
					Map<String, ArrayList<String>> map = data.getData();
					
					System.out.println(map.keySet());
					
					
					ArrayList<String> columns = new ArrayList<String>();
					ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
					for(Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
						columns.add(entry.getKey());
						values.add(entry.getValue());
					}
					
					/*
					for (int i = 0; i < data.getData().size(); i++) {
						output+="{ \n";
						for (int j = 0; j < columns.size(); j++) {
							
						output+="\""+columns.get(j)+"\": \""+values.get(i).get(j)+"\",\n";
						}
						output+="}\n";
					} */
					
					ArrayList<Document> documentList = new ArrayList<>();
					int num_docs = values.get(0).size();
					int num_cols = columns.size();
					
					for (int i = 0; i < num_docs; i++)
					{
						Document d = new Document();
						//we iterate through every row
						for (int j = 0; j < num_cols; j++)
						{
							String attr = "";
							
							
							if (j == 0) attr = "_from";
							else if (j == 1) attr = "_to";
							else return "Pogresni podaci. Vise od dve kolone pri kreaciji grane grafa!";
							
							d.append(attr, values.get(j).get(i));
						}
						
						documentList.add(d);
					}
					
					//We are writing our list of documents as json
					final ObjectMapper mapper = new ObjectMapper();
					output = mapper.writeValueAsString(documentList);
					
					//sending that output to some mongo service
					System.out.println(output);
					return output;
					
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					System.out.println("NOT MAPPED CORRECTLY");
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					System.out.println("JSON PROCESSING ERROR");
					e.printStackTrace();
				}
				if(output.compareTo("")==0) System.out.println("NO BODY"); //nobody is guilty for this
				return "LULZ";
	}

}
