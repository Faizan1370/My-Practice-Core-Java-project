package com.faizan.multithread.prac.prac11;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableProb {
	
	
	public void print() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		Callable<String> callable =()->{
			return "hi Dear";
		};
		Future<String> submit = executorService.submit(callable);
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
		CallableProb callableProb = new CallableProb();
		callableProb.print();
	}

}
