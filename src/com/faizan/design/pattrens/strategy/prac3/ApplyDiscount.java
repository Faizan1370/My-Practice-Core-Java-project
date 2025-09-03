package com.faizan.design.pattrens.strategy.prac3;

public class ApplyDiscount {
	
	private DiscountStrategy discountStrategy;
	
	public ApplyDiscount(DiscountStrategy discountStrategy) {
		this.discountStrategy=discountStrategy;
	}
	
	public void sentStratgey(DiscountStrategy discountStrategy) {
		this.discountStrategy=discountStrategy;
	}
	
	public void getDiscount() {
		discountStrategy.giveDiscount();
	}

}
