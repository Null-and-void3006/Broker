package com.Tim401_6.Broker.model;

import javax.persistence.*;

@Entity
@Table(name = "authorization_group")
public class AuthorizationGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int userId;
	private int endpointId;
	 
	public AuthorizationGroup() {}

	public AuthorizationGroup(int userId, int endpointId) {
		this.userId = userId;
		this.endpointId = endpointId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getEndpointId() {
		return endpointId;
	}

	public void setEndpointId(int endpointId) {
		this.endpointId = endpointId;
	}
}
