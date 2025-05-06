package com.faizan.design.pattrens.factory;

public class MobileFactory {
	
	public Mobile orderMobile(String keyword) {
		if(keyword.equalsIgnoreCase("iphone")) {
			return new Iphone();
		}else if(keyword.equalsIgnoreCase("iphone")) {
			return new OnePlus();
		}else {
			return new Redmi();
		}
		
	}

}
