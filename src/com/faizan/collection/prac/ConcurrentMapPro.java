package com.faizan.collection.prac;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapPro {
	
	//static Map<String,Integer> map = new  HashMap<String, Integer>();
	static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
	
	public void add() {
		for(int i=0;i<100;i++) {
			map.put("data"+i, i);
		}
	}
	
	public void read() {
		while(true) {
			for(Map.Entry<String, Integer> entry:map.entrySet()) {
				System.out.println(entry.getKey() + " "+entry.getValue());
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
		ConcurrentMapPro conCurrentList = new ConcurrentMapPro();
		Thread t1 = new Thread(()->conCurrentList.add());
		Thread t2 = new Thread(()->conCurrentList.read());
		
		t1.start();
		t2.start();
	}

}
