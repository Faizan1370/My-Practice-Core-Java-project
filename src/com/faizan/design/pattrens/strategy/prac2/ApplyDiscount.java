package com.faizan.design.pattrens.strategy.prac2;

public class ApplyDiscount {
	
	private DiscountStrategy discountStrategy;
	
	public ApplyDiscount(DiscountStrategy discountStrategy) {
		this.discountStrategy=discountStrategy;
	}
	
	public void setDiscountStrategy(DiscountStrategy discountStrategy) {
		this.discountStrategy=discountStrategy;
	}
	
	public void getDiscount(double amount) {
		discountStrategy.giveDiscount(amount);
	}

}
