package com.ditra.Transformer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransformerApplication {

	public static void main(String[] args) {
		System.out.println("Starting transformer service...");
		SpringApplication.run(TransformerApplication.class, args);
		System.out.println("Transformer started! Proceed...");
	}

}
