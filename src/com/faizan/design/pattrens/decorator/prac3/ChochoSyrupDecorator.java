package com.faizan.design.pattrens.decorator.prac3;

public class ChochoSyrupDecorator  implements Icecream{
	private Icecream icecream;
	
	public ChochoSyrupDecorator(Icecream icecream) {
		this.icecream=icecream;
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return icecream.getPrice()+15;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return icecream.getDescription() +" with chocho syrup decorator";
	}

}
