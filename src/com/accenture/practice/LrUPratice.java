package com.accenture.practice;

import java.util.HashMap;
import java.util.LinkedList;

public class LrUPratice {
	private int capacity;
	
	
	HashMap<Integer, Integer> cacheMap;
	
	LinkedList<Integer> lruList;
	
	public LrUPratice(int capacity) {
		this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.lruList = new LinkedList<>();
	}
	
	public int get(int key) {
		 if (!cacheMap.containsKey(key)) {
	            return -1;
	        }
		  lruList.remove(Integer.valueOf(key));
		  
		  lruList.addFirst(key);
		  return cacheMap.get(key);
	}
	public void put(int key, int value) {
		if(cacheMap.containsKey(key)) {
			cacheMap.put(key, value);
			
			  lruList.remove(Integer.valueOf(key));
		}else {
			if(cacheMap.size()>= capacity) {
				int leastRemove= lruList.remove();
				cacheMap.remove(leastRemove);
			}
			 cacheMap.put(key, value);
		}
		lruList.addFirst(key);
	}

}
