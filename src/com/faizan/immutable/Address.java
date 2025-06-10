package com.faizan.immutable;

public class Address {
	
	
	
	private  String state;
	

	public Address(Address address) {
		address.state=state;
	}


	public String getState() {
		return state;
	}
	
	

}
