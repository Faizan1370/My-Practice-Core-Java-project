package com.faizan.design.pattrens.strategy.prac3;

public class CouponDiscount implements DiscountStrategy{

	@Override
	public void giveDiscount() {
		System.out.println("Coupon Disocunt");
		
	}

}
