package com.faizan.design.pattrens.decorator.prac7;

public class ChochoSyruypDecorator implements IcecreamInt{
	
	private IcecreamInt icecreamInt;
	
	public ChochoSyruypDecorator(IcecreamInt icecreamInt) {
		this.icecreamInt =icecreamInt;
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return icecreamInt.getPrice() + 15;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return icecreamInt.getDescription() +" "+"chocho syrup Decorator";
	}

}
