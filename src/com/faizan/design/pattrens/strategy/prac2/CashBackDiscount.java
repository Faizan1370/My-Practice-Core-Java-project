package com.faizan.design.pattrens.strategy.prac2;

public class CashBackDiscount implements DiscountStrategy{

	@Override
	public void giveDiscount(double amount) {
		System.out.println("Cash Back Discount of:"+amount);
	}

}
