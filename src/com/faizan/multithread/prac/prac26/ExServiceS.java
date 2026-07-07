package com.faizan.multithread.prac.prac26;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExServiceS {
	public void runTask() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		for(int i=0;i<5;i++) {
			int taskId=i;
			executorService.submit(()->{
				System.out.println("task Id"+taskId +" " +Thread.currentThread().getName());
			});
		}
		executorService.shutdown();
	}
	public static void main(String[] args) {
		ExServiceS exServiceS = new ExServiceS();
		Thread t1 = new Thread(()->exServiceS.runTask());
		t1.start();
	}

}
