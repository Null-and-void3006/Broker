package model;

import java.util.ArrayList;

import enums.PackageType;

public class Package extends InfNode{

	private PackageType type;
	private String username;
	private String password;
	private String connection;
	
	protected ArrayList<Entity> entities;
	
	public Package(String name, PackageType type, String username, String password, String connection) {
		super(name);
		entities = new ArrayList<Entity>();
		this.type = type;
		this.username=username;
		this.password=password;
		this.connection=connection;
	}
	
	public void addEntity(Entity ent){
		entities.add(ent);
		add(ent);
	}
	
	public void removeEntity(Entity ent){
		entities.remove(ent);
		remove(ent);
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public PackageType getType() {
		return type;
	}

	public void testPrint() {
		System.out.println("/----" + this.name + "----\\");
		for (Entity e: entities) {
			e.testPrint();
		}
		System.out.println("\\----" + this.name + "----/");
	}
}
