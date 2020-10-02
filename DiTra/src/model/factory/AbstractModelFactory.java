package model.factory;

import java.io.IOException;

import model.InfNode;

public abstract class AbstractModelFactory{

	abstract InfNode getNode(String input) throws IOException;
}
