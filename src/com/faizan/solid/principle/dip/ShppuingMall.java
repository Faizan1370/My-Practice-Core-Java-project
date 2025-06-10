package com.faizan.solid.principle.dip;

public class ShppuingMall {
	
	private BankCard bankCard;
	
	public ShppuingMall(BankCard bankCard) {
		this.bankCard=bankCard;
	}
	
	public void purchaseSomething() {
		bankCard.doPayment();
	}
	
	public static void main(String[] args) {
		BankCard card= new DebitCard();
		ShppuingMall mall = new ShppuingMall(card);
		mall.purchaseSomething();
	}

}
