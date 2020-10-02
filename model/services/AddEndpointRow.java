package com.Tim401_6.Broker.model.services;

import java.util.ArrayList;

public class AddEndpointRow {
	
	//Who is registering the endpoint
	private int userId;
	 
	//Who can use the endpoint
	private String allowedUserType;
	 
	//Endpoint metaparameters
	private String endpointName;
	private String path;
	private int serviceId;
	private int methodId;
	private String requestParams;
	private String responseParams;
	 
	public AddEndpointRow() {}

	public AddEndpointRow(int userId, String allowedUserType, String endpointName, String path, int serviceId,
			int methodId, String requestParams, String responseParams ) {
		this.userId = userId;
		this.allowedUserType = allowedUserType;
		this.endpointName = endpointName;
		this.path = path;
		this.serviceId = serviceId;
		this.methodId = methodId;
		this.requestParams = requestParams;
		this.responseParams = responseParams;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAllowedUserType() {
		return allowedUserType;
	}

	public void setAllowedUserType(String allowedUserType) {
		this.allowedUserType = allowedUserType;
	}

	public String getEndpointName() {
		return endpointName;
	}

	public void setEndpointName(String endpointName) {
		this.endpointName = endpointName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getMethodId() {
		return methodId;
	}

	public void setMethodId(int methodId) {
		this.methodId = methodId;
	}

	public String getRequestParams() { return requestParams; }

	public void setRequestParams(String requestParams) { this.requestParams = requestParams; }

	public String getResponseParams() { return responseParams; }

	public void setResponseParams(String responseParams) { this.responseParams = responseParams; }
}
