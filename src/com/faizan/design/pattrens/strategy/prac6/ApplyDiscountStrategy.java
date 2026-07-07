package com.faizan.design.pattrens.strategy.prac6;

public class ApplyDiscountStrategy {
	
	private DisciountStrategy disciountStrategy;
	
	public ApplyDiscountStrategy(DisciountStrategy disciountStrategy) {
		this.disciountStrategy=disciountStrategy;
	}
	public void setStrategy(DisciountStrategy disciountStrategy) {
		this.disciountStrategy=disciountStrategy;
	}
	
	public void applyDiscount() {
		disciountStrategy.giveDiscount();
	}

}
