package model.factory;

import java.io.IOException;
import model.Entity;
import model.InfNode;

public class EntityFactory extends AbstractModelFactory{

	@Override
	public InfNode getNode(String input) throws IOException {
		// TODO Auto-generated method stub
		return new Entity(input);
	}
	
	public Entity setParams(Entity e, String name, String url)
	{
		e.setName(name);
		return e;
	}

}
