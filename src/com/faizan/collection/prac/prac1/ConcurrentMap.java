package com.faizan.collection.prac.prac1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMap {
	
	//private static Map<String,String> map = new HashMap<>();
	
	private static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
	
	public void add() {
		for(int i=0;i<1000;i++) {
			map.put("key"+i,"val"+i);
		}
	}
	
	public void print() {
		while(true) {
			for(Map.Entry<String, String> enrty : map.entrySet()) {
				System.out.println(enrty);
				/*
				 * try { Thread.sleep(100); } catch (InterruptedException e) { // TODO
				 * Auto-generated catch block e.printStackTrace(); }
				 */
			}
		}
	}
	
	public static void main(String[] args) {
		
		ConcurrentMap conCurreentList1 = new ConcurrentMap();
		Thread t1 = new Thread(()->conCurreentList1.add());
		Thread t2 = new Thread(()->conCurreentList1.print());
		
		t1.start();
		t2.start();
	}

}
