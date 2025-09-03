package com.faizan.collection.prac.prac21;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapPr {
	
	//HashMap<String, String> map = new HashMap<String, String>();
	ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
	
	
	public void add() {
		for(int i=0;i<50;i++) {
			map.put("hi"+i, "hi");
		}
	}
	
	public void show() {
		while(true) {
			for(Map.Entry<String, String> entry: map.entrySet()) {
				System.out.println(entry.getValue() +" "+entry.getKey());
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
		ConcurrentMapPr consurrentListPr = new ConcurrentMapPr();
		Thread t1= new Thread(()->consurrentListPr.add());
		Thread t2 = new Thread(()->consurrentListPr.show());
		
		t1.start();
		t2.start();
	}

}
