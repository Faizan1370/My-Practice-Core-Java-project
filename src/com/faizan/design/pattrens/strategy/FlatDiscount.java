package com.faizan.design.pattrens.strategy;

public class FlatDiscount implements DiscountStrategy{

	@Override
	public void giveDiscount() {
		System.out.println("flat discount");
		
	}

}
