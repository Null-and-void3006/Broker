package model;

import enums.AtributeType;

public class CountAttribute extends Attribut{

	private int count = 0;
	
	public CountAttribute() {
		super("Count", 50, AtributeType.CountAttribute, false, true, 1);
		this.count=count;
	}

	public void incConut() {
		count++;
	}
	
	public int getCount() {
		return count;
	}
}
