package com.faizan.java8Prac;

import java.util.List;

public class Customer {
	
	private int id;
	private String email;
	private String name;
	private List<Integer> numbers;
	
	
	public Customer(int id, String email, String name, List<Integer> numbers) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.numbers = numbers;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getNumbers() {
		return numbers;
	}
	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", email=" + email + ", name=" + name + ", numbers=" + numbers + "]";
	}
	
	

}
