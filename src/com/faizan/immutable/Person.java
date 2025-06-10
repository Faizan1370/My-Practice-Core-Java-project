package com.faizan.immutable;

public final class Person {
	
	private final String name;
	
	private final String number;
	
	private final Address address;
	
	public Person(String name,String number,Address address) {
		this.name=name;
		this.number=number;
		this.address=new Address(address);
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public Address getAddress() {
		return new Address(address);
	}
	
	

}
