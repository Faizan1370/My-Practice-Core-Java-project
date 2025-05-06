package com.faizan.design.pattrens.decorator;

public class ChocoChipsDecorator implements Icecream{
	
	private Icecream icecream;
	
	public ChocoChipsDecorator(Icecream icecream) {
		this.icecream=icecream;
	}

	@Override
	public int getCost() {
		
		return icecream.getCost()+15;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return icecream.description() +" Chocho Chips Decorator Added";
	}

}
