package com.faizan.design.pattrens.strategy.prac4;

public class Test {
	
	public static void main(String[] args) {
		ApplyDiscount applyDiscount = new ApplyDiscount(new FlatDiscount());
		applyDiscount.setStrategy(new CouponDiscount());
		applyDiscount.addDiscount();
	}

}
