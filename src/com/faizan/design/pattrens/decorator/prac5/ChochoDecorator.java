package com.faizan.design.pattrens.decorator.prac5;

public class ChochoDecorator implements Icecream{
	private Icecream icecream;
	
	public ChochoDecorator(Icecream icecream) {
		this.icecream=icecream;
	}


	@Override
	public int getPrice() {
		return icecream.getPrice()+10;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return icecream.description() +" Chocho Decorator";
	}

}
