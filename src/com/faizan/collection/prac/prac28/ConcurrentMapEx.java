package com.faizan.collection.prac.prac28;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapEx {
	//HashMap<String, String> map = new HashMap<String, String>();
	ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
	
	public void add() {
		for(int i=0;i<50;i++) {
			map.put("hi"+i, "hello");
		}
	}
	
	public void show() {
		while(true) {
			for(Map.Entry<String, String> entry:map.entrySet()) {
				System.out.println(entry.getKey() +" "+entry.getValue());
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
		ConcurrentMapEx concurrentMapEx = new ConcurrentMapEx();
		Thread t1 = new Thread(()->concurrentMapEx.add());
		Thread t2 = new Thread(()->concurrentMapEx.show());
		
		t1.start();
		t2.start();
	}

}
