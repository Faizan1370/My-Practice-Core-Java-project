package com.faizan.design.pattrens.decorator.prac2;




public class TestDecorator {
	
	public static void main(String[] args) {
	    Icream icream = new ChochoSyrupDecorator(new ButtreScotch());
	    System.out.println(icream.getPrice() + " "+ icream.getDiscription());
	}

}
