package com.faizan.multithread.prac.prac15;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallAbleProb {
	
	public void print() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		Callable<String> cal =()->{
			return "hi";
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
		CallAbleProb ableProb = new CallAbleProb();
		Thread t= new Thread(()->ableProb.print());
		t.start();
	}

}
