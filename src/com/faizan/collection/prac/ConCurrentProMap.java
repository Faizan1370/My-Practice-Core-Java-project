package com.faizan.collection.prac;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConCurrentProMap {
	
	Map<String,String> map = new HashMap<>();
	//ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
	 public void addData() {
		  for(int i=0;i<1000;i++) {
			  map.put("abc"+i, "hxd"+i);
			
		  }
		 
	 }
	 public void readData() {
		 while(true) { //need to check every time map has data to itrate and no need to add sleep here
			 for(Map.Entry<String, String> enrty :map.entrySet()) {
				  System.out.println(enrty.getKey() + " "+enrty.getValue());
				
			  } 
		 }
		 
		 
	 }
	 
	 public static void main(String[] args) {
		ConCurrentProMap conCurrentProMap = new ConCurrentProMap();
		 
		Thread t1 = new Thread(()->conCurrentProMap.addData());
		Thread t2 = new Thread(()->conCurrentProMap.readData());
		t1.start();
		t2.start();
	}

}
