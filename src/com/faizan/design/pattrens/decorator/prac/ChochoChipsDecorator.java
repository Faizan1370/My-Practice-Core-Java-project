package com.faizan.design.pattrens.decorator.prac;

public class ChochoChipsDecorator  implements Icecream{
	
	private Icecream icecream;
	
	public ChochoChipsDecorator(Icecream icecream) {
		this.icecream=icecream;
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return icecream.getCost()+10;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return icecream.getDescription() +" Chocho Chips Decorator";
	}

}
