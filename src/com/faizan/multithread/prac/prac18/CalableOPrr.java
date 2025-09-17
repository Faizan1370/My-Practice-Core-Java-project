package com.faizan.multithread.prac.prac18;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CalableOPrr {
	
	public void print() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		
		Callable<String> callable =()->"hi";
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
		CalableOPrr calableOPrr = new CalableOPrr();
		Thread t1 = new Thread(()->calableOPrr.print());
		
		t1.start();
	}

}
