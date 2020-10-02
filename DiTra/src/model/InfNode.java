package model;

import javax.swing.tree.DefaultMutableTreeNode;

public abstract class InfNode extends DefaultMutableTreeNode {
	protected String name;
	
	public InfNode(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public void testPrint(){
		// Za testiranje printovanjem;
		// Override u svim konkretnim klasama.
	}

	public void setName(String name) {
		this.name = name;
	}

}
