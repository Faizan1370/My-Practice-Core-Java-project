package com.faizan.design.pattrens.decorator;

public class VanilaIcecream implements Icecream{

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 60;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Vanila Icecream";
	}

}
