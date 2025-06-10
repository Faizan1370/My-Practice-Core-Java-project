package com.faizan.design.pattrens.decorator.prac;

public class TestIcecreamDecorator {
	public static void main(String[] args) {
		Icecream vanilaIcecream =  new ChochoChipsDecorator(new ChochoSyrupDecorator(new Vanila()));
		 System.out.println(vanilaIcecream.getCost() + " "+vanilaIcecream.getDescription());
	}

}
