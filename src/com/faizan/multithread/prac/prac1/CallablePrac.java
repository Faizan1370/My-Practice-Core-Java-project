package com.faizan.multithread.prac.prac1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallablePrac {
	
	ExecutorService executorService = Executors.newFixedThreadPool(1);
	
	public void callablePrint() throws InterruptedException, ExecutionException {
		Callable<String> callable = ()->"hello";
		 Future<String> submit = executorService.submit(callable);
		 System.out.println(submit.get());
		 executorService.shutdown();
		
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CallablePrac callablePrac = new CallablePrac();
		callablePrac.callablePrint();
	}

}
