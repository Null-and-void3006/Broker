package com.Tim401_6.Broker.services;

import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransformService {
	
	private RestTemplateBuilder rtb = new RestTemplateBuilder();
	private final RestTemplate restTemplate = rtb.build();
	private String response = "";
	private String url = "http://localhost:9000/transform/";
	
	private final String url1 = "http://localhost:9000/transform/mongodb";
	private final String url2 = "http://localhost:9000/transform/arangodb";
	private final String url3 = "http://localhost:9000/transform/arangodbedge";

	public String routePutWithPayload(String payload, String type) {
		
		
		String url = "http://localhost:9000/transform/";
		
		type = type.toUpperCase(); 
		if(type.compareTo("MONGODB")==0) url+="mongodb";
		else if(type.compareTo("ARANGODB")==0) url+="arangodb";
		else if(type.compareTo("ARANGODBEDGE")==0)url+="arangodbedge";
		else System.out.println("ERROR, NO DB TRANSFORMER FOR THAT TYPE");
		
		System.out.println("OVo je moj url:"+ url);
		
		
		
		String postResponse = "";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(payload, headers);
		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
		postResponse = response.getBody();
		//postResponse = validateResponseForUser(response.getBody());
		
		return postResponse;
	}

	public String routeWithPayload(Map<String, Object> payload, String type) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
