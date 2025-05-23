package com.faizan.multithread.prac.prac;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallablePrac {
	
	static ExecutorService executorService = Executors.newFixedThreadPool(1);
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
	Callable<String> cal =// new Callable<String>
			()-> {
			
			//@Override
			//public String call() throws Exception {
				
				return "Hello Bro";
			//}
		};
		Future<String> future = executorService.submit(cal);
		String string = future.get();
		System.out.println(string);
		executorService.shutdown();
	}

}
