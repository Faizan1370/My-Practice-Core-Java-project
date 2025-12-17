package com.faizan.multithread.prac.prac22;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableCC {
	
	public void show() {
		ExecutorService executorService =Executors.newFixedThreadPool(1);
		Callable<String> cal = ()->{
			return "hell0 cal";
		};
		Future<String> submit = executorService.submit(cal);
		System.out.println(Thread.currentThread().getName());
		try {
			System.out.println(submit.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();
	}
	public static void main(String[] args) {
		CallableCC callableCC = new CallableCC();
		Thread t1 = new Thread(()->callableCC.show());
		t1.start();
	}

}
