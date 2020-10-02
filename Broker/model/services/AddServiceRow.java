package com.Tim401_6.Broker.model.services;

import java.util.ArrayList;
import java.util.List;

public class AddServiceRow {

	private String serviceName;
	private String path; //the path to the service, for example https://www.google.com/search/
	private int userId;
	private String userName;
	private boolean isAbstract;
	private List<AddServiceRow> children;
	
	public AddServiceRow() { }

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public boolean isAbstract() { return this.isAbstract; }

	public void setAbstract(boolean anAbstract) { this.isAbstract = anAbstract;	}

/*	public int getServiceId(){ return this.getServiceId(); }

	public void setServiceId(int serviceId){ this.serviceId = serviceId; }
*/
	public List<AddServiceRow> getChildren(){ return this.children;}

	public void setChildren(List<AddServiceRow> children) {
		this.children = children;
	}
}
