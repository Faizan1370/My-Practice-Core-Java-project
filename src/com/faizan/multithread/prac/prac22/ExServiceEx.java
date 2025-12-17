package com.faizan.multithread.prac.prac22;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExServiceEx {
	
	public static void runTask() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for(int i=0;i<=5;i++) {
			int taskId = i;
			executorService.submit(()->{
				System.out.println("task Id"+ taskId +"Thread name" +Thread.currentThread().getName());
			});
		}
		executorService.shutdown();
	}
	
	public static void main(String[] args) {
		runTask();
	}
	

}
