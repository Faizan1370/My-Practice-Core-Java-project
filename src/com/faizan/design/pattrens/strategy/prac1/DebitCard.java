package com.faizan.design.pattrens.strategy.prac1;

public class DebitCard implements PaymentStrategy{

	@Override
	public void doPayment() {
		System.out.println("payment done by debit card");
		
	}

}
