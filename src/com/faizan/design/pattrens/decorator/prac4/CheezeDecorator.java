package com.faizan.design.pattrens.decorator.prac4;

public class CheezeDecorator implements Icecream{

	
private Icecream icecream;
	
	public CheezeDecorator(Icecream icecream) {
		this.icecream=icecream;
	}
	
	@Override
	public int amount() {
		
		return icecream.amount()+15;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return icecream.description() +" Cheeze decorator";
	}
}
