package com.faizan.collection.prac.prac4;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ConsurrentMapProb {
	
	//Map<String,Integer> map = new HashMap<String, Integer>();
	ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
	
	public void add() {
		for(int i=0;i<50;i++) {
			map.put("hi"+i, i);
		}
	}
	
	public void show() {
		while(true) {
			for(Entry<String,Integer> enrt:map.entrySet()) {
				System.out.println(enrt);
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
		ConsurrentMapProb concurrentListProb = new ConsurrentMapProb();
		Thread t1= new Thread(()->concurrentListProb.add());
		Thread t2 = new Thread(()->concurrentListProb.show());
		
		t1.start();
		t2.start();
	}

}
