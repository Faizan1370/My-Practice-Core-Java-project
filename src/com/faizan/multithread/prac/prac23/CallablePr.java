package com.faizan.multithread.prac.prac23;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallablePr {
	
	
	public void show() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		Callable<String> cal = ()->"hi";
		
		Future<String> submit = executorService.submit(cal);
		try {
			System.out.println(submit.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			executorService.shutdown();
		}
	}
	public static void main(String[] args) {
		CallablePr callablePr = new CallablePr();
		Thread t1 = new Thread(()->callablePr.show());
		t1.start();
	}

}
