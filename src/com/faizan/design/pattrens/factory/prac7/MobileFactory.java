package com.faizan.design.pattrens.factory.prac7;

import com.faizan.design.pattrens.factory.Redmi;

public class MobileFactory {
	
	public Mobile orderMobile(String key) {
		if(key.equals("iphone")) {
			return new IPhone();
		}else if(key.equals("oneplus")) {
			return new OnePlus();
		}else {
			throw new RuntimeException("Invlaud type");
		}
	}

}
