package com.faizan.design.pattrens.decorator.prac6;

public class ChocoChipsDeco implements IcecreamPat{
	IcecreamPat icecreamPat;
	

	public ChocoChipsDeco(IcecreamPat icecreamPat) {
		this.icecreamPat=icecreamPat;
	}

	@Override
	public int price() {
		return icecreamPat.price()+15;
	}

	@Override
	public String description() {
		return icecreamPat.description()+" Chohco Ships";
	}

}
