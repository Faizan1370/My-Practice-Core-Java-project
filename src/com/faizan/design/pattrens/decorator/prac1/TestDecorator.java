package com.faizan.design.pattrens.decorator.prac1;

public class TestDecorator {
	
	public static void main(String[] args) {
		Icecream icecream = new ChoclateSyrupDecorator(new ChocoChipsDecorator(new ButterScotch()));
		 System.out.println(icecream.getCost() + " "+icecream.getDescription());
	}

}
