package model.dataStorage;

import java.io.IOException;
import model.Entity;

public class EntityObject extends Entity {

	public EntityObject(String name) throws IOException {
		super(null, null);
	}

	@Override
	public String toString(){
		return name;
	}
}
