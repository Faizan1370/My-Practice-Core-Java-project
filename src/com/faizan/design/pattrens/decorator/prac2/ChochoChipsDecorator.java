package com.faizan.design.pattrens.decorator.prac2;

import com.faizan.design.pattrens.decorator.Icecream;

public class ChochoChipsDecorator implements Icream{
     private Icream icream;

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return icream.getPrice()+4;
	}

	@Override
	public String getDiscription() {
		// TODO Auto-generated method stub
		return icream.getDiscription()+" Choco chips decorator";
	}  
	
	
}
