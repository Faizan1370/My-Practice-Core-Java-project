package com.faizan.multithread.prac.prac25;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.faizan.multithread.prac.prac23.ExServicePr;

public class ExServicez {
	
	public void runTask() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for(int i=0;i<=5;i++) {
			int taskId=i;
			executorService.submit(()->{
				System.out.println("task Id "+ taskId +" "+Thread.currentThread().getName());
			});
			
		}
		executorService.shutdown();
	}
	public static void main(String[] args) {
		ExServicez exServicePr = new ExServicez();
		Thread t = new Thread(()->exServicePr.runTask());
		 t.start();
	}

}
