package com.faizan.design.pattrens.strategy.prac6;

public class CouponDiscount implements DisciountStrategy {

	@Override
	public void giveDiscount() {
		System.out.println("Coupon Discount");
		
	}

}
