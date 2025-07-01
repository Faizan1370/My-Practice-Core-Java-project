package com.faizan.design.pattrens.decorator.prac2;

import com.faizan.design.pattrens.decorator.Icecream;

public class ChochoBar implements Icecream {

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 30;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Choco Bar ";
	}

}
