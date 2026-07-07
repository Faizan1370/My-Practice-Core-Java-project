package com.faizan.design.pattrens.decorator.prac7;

public class ChochoChipsDecorator implements IcecreamInt{
	private IcecreamInt icecreamInt;
	
	public ChochoChipsDecorator(IcecreamInt icecreamInt) {
		this.icecreamInt=icecreamInt;
	}

	@Override
	public int getPrice() {
	
		return icecreamInt.getPrice() + 10;
	}

	@Override
	public String getDescription() {
		
		return icecreamInt.getDescription() + " "+"chocho chips Decorator";
	}

}
