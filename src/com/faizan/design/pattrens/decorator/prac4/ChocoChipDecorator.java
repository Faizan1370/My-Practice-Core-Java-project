package com.faizan.design.pattrens.decorator.prac4;

public class ChocoChipDecorator implements Icecream{
    
	private Icecream icecream;
	
	public ChocoChipDecorator(Icecream icecream) {
		this.icecream=icecream;
	}
	
	@Override
	public int amount() {
		
		return icecream.amount()+10;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return icecream.description() +" chochochips decorator";
	}

}
