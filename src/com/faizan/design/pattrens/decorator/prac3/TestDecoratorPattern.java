package com.faizan.design.pattrens.decorator.prac3;

public class TestDecoratorPattern {
	
	public static void main(String[] args) {
		
		Icecream icecream = new ChochoChipsDecorator(new ChochoBar());
		System.out.println(icecream.getPrice() + " "+ icecream.getDescription());
		
	}

}
