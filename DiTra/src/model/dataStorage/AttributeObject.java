package model.dataStorage;

import enums.AtributeType;
import model.Attribut;

public class AttributeObject extends Attribut {

	public AttributeObject(String name, int lenght, AtributeType type,
						  boolean primaryKey, boolean mandatory, int group) {
		super(name, lenght, type, primaryKey, mandatory,group);
	}

	@Override
	public String toString(){
		return name;
	}
}
