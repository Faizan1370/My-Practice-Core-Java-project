package com.faizan.design.pattrens.strategy;

public class StrategyPattern {
	public static void main(String[] args) {
		ApplyDiscount applyDiscount = new ApplyDiscount(new FlatDiscount());
		applyDiscount.getDiscount();
	}

}
