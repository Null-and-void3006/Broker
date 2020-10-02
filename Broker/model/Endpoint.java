package com.Tim401_6.Broker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "endpoint")
public class Endpoint {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
	 private String endpointName;
	 private String path;
	 private int serviceId;
	 private int methodId;
	 private String requestParams;
	 private String responseParams;
	 private boolean slozen;
	 
	 public Endpoint() {}

	 public Endpoint(String endpointName, String path, int serviceId, int methodId, String requestParams, String responseParams, boolean slozen) {
		 this.endpointName = endpointName;
		 this.path = path;
		 this.serviceId = serviceId;
		 this.methodId = methodId;
		 this.requestParams = requestParams;
		 this.responseParams = responseParams;
		 this.slozen = slozen;
	 }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isSlozen(){ return this.slozen; }

	public void setSlozen(boolean slozen) { this.slozen = slozen; }
}
