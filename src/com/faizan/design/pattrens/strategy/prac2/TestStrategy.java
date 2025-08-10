package com.faizan.design.pattrens.strategy.prac2;

public class TestStrategy {
	
	public static void main(String[] args) {
		ApplyDiscount applyDiscount = new ApplyDiscount(new CashBackDiscount());
		applyDiscount.setDiscountStrategy(new FlatDiscount());
		applyDiscount.getDiscount(5);
	}

}
