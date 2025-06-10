package com.faizan.multithread.prac.prac4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableProblem {
	static ExecutorService executorService =Executors.newFixedThreadPool(1);
	
	public static void print() {
		Callable<String> cal =()->"hi";
		try {
			
			Future<String> submit = executorService.submit(cal);
			System.out.println(submit.get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName());
		executorService.shutdown();
	}
	
	public static void main(String[] args) {
		print();
	}

}
