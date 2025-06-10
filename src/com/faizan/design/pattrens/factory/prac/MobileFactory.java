package com.faizan.design.pattrens.factory.prac;

public class MobileFactory {
	
	public Mobile orderMobile(String type) {
		if(type.equalsIgnoreCase("Iphone")) {
			return new Iphone();
		}else if(type.equalsIgnoreCase("redmid")) {
			return new Redmi();
		}else {
			return null;
		}
	}

}
