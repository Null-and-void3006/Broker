package com.Tim401_6.Broker;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BrokerApplication {

	private static Connection conn;
	
	public static void main(String[] args) {
		System.out.println("Starting the broker application. Please wait...");
		SpringApplication.run(BrokerApplication.class, args);
		System.out.println("Application started successfully. Proceed to Postman.");

	}

}
