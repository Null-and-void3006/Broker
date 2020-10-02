package model;

import enums.AtributeType;

public class Attribut extends InfNode {
	private int length;
	private boolean primaryKey;
	private boolean mandatory;
	private AtributeType type;
	private int group;
	
	public Attribut(String name, int lenght, AtributeType type,
					boolean primaryKey, boolean mandatory, int group) {
		super(name);
		this.length = lenght;
		this.type = type;
		this.primaryKey = primaryKey;
		this.mandatory = mandatory;
		this.group=group;
	}
	
	public Attribut(String name) {
		// TODO Auto-generated constructor stub
		super(name);
	}
	
	public void testPrint() {
		System.out.println("		" + this.name);
	}

	public int getLenght() {
		return length;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public AtributeType getType() {
		return type;
	}

	public int getGroup() {
		return group;
	}
	
	@Override
	public String toString(){
		return name;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public void setType(AtributeType type) {
		this.type = type;
	}

	public void setGroup(int group) {
		this.group = group;
	}
	
	public void setName(String name) {
		super.setName(name);
	}

	public int getLength() {
		return length;
	}

	
}
