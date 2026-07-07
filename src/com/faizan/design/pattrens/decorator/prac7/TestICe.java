package com.faizan.design.pattrens.decorator.prac7;

public class TestICe {
	public static void main(String[] args) {
		IcecreamInt icecreamInt = new VanilaIce();
		System.out.println(icecreamInt.getPrice());
		System.out.println(icecreamInt.getDescription());
		ChochoChipsDecorator chochoChipsDecorator = new ChochoChipsDecorator(icecreamInt);
		System.out.println(chochoChipsDecorator.getPrice());
		System.out.println(chochoChipsDecorator.getDescription());
		ChochoSyruypDecorator chochoSyruypDecorator = new ChochoSyruypDecorator(chochoChipsDecorator);
		System.out.println(chochoSyruypDecorator.getPrice());
		System.out.println(chochoSyruypDecorator.getDescription());
		
	}

}
