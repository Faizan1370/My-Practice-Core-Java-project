package com.faizan.design.pattrens.strategy.prac1;

public class CoupanDiscount implements DiscountStrategy{

	@Override
	public void giveDiscount() {
		System.out.println("Coupan Discount");
	}

}
