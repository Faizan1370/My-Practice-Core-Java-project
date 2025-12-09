package com.faizan.design.pattrens.decorator.prac6;

public class ChochoBarIce implements IcecreamPat{

	@Override
	public int price() {
		return 30;
	}

	@Override
	public String description() {
		return "Choco Bar";
	}

}
