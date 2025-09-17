package com.faizan.collection.prac.prac22;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConsurrentMapPrr {
	//HashMap<String, String> map = new HashMap<String, String>();
	ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
	
	public void add() {
		for(int i=0;i<50;i++) {
			map.put("hi"+i, "hj"+i);
		}
	}
	
	public void show() {
		while(true) {
			for(Map.Entry<String, String> entrty:map.entrySet()) {
				System.out.println(entrty.getKey() +" "+entrty.getValue());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ConsurrentMapPrr concurrentListPrr = new ConsurrentMapPrr();
		Thread t1 = new Thread(()->concurrentListPrr.add());
		Thread t2 = new Thread(()->concurrentListPrr.show());
		
		t1.start();
		t2.start();
	}


}
