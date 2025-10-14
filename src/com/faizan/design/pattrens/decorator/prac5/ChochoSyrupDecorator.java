package com.faizan.design.pattrens.decorator.prac5;

public class ChochoSyrupDecorator implements Icecream{
	private Icecream icecream;
	
	public ChochoSyrupDecorator(Icecream icecream) {
		this.icecream=icecream;
	}

	@Override
	public int getPrice() {
		return icecream.getPrice() +15;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return icecream.description() +" with Chocho Syrup Decorator";
	}

}
