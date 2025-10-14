package com.faizan.immutable1;

public class Address {
	private String city;
	
	 public Address(String city) {
	        this.city = city;
	    }

	
	public Address(Address address) {
		this.city=address.city;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	

}
