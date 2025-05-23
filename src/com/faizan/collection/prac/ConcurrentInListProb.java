package com.faizan.collection.prac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentInListProb {
	
	ArrayList<String> list = new ArrayList<String>();
	// CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
	 public  void data() {
		
		 list.add("hello");
		 list.add("buddy");
		 
		 for(int i=0;i<2000;i++) {
			 list.add("abc"+i);
			 try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
	 }
	 
	 public void show() {
	        while (true) {  // need to check every time list has data to iterate and we can remove sleep
	            //synchronized (list) { // can remove to show concurrent issue here
	                for (String str : list) {
	                    System.out.println(str);
	                }
	           // }

	            try {
	                Thread.sleep(500); // Wait before showing updated snapshot
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }

	            if (list.size() >= 52) break; // Exit when all data is likely added
	        }
	    }
	 
	 public static void main(String[] args) {
		ConcurrentInListProb concurrentInListProb = new ConcurrentInListProb();
		Thread t1 = new Thread(()->concurrentInListProb.data());
		Thread t2 = new Thread(()->concurrentInListProb.show());
		t1.start();
		t2.start();
	}

}
