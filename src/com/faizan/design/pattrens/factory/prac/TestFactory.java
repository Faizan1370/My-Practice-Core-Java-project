package com.faizan.design.pattrens.factory.prac;

public class TestFactory {
	
	public static void main(String[] args) {
		MobileFactory factory = new MobileFactory();
		Mobile orderMobile = factory.orderMobile("iphone");
	   orderMobile.createMobie();
		
	}

}
