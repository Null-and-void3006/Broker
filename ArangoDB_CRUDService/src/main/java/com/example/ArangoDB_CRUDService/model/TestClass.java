package com.example.ArangoDB_CRUDService.model;

public class TestClass {

	private String name;
	private String surname;
	
	public TestClass(String name, String surname)
	{
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
