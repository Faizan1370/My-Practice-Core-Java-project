package com.faizan.design.pattrens.factory.prac5;

public class MobileFactory {
	
	public Mobile orderMobile(String type) {
		if(type.equalsIgnoreCase("poco")) {
			return new Poco();
		}else if (type.equalsIgnoreCase("realme")) {
			return new RealMe();
		}else {
			throw new RuntimeException("invalid type");
		}
	}

}
