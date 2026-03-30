package com.faizan.collection.prac.prac29;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurretMapE {
	//HashMap<String, String> map = new HashMap<String, String>();
	ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
	
	public void add() {
		for(int i=0;i<50;i++) {
			map.put("hi"+i, "hi");
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
		ConcurretMapE concurrentListE = new ConcurretMapE();
		Thread t1 = new Thread(()->concurrentListE.add());
		Thread t2 = new Thread(()->concurrentListE.show());
		
		t1.start();
		t2.start();
	}

}
