package com.faizan.multithread.prac.prac16;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallablePr {
	ExecutorService executorService =Executors.newFixedThreadPool(1);
	
	public void print() {
		Callable<String> cal =()->{
			return "Hi";
		};
		Future<String> submit = executorService.submit(cal);
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
		CallablePr callablePr = new CallablePr();
		Thread t1 = new Thread(()->callablePr.print());
		t1.start();
	}

}
