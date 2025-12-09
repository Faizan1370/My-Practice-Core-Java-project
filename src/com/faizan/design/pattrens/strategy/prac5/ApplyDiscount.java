package com.faizan.design.pattrens.strategy.prac5;

public class ApplyDiscount {
	
	private DiscountStrategy discountStrategy;
	
	public ApplyDiscount(DiscountStrategy discountStrategy) {
		this.discountStrategy=discountStrategy;
	}
	
	public void setStrategy(DiscountStrategy discountStrategy) {
		this.discountStrategy=discountStrategy;
	}
	
	public void askDiscount() {
		discountStrategy.giveDiscount();
	}
	

}
