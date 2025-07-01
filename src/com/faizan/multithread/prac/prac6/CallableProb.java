package com.faizan.multithread.prac.prac6;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableProb {
	static ExecutorService executorService = Executors.newFixedThreadPool(1);
	
	public static void print() throws InterruptedException, ExecutionException {
		Callable<String> task=()->"hello Faizan";
		
		Future<String> submit = executorService.submit(task);
		System.out.println(submit.get());
		executorService.shutdown();
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		print();
	}

}
