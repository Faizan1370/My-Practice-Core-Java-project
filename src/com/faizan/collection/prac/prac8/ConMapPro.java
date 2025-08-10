package com.faizan.collection.prac.prac8;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConMapPro {
	
	//HashMap<String, String> hashMap = new HashMap<String, String>();
	ConcurrentHashMap<String, String> hashMap= new ConcurrentHashMap<String, String>();
	
	public void add() {
		for(int i=0;i<50;i++) {
			hashMap.put("hi"+i, "hi"+i);
		}
	}
	
	public void show() {
		while(true) {
			for(Map.Entry<String, String> entry : hashMap.entrySet()) {
				
				try {
					System.out.println(entry.getKey()+" "+entry.getValue());
					entry.getValue().toLowerCase();
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ConMapPro concurrentListProb = new ConMapPro();
		Thread t1 = new Thread(()->concurrentListProb.add());
		Thread t2= new Thread(()->concurrentListProb.show());
		
		t1.start();
		t2.start();
		
	}

}
