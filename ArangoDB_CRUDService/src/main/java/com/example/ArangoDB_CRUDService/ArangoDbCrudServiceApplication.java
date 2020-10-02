package com.example.ArangoDB_CRUDService;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.EdgeDefinition;
import com.arangodb.model.GraphCreateOptions;
import com.example.ArangoDB_CRUDService.model.Database;




@SpringBootApplication
public class ArangoDbCrudServiceApplication {

	public static void main(String[] args) {
		
			
		
		System.out.println("\n\nConnecting to our default SQL database...");
		Database.establishDefaultConnection();
		System.out.println("Connection established!");
		
		System.out.println("Connecting to ArangoDB service...\n\n");
		SpringApplication.run(ArangoDbCrudServiceApplication.class, args);
		System.out.println("\n\nConnection established, proceeed to Postman or DiTra.");
	}

}
