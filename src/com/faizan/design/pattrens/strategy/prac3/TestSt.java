package com.faizan.design.pattrens.strategy.prac3;

public class TestSt {
	
	public static void main(String[] args) {
		ApplyDiscount applyDiscount = new ApplyDiscount(new FlatDisocunt());
		applyDiscount.sentStratgey(new CouponDiscount());
		applyDiscount.getDiscount();
	}
}
