package com.faizan.design.pattrens.decorator.prac5;

public class Test {
	
	public static void main(String[] args) {
		Icecream icecream = new Vanila();
		icecream= new ChochoSyrupDecorator(icecream);
		System.out.println(icecream.getPrice() +" "+icecream.description());
	}


}
