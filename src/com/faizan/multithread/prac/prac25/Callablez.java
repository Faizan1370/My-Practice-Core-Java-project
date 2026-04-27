package com.faizan.multithread.prac.prac25;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Callablez {
	ExecutorService executorService = Executors.newFixedThreadPool(1);
	
	public void show() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		Callable<String> callable =()->"hi";
		Future<String> submit = executorService.submit(callable);
		try {
			System.out.println(submit.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();
	}
	public static void main(String[] args) {
		Callablez callablez = new Callablez();
		Thread t1 = new Thread(()->callablez.show());
		t1.start();
	}

}
