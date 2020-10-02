package com.Tim401_6.Broker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "method_type")
public class MethodType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;

	public MethodType() {}

	public MethodType(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
