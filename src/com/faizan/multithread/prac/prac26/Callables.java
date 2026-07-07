package com.faizan.multithread.prac.prac26;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Callables {
	
	public void print() throws InterruptedException, ExecutionException {
		Callable<String> cal =()->"hi";
		ExecutorService executorService =Executors.newFixedThreadPool(1);
		Future<String> future = executorService.submit(cal);
		String string = future.get();
		System.out.println(string);
		executorService.shutdown();
	}
	public static void main(String[] args) {
		Callables callables = new Callables();
		Thread t1 = new Thread(()->{
			try {
				callables.print();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		t1.run();
	}

}
