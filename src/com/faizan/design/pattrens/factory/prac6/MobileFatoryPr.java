package com.faizan.design.pattrens.factory.prac6;

public class MobileFatoryPr {
	
	public Mobile orderMobile(String type) {
		if(type.equalsIgnoreCase("symphony")) {
			return new Symphoy();
		}else if(type.equalsIgnoreCase("nokia")) {
			return new Nokia();
		}else {
			throw new RuntimeException("invalid type or not available");
		}
	}
	

}
