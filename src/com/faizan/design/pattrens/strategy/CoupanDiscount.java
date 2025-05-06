package com.faizan.design.pattrens.strategy;

public class CoupanDiscount implements DiscountStrategy{

	@Override
	public void giveDiscount() {
		System.out.println("Coupan Discount");
		
	}

}
