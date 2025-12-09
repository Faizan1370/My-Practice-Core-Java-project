package com.faizan.multithread.prac.prac21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceEx {
	
	 public static void runTasks() {
		// ExecutorService executor = Executors.newFixedThreadPool(3);
		 ExecutorService executor = Executors.newCachedThreadPool();
        
		 for(int i=0;i<=5;i++) {
			 int taskId = i;
			 
			 executor.submit(()->{
				 System.out.println("Task " + taskId +
	                        " executed by " + Thread.currentThread().getName());
			 });
		 }
		 executor.shutdown();
	 }
	 
	 public static void main(String[] args) {
		runTasks();
	}


}
