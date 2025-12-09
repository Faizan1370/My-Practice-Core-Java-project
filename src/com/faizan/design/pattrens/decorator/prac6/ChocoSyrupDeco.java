package com.faizan.design.pattrens.decorator.prac6;

public class ChocoSyrupDeco implements IcecreamPat{

	IcecreamPat icecreamPat;
	
	public ChocoSyrupDeco(IcecreamPat icecreamPat) {
		this.icecreamPat=icecreamPat;
	}
	@Override
	public int price() {
		return icecreamPat.price()+20;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return icecreamPat.description() +" chocho syrup";
	}

}
