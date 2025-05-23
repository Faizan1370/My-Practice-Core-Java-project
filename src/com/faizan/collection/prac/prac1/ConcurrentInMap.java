package com.faizan.collection.prac.prac1;

import java.util.HashMap;
import java.util.Map;

public class ConcurrentInMap {
	
   Map<String,String> map = new HashMap<String, String>();
	
	public void add() {
		for(int i=0;i<1000;i++) {
			map.put("vav"+i, "bhgv"+i);
		}
	}
	
	public void print() {
		while(true) {
		for(Map.Entry<String, String> enrty:map.entrySet()) {
			 System.out.println(enrty.getKey() + " "+enrty.getValue());
			
		}
		}
	}
	
	public static void main(String[] args) {
		ConcurrentInMap conCurrentProMap = new ConcurrentInMap();
		 
		Thread t1 = new Thread(()->conCurrentProMap.add());
		Thread t2 = new Thread(()->conCurrentProMap.print());
		t1.start();
		t2.start();
	}

}
