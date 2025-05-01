package com.faizan.collection.prac;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentProUsingMap {
	
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<String, String>();
		//ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		map.put("gff", "fh");
		map.put("hjjj", "jh");
		
		  Thread t1 = new Thread() {
	            @Override
	            public void run() {
	                for (int i = 0; i < 50; i++) {
	                    map.put("fhg"+i, "hg"+i);
	                }
	            }
	        };

	        Thread t2 = new Thread() {
	            @Override
	            public void run() {
	                	 try {
	                         Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
	                         while (iterator.hasNext()) {
	                             Map.Entry<String, String> entry = iterator.next();
	                             System.out.println(entry.getKey() + " = " + entry.getValue());
	                             Thread.sleep(10); // Give t1 a chance to modify
	                         }
	                    }
	                catch (Exception e) {
	                    System.out.println("Exception in t2: " + e);
	                }
	            }
	        
	        };
	           

	        t1.start();
	        t2.start();
	}

}
