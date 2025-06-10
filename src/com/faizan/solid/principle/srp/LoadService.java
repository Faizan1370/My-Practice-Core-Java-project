package com.faizan.solid.principle.srp;

public class LoadService {
	
	public void getLoad(String loanType) {
		if(loanType.equalsIgnoreCase("homw load")) {
			System.out.println("Home load");
		}
	}

}
