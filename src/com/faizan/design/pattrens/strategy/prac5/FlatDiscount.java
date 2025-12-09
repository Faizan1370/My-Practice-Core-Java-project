package com.faizan.design.pattrens.strategy.prac5;

public class FlatDiscount implements DiscountStrategy{

	@Override
	public void giveDiscount() {
		System.out.println("flat Disocunt");
	}

}
