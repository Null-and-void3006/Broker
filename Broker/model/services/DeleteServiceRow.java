package com.Tim401_6.Broker.model.services;

public class DeleteServiceRow {

	private int userId;
	private String userName;
	private String serviceName;


	public DeleteServiceRow(int userId, String userName, String serviceName) {
		this.userId = userId;
		this.userName = userName;
		this.serviceName = serviceName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
