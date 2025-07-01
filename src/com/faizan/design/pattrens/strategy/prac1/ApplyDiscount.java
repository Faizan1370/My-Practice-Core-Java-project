package com.faizan.design.pattrens.strategy.prac1;

public class ApplyDiscount {
	
	private DiscountStrategy discountStrategy;
	
	public void setDiscountStrategy(DiscountStrategy discountStrategy) {
		this.discountStrategy=discountStrategy;
	}
	
	public void applyDiscount() {
		discountStrategy.giveDiscount();
	}

}
