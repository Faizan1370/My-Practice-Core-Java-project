package com.faizan.collection.prac.prac2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapProb {
	//static Map<String,Integer> map = new HashMap<String, Integer>();
	static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
	
	
	public void add() {
		for(int i=0;i<100;i++) {
			map.put("hi"+i, i);
		}
	}
	
	public void show() {
		while(true) {
			for(Map.Entry<String, Integer> enry :map.entrySet()) {
				System.out.println(enry);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ConcurrentMapProb cunsurrentListProb = new ConcurrentMapProb();
		Thread t1 = new Thread(()->cunsurrentListProb.add());
		Thread t2 = new Thread(()->cunsurrentListProb.show());
		
		t1.start();
		t2.start();
	}

}
