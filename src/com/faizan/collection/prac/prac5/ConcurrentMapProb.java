package com.faizan.collection.prac.prac5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapProb {
	
	//Map<String,Integer> map = new HashMap<String, Integer>();
	ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
	
	public void add() {
		for(int i=0;i<100;i++) {
			map.put("hi"+i, i);
		}
	}
	public void show() {
		while(true) {
			for(Map.Entry<String, Integer> entry: map.entrySet()) {
				System.out.println(entry);
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
		ConcurrentMapProb consurrenListProb = new ConcurrentMapProb();
		Thread t1 = new Thread(()->consurrenListProb.add());
		Thread t2 = new Thread(()->consurrenListProb.show());
		
		t1.start();
		t2.start();
	}


}
