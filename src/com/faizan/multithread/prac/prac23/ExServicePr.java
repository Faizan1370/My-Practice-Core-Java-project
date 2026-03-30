package com.faizan.multithread.prac.prac23;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExServicePr {
	
	public void runTask() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for(int i=0;i<=5;i++) {
			int taskId = i;
			executorService.submit(()->{
				System.out.println("task Id "+ taskId +" "+Thread.currentThread().getName());
			});
		}
		executorService.shutdown();
	}
	
	public static void main(String[] args) {
		ExServicePr exServicePr = new ExServicePr();
		Thread t = new Thread(()->exServicePr.runTask());
		 t.start();
	}

}
