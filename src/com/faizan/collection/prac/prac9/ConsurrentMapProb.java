package com.faizan.collection.prac.prac9;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConsurrentMapProb {
	
	//HashMap<String, String> map = new HashMap<String, String>();
	ConcurrentHashMap<String, String> map= new ConcurrentHashMap<String, String>();
	
	public void add() {
		for(int i=0;i<50;i++) {
			map.put("hi"+i, "hi");
		}
	}
	
	public void show() {
		while(true) {
			for(Map.Entry<String,String> entry:map.entrySet()) {
				System.out.println(entry.getKey() +" "+entry.getValue());
			}
		}
	}
	
	public static void main(String[] args) {
		ConsurrentMapProb consurrentListProb = new ConsurrentMapProb();
		Thread t1= new Thread(()->consurrentListProb.add());
		Thread t2= new Thread(()->consurrentListProb.show());
		
		t1.start();
		t2.start();
	}

}
