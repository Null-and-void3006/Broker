package model.factory;

import javax.management.Attribute;

import enums.AtributeType;
import model.Attribut;
import model.InfNode;

public class AttributeFactory extends AbstractModelFactory{

	@Override
	public InfNode getNode(String input) {
		// TODO Auto-generated method stub
		return new Attribut(input);
	}
	
	public Attribut setParams(Attribut e, String name, int lenght, AtributeType type,
			boolean primaryKey, boolean mandatory, int group)
	{
		e.setName(name);
		e.setLength(lenght);
		e.setType(type);
		e.setPrimaryKey(primaryKey);
		e.setMandatory(mandatory);
		e.setGroup(group);
		return e;
	}

}
