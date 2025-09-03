package com.faizan.design.pattrens.decorator.prac4;

public class ChochoBar implements Icecream{

	@Override
	public int amount() {
		return 30;
	}

	@Override
	public String description() {
		return "Choco Bar Icecream";
	}

}
