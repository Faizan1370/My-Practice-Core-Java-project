package com.faizan.multithread.prac.prac5;

import java.security.Principal;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableProblem {
	static ExecutorService executorService = Executors.newFixedThreadPool(1);
	
	public static void show() {
		Callable<String> cal =()->"hello mbuddy";
		
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
		show();
	}

}
