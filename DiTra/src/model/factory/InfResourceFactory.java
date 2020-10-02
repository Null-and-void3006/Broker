package model.factory;

import java.io.IOException;

import enums.InfResourceType;
import model.InfNode;
import model.InfResource;

public class InfResourceFactory extends AbstractModelFactory{
	
	@Override
	public InfNode getNode(String input) throws IOException {
		// TODO Auto-generated method stub
		return new InfResource(input, null, null, null, null);
	}
	
	public InfResource setParams(InfResource e, String name,String description,
						String location, String metaSchemaLocation,
						InfResourceType type)
	{
		
		e.setName(name);
		e.setDescription(description);
		e.setLocation(location);
		e.setMetaSchemaLocation(metaSchemaLocation);
		e.setType(type);
		
		return e;
		
	}

}
