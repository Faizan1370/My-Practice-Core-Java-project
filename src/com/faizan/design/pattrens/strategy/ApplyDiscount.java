package com.faizan.design.pattrens.strategy;

public class ApplyDiscount {
	
	private DiscountStrategy discountStrategy;
	
	public ApplyDiscount(DiscountStrategy discountStrategy) {
		this.discountStrategy =discountStrategy;
		
	}
	
	public void setStrategy(DiscountStrategy discountStrategy) {
		this.discountStrategy=discountStrategy;
	}
	
	public void getDiscount() {
		discountStrategy.giveDiscount();
	}

}
