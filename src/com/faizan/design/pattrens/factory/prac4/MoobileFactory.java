package com.faizan.design.pattrens.factory.prac4;

public class MoobileFactory {
	
	
	public Mobile orderMobile(String type) {
		if(type.equalsIgnoreCase("iphone")) {
			return new Iphone();
		}else if(type.equalsIgnoreCase("samsung")) {
			return new Samsung();
		}else {
			throw new RuntimeException("Invalid type");
		}
	}

}
