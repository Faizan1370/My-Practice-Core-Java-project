package com.faizan.design.pattrens.strategy.prac1;

public class ApplyStrategy {
	
	private PaymentStrategy paymentStrategy;
	
	public void setStrategy(PaymentStrategy paymentStrategy) {
		this.paymentStrategy=paymentStrategy;
	}
	
	public void processPayment() {
		paymentStrategy.doPayment();
	}

}
