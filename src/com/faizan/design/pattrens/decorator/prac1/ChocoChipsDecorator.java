package com.faizan.design.pattrens.decorator.prac1;

public class ChocoChipsDecorator implements Icecream {
	
	private Icecream icecream;
	
	public ChocoChipsDecorator(Icecream icecream) {
		this.icecream = icecream;
	}

	@Override
	public int getCost() {
		
		return icecream.getCost()+7;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return icecream.getDescription() + " Choco chips decorator";
	}

}
