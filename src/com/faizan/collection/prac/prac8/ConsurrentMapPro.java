package com.faizan.collection.prac.prac8;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConsurrentMapPro {
	
	//HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
	ConcurrentHashMap<String, Integer> hashMap= new ConcurrentHashMap<String, Integer>();
	
	public void add() {
		for(int i=0;i<50;i++) {
			hashMap.put("Hi"+i, i);
		}
	}
	
	public void show() {
		while(true) {
			for(Map.Entry<String, Integer> entry:hashMap.entrySet()) {
				System.out.println(entry.getKey() +" "+entry.getKey());
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
		ConsurrentMapPro concurrentListProb = new ConsurrentMapPro();
		Thread t1 = new Thread(()->concurrentListProb.add());
		Thread t2= new Thread(()->concurrentListProb.show());
		
		t1.start();
		t2.start();
	}


}
