package com.faizan.design.pattrens.factory.prac7;

public class Testfac {
	public static void main(String[] args) {
		MobileFactory factory = new MobileFactory();
		Mobile orderMobile = factory.orderMobile("oneplus");
		orderMobile.createMobile();
	}

}
