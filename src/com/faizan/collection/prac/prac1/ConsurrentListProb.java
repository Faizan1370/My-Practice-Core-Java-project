package com.faizan.collection.prac.prac1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConsurrentListProb {
	
   static List<String> list = new ArrayList<String>();
	//static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
	
	public void add() {
		//int i=0;
		for(int i=0;i<1000;i++) {
			list.add("value"+i);
			//i++;
		}
	  
	}
	
	public void print() {
		while(true) {
			for(String str:list) {
				System.out.println("print value :"+str);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	
	}
	
	public static void main(String[] args) {
		ConsurrentListProb consurrentListProb = new ConsurrentListProb();
		
		Thread t1 = new Thread(()->consurrentListProb.add());
		Thread t2 = new Thread(()->consurrentListProb.print());
		 t1.start();
		 t2.start();
	}

}
