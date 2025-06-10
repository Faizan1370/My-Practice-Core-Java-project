package com.faizan.design.pattrens.strategy.prac1;

public class CreditCard implements PaymentStrategy{

	@Override
	public void doPayment() {
		System.out.println("payment done by credit card");
		
	}

}
