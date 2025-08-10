package com.faizan.design.pattrens.factory.prac3;

public class MobileFatctory {
	
	public Mobile orderMobile(String type) {
		if(type.equalsIgnoreCase("iphone")) {
			return new Iphone();
		}else if(type.equalsIgnoreCase("redmi")) {
			return new Redmi();
		}else {
			return (Mobile) new RuntimeException("Invalid type");
		}
		
	}

}
