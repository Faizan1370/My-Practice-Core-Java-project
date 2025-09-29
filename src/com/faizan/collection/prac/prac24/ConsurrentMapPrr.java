package com.faizan.collection.prac.prac24;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.faizan.collection.prac.prac10.ConsurrentLisPrr;

public class ConsurrentMapPrr {
	
	//HashMap<String, String> map = new HashMap<String, String>();
	ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
	
	public void add() {
		for(int i=0;i<50;i++) {
			map.put("Hi"+i, "hk"+i);
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
		ConsurrentMapPrr consurrentLisPrr = new ConsurrentMapPrr();
		Thread t1 = new Thread(()->consurrentLisPrr.add());
		Thread t2 = new Thread(()->consurrentLisPrr.show());
		
		t1.start();
		t2.start();
	}

}
