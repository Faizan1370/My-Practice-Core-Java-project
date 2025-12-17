package com.faizan.collection.prac.prac27;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapRRR {
	//HashMap<String, String> map = new HashMap<String, String>();
	ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
	public void test() {
		ConcurrentHashMap<String, String> map1 = new ConcurrentHashMap<String, String>();
		for(int i=0;i<50;i++) {
			map1.put("hi"+i, "hi");
		}
		for(Map.Entry<String, String> entry:map1.entrySet()) {
			System.out.println(entry.getKey());
			map1.put("lel", "hij");
		}
	}
	
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
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		ConcurrentMapRRR concurrentListRR = new ConcurrentMapRRR();
		concurrentListRR.test();
		/*
		 * Thread t1 = new Thread(()->concurrentListRR.add()); Thread t2 = new
		 * Thread(()->concurrentListRR.show());
		 * 
		 * t1.start(); t2.start();
		 */
	}

}
