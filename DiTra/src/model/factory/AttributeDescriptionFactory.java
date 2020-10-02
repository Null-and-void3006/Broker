package model.factory;

import model.AttributeDescription;
import model.InfNode;

public class AttributeDescriptionFactory extends AbstractModelFactory {

	@Override
	public InfNode getNode(String input) {
		
		return new AttributeDescription(input);
	}

}
