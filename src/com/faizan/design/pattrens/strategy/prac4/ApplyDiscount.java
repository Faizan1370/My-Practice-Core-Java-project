package com.faizan.design.pattrens.strategy.prac4;

public class ApplyDiscount {
	
	private DiscountStratgy discountStratgy;
	
	public ApplyDiscount(DiscountStratgy discountStratgy) {
		this.discountStratgy=discountStratgy;
	}
	
	public void setStrategy(DiscountStratgy discountStratgy) {
		this.discountStratgy=discountStratgy;
	}
	
	public void addDiscount() {
		discountStratgy.giveDiscount();
	}

}
