package com.faizan.design.pattrens.decorator.prac3;

public class ChochoChipsDecorator implements Icecream{
     private Icecream icecream;
     
     public ChochoChipsDecorator(Icecream icecream) {
    	 this.icecream=icecream;
     }
	
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return icecream.getPrice()+10;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return icecream.getDescription() +"  with Chocho chips decorator";
	}

}
