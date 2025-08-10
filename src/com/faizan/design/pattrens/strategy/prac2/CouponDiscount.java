package com.faizan.design.pattrens.strategy.prac2;

public class CouponDiscount implements DiscountStrategy{

	@Override
	public void giveDiscount(double amount) {
		System.out.println("Coupan Discount of :"+amount);
	}

}
