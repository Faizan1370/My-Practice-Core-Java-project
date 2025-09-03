package com.faizan.design.pattrens.decorator.prac4;

public class Test {
	
	public static void main(String[] args) {
		Icecream icecream = new Vanila();
		icecream= new CheezeDecorator(new ChocoChipDecorator(icecream));
		System.out.println(icecream.amount() + " "+ icecream.description());
	}

}
