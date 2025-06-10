package com.faizan.design.pattrens.decorator.prac;

public class ChochoSyrupDecorator  implements Icecream{
	private Icecream icecream;
	
	public ChochoSyrupDecorator(Icecream icecream) {
		this.icecream = icecream;
	}

	@Override
	public int getCost() {
		
		return icecream.getCost()+15;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return icecream.getDescription() +" Chocho Syrup Decorator";
	}

}
