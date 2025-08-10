package com.faizan.design.pattrens.factory.prac3;

public class TestFactory {
	
	public static void main(String[] args) {
		MobileFatctory fatctory = new MobileFatctory();
		Mobile orderMobile = fatctory.orderMobile("iphone");
		orderMobile.createMobile();
	}

}
