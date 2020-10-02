package model;

import java.util.ArrayList;

import enums.InfResourceType;

public class InfResource extends InfNode {
	protected String description;
	protected String location;
	private String metaSchemaLocation;
	protected InfResourceType type;
	protected ArrayList<Package> packages;
	protected String jsonString = new String();
	
	public InfResource(String name,String description,
						String location, String metaSchemaLocation,
						InfResourceType type) {
		super(name);
		this.description = description;
		this.location = location;
		this.setMetaSchemaLocation(metaSchemaLocation);
		this.type = type;
		packages = new ArrayList<>();
	}
	
	public void addPackage(Package pcg){
		packages.add(pcg);
		add(pcg);
	}
	
	public void removePackage(Package pcg){
		packages.remove(pcg);
		remove(pcg);
	}

	public ArrayList<Package> getPackage() {
		return packages;
	}

	public String getMetaSchemaLocation() {
		return metaSchemaLocation;
	}

	public void setMetaSchemaLocation(String metaSchemaLocation) {
		this.metaSchemaLocation = metaSchemaLocation;
	}
	
	
	public String getDescription() {
		return description;
	}

	public String getLocation() {
		return location;
	}

	public InfResourceType getType() {
		return type;
	}
	
	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public void testPrint() {
		System.out.println("/----" + this.name + "----\\");
		for (Package p: packages) {
			p.testPrint();
		}
		System.out.println("\\----" + this.name + "----/");
	}

	public ArrayList<Package> getPackages() {
		return packages;
	}

	public void setPackages(ArrayList<Package> packages) {
		this.packages = packages;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setType(InfResourceType type) {
		this.type = type;
	}
}
