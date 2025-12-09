package com.faizan.immutable2;

public final class Person {
	private final String name;
	private final int age;
	private final Address address;
	
	public Person(String name,int age,Address address) {
		this.name=name;
		this.age=age;
		this.address=new Address(address);
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public Address getAddress() {
		return new Address(address);
	}
	
	  public static void main(String[] args) {
	        
	        Address original = new Address("Delhi");
	        Person person = new Person("Faizan", 30, original);

	        System.out.println("Person city before change: " + person.getAddress().getCity());

	        // 1️⃣ Modify original address (constructor defensive copy test)
	        original = new Address("Mumbai");
	        System.out.println("Original address changed to Mumbai");
	        System.out.println("Person city after changing ORIGINAL: " + person.getAddress().getCity());

	        // 2️⃣ Modify address returned from getter (getter defensive copy test)
	        Address fromGetter = person.getAddress();
	        Address modified = new Address("Chennai");
	        fromGetter = modified;  // trying to replace it

	        System.out.println("Returned getter address changed to Chennai");
	        System.out.println("Person city after modifying GETTER return: " + person.getAddress().getCity());

	        // 3️⃣ Check object identity
	        System.out.println("Are two getter calls same object? " +
	                (person.getAddress() == person.getAddress())); // should print false
	    }
	
	
}
