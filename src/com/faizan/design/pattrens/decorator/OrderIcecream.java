package com.faizan.design.pattrens.decorator;

public class OrderIcecream {
	
	public static void main(String[] args) {
		Icecream  icecream = new VanilaIcecream();
		System.out.println(icecream.getCost() +" "+icecream.description());
		icecream =  new ChocoSyrupDecorator(new ChocoChipsDecorator(icecream));
		System.out.println(icecream.getCost() +" "+icecream.description());
		 Icecream icecream2 = new ChoclateIcecream();
			System.out.println(icecream2.getCost() +" "+icecream2.description());
			icecream2 =  new ChocoSyrupDecorator(new ChocoChipsDecorator(icecream2));
			System.out.println(icecream2.getCost() +" "+icecream2.description());
	}

}
