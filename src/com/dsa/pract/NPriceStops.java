package com.dsa.pract;

public class NPriceStops implements Comparable<NPriceStops>{
	int node;
	int price;
	int stops;
	
	
	public NPriceStops(int node,int price,int stops) {
		this.node=node;
		this.price=price;
		this.stops=stops;
	}


	@Override
	public int compareTo(NPriceStops o) {
		// TODO Auto-generated method stub
		return this.price-o.price;
	}

}
