package com.faizan.design.pattrens.strategy.prac6;

public class TestDis {
	
	public static void main(String[] args) {
		ApplyDiscountStrategy applyDiscountStrategy = new ApplyDiscountStrategy(new FlatDiscount());
		applyDiscountStrategy.applyDiscount();
		applyDiscountStrategy.setStrategy(new CouponDiscount());
		applyDiscountStrategy.applyDiscount();
	}

}
