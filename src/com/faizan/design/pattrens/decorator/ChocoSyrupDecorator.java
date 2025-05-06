package com.faizan.design.pattrens.decorator;

public class ChocoSyrupDecorator implements Icecream{
	
	private Icecream icecream;
	
	public ChocoSyrupDecorator(Icecream icecream) {
		this.icecream=icecream;
	}

	@Override
	public int getCost() {
	
		return icecream.getCost() +10;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return icecream.description()+" Choco Syrup Decorator Added";
	}

}
