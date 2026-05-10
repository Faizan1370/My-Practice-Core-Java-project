package com.faizan.immutable1;

public final class Imm {
	
	private final String name;
	private final String city;
	private final Add add;
	
	public Imm(String name,String city,Add add) {
		this.city=city;
		this.name=name;
		this.add= new Add(add);
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}
	public Add getAdd() {
		return new Add(add);
	}
	
	

}
