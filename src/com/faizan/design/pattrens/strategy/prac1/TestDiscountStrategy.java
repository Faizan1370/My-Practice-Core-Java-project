package com.faizan.design.pattrens.strategy.prac1;

public class TestDiscountStrategy {
	public static void main(String[] args) {
		ApplyDiscount applyDiscount = new ApplyDiscount();
		applyDiscount.setDiscountStrategy(new FlatDiscount());
		applyDiscount.applyDiscount();
		applyDiscount.setDiscountStrategy(new CoupanDiscount());
		applyDiscount.applyDiscount();
	
	}
	
}
