package com.faizan.design.pattrens.strategy.prac2;

public class FlatDiscount implements DiscountStrategy{

	@Override
	public void giveDiscount(double amount) {
	    System.out.println("Flat discount of :"+amount);
		
	}

}
