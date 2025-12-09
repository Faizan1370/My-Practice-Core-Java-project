package com.faizan.design.pattrens.factory.prac6;

public class TestFact {
	
	public static void main(String[] args) {
	  MobileFatoryPr fatoryPr = new MobileFatoryPr();
	  Mobile orderMobile = fatoryPr.orderMobile("nokia");
	  orderMobile.createMobile();
	}

}
