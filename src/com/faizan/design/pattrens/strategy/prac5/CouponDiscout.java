package com.faizan.design.pattrens.strategy.prac5;

public class CouponDiscout implements DiscountStrategy{

	@Override
	public void giveDiscount() {
		System.out.println("Coupon Discount");
		
	}

}
