package com.faizan.immutable1;

public final class Person {
	
	private final String name;
	private final Address address;
	
	public Person(String name,Address address) {
		this.address= new Address(address);
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return new Address(address);
	}
	

}
