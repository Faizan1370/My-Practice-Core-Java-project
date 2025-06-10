package com.faizan.design.pattrens.factory.prac1;

public class MobileFactory {
	
	
	public Mobile orderMobile(String type) {
		if(type.equalsIgnoreCase("iphone")) {
			return new Iphone();
		}else if(type.equalsIgnoreCase("redmi")){
			return new Redmi();
		}else {
			return null;
		}
	}

}
