package com.faizan.collection.prac.prac23;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapIssue {
	//HashMap<String, String> map = new HashMap<String, String>();
	ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
	
	public void add() {
		for(int i=0;i<50;i++) {
			map.put("hi"+i, "hekl"+i);
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
		ConcurrentMapIssue concurrentListPrr = new ConcurrentMapIssue();
		Thread t1= new Thread(()->concurrentListPrr.add());
		Thread t2= new Thread(()->concurrentListPrr.show());
		
		t1.start();
		t2.start();
	}


}
