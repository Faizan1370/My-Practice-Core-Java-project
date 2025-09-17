package com.faizan.multithread.prac.prac19;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallablePrrr {
	
	public void print() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		Callable<String> cal = ()->"hi";
		
		Future<String> submit = executorService.submit(cal);
		try {
			System.out.println(submit.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();
	}
	
	public static void main(String[] args) {
		CallablePrrr callablePrrr = new CallablePrrr();
		Thread t1 = new Thread(()->callablePrrr.print());
		t1.start();
	}

}
