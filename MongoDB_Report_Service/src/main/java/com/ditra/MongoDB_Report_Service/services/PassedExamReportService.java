package com.ditra.MongoDB_Report_Service.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.ditra.MongoDB_Report_Service.model.MongoDataSet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

@Service
public class PassedExamReportService {
	
	private HashMap<String, MongoDataSet> reportList = new HashMap<String, MongoDataSet>();
	private FindIterable<Document> dataSetLeft;
	private FindIterable<Document> dataSetRight;
	private Integer counter = 0;
	private MongoDataSet data;
	
	public String generateReport(String service, String student) {
		/* CONTACT BROKER AT /{SERVICE}/REPORTPASSED */
		String connection = "mongodb://tim_401_6_mongo_si2019:sdnteMxe@64.225.110.65:27017/test?authSource=tim_401_6_mongo_si2019";
		System.out.println("Creating mongo client");
		try {
			
		
		MongoClient client = MongoClients.create(connection); 
		data = new MongoDataSet();
		
		System.out.println("Extracting datbase");
		MongoDatabase db = client.getDatabase("tim_401_6_mongo_si2019");
		MongoCollection<org.bson.Document> students = db.getCollection("STUDENT_STUDY_DATA");
		MongoCollection<Document> exams = db.getCollection("PASSED_EXAMS");
		
		
		dataSetLeft = students.find(Filters.eq("PG_IDENT",student));
		for(Document doc : dataSetLeft) {
			dataSetRight = exams.find(Filters.and(Filters.eq("PG_IDENT",student), Filters.gt("PASS_POINTS",50)));
			for(Document ex : dataSetRight) {
				ArrayList<Document> docs = new ArrayList<Document>();
				docs.add(doc);
				docs.add(ex);
				data.getLinkedDocuments().put(counter, docs);
				docs.clear();
				counter++;
			}
		}
		reportList.put(student, data);
		
		return "SUCCESS";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "FAILED";
		}
	}
	
	public String printReport(String service, String student) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		String result;
		try {
			result = objectMapper.writeValueAsString(reportList.get(student));
			return result;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR, NO ENTRY IN REPORT LIST";
		}
		
		
	}

}
