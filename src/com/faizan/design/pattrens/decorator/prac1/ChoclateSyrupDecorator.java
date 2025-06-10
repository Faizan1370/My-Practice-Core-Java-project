package com.faizan.design.pattrens.decorator.prac1;

public class ChoclateSyrupDecorator implements Icecream{
	
	private Icecream icecream;
	
	public ChoclateSyrupDecorator(Icecream icecream) {
		this.icecream=icecream;
	}

	@Override
	public int getCost() {
		
		return icecream.getCost()+5;
	}

	@Override
	public String getDescription() {
		
		return icecream.getDescription() + " Choclate syrup decorator";
	}

}
