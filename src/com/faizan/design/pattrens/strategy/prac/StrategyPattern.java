package com.faizan.design.pattrens.strategy.prac;

public class StrategyPattern {
	
	public static void main(String[] args) {
		ApplyDiscount applyDiscount = new ApplyDiscount(new CouppanDiscount());
		//applyDiscount.setStrategy(new FlatDiscount());
		applyDiscount.getDiscount();
	}

}
