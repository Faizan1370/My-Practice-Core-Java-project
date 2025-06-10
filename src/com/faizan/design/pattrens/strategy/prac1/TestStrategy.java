package com.faizan.design.pattrens.strategy.prac1;

public class TestStrategy {
	
	public static void main(String[] args) {
		PaymentStrategy paymentStrategy = new CreditCard();
		ApplyStrategy applyStrategy = new ApplyStrategy();
		applyStrategy.setStrategy(paymentStrategy);
		applyStrategy.processPayment();
		applyStrategy.setStrategy(new DebitCard());
		applyStrategy.processPayment();
	}

}
