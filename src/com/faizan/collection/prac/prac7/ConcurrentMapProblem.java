package com.faizan.collection.prac.prac7;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapProblem {
	
	//HashMap<String, Integer> map = new HashMap<String, Integer>();
	ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
	
	public void add() {
		for(int i=0;i<50;i++) {
			map.put("hi"+i, i);
		}
	}
	
	public void show() {
		while(true) {
			for(Map.Entry<String, Integer> entry:map.entrySet()) {
				System.out.println(entry);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ConcurrentMapProblem concurrentListProb = new ConcurrentMapProblem();
		Thread t1= new Thread(()->concurrentListProb.add());
		Thread t2= new Thread(()->concurrentListProb.show());
		
		t1.start();
		t2.start();
	}

}
