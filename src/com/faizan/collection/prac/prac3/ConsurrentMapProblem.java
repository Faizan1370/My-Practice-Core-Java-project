package com.faizan.collection.prac.prac3;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ConsurrentMapProblem {
	//Map<String,String> map = new HashMap<String, String>();
	
	ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
	
	public void add() {
		for(int i=0;i<100;i++) {
			map.put("hello"+i, "hi"+i);
		}
	}
	
	public void show() {
		while(true) {
			for(Entry<String, String> entry:map.entrySet()) {
				System.out.println(entry);
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
		ConsurrentMapProblem concurrentListProblem = new ConsurrentMapProblem();
		Thread t1 = new Thread(()->concurrentListProblem.add());
		Thread t2 = new Thread(()->concurrentListProblem.show());
		t1.start();
		t2.start();
	}

}
