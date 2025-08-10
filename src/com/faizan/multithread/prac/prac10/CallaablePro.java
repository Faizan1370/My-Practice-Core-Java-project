package com.faizan.multithread.prac.prac10;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallaablePro {
	ExecutorService executorService = Executors.newFixedThreadPool(1);
	
	public void print() {
		Callable<String> cal =()->{
			return "hi";
		};
		Future<String> submit = executorService.submit(cal);
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
		CallaablePro callaablePro = new CallaablePro();
		callaablePro.print();
	}

}
