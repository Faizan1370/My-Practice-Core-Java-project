package com.faizan.design.pattrens.strategy.prac5;

public class TestStr {
	public static void main(String[] args) {
		ApplyDiscount applyDiscount = new ApplyDiscount(new FlatDiscount());
		applyDiscount.askDiscount();
		applyDiscount.setStrategy(new CouponDiscout());
		applyDiscount.askDiscount();
		
	}
	


}
