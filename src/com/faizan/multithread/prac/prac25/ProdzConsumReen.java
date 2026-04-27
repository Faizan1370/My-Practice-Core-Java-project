package com.faizan.multithread.prac.prac25;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.faizan.multithread.prac.prac9.DeadLokc;

public class ProdzConsumReen {

	int num = 1;
	boolean isProduce = false;
	ReentrantLock lock = new ReentrantLock();
	Condition produceCondition = lock.newCondition();
	Condition consumeCondition = lock.newCondition();

	public void produce() {
		
		while (true) {
			lock.lock();
			while (isProduce) {
				try {
					produceCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Produce Num:" + num);
			isProduce = true;
			consumeCondition.signal();
			lock.unlock();
		}
	}

	public void consume() {
		
		while (true) {
			lock.lock();
			while (!isProduce) {
				try {
					consumeCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Consume Num:" + num);
			isProduce = false;
			num++;
			produceCondition.signal();
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		ProdzConsumReen consum = new ProdzConsumReen();
		Thread t1 = new Thread(() -> consum.produce());
		Thread t2 = new Thread(() -> consum.consume());
		t1.start();
		t2.start();
	}

}
