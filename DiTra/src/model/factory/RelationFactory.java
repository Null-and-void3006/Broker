package model.factory;

import java.io.IOException;

import model.Attribut;
import model.Entity;
import model.InfNode;
import model.Relation;

public class RelationFactory{


	public Relation getNode(String input) throws IOException {
		// TODO Auto-generated method stub
		return new Relation(input, null, null, null, null);
	}
	
	public Relation setParams(Relation e, String name, Entity fromEntity, Entity toEntity,
			Attribut fromAttribute, Attribut toAttribute)
	{
		e.setName(name);
		e.setFromAttribute(fromAttribute);
		e.setToAttribute(toAttribute);
		e.setFromEntity(fromEntity);
		e.setToEntity(toEntity);
		return e;
	}

}
