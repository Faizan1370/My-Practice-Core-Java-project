package com.faizan.multithread.prac.prac7;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableProb {
	ExecutorService executorService = Executors.newFixedThreadPool(1);
	
	public void print() throws InterruptedException, ExecutionException {
		Callable<String> cal =()->{
			
			return "hi buddy";
		};
		Future<String> submit = executorService.submit(cal);
		System.out.println(submit.get());
		executorService.shutdown();
		
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CallableProb callableProb = new CallableProb();
		callableProb.print();
	}

}
