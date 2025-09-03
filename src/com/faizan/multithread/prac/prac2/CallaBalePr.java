package com.faizan.multithread.prac.prac2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallaBalePr {
	
	public void print() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		Callable<String> callable=()->"Hi";
		Future<String> submit = executorService.submit(callable);
		try {
			System.out.println(submit.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();
		
	}
	
	public static void main(String[] args) {
		CallaBalePr balePr = new CallaBalePr();
		Thread t1 = new Thread(()->balePr.print());
		t1.start();
	}

}
