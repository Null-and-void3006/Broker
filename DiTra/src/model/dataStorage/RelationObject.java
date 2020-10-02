package model.dataStorage;

import model.Attribut;
import model.Entity;
import model.Relation;

public class RelationObject extends Relation {

	public RelationObject(String name, Entity fromEntity, Entity toEntity,
						  Attribut fromAttribut, Attribut toAttribut) {
		super(name, fromEntity, toEntity, fromAttribut, toAttribut);
	}

	@Override
	public String toString(){
		return getName();
	}
}
