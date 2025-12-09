package com.faizan.design.pattrens.decorator.prac6;

public class TestDecoIce {
	
	public static void main(String[] args) {
		IcecreamPat icecreamPat = new Vanila();
		IcecreamPat chocoSyrupDeco = new ChocoSyrupDeco(icecreamPat);
	    System.out.println(chocoSyrupDeco.price() +" "+chocoSyrupDeco.description());
	}

}
