package com.dsa.graph.again.rev;

public class VerPriceStops implements Comparable<VerPriceStops> {
	int vertex;
	int price;
	int stpos;
	
	public VerPriceStops(int vertex,int price,int stops) {
		this.vertex=vertex;
		this.price=price;
		this.stpos=stops;
	}

	@Override
	public int compareTo(VerPriceStops o) {
		// TODO Auto-generated method stub
		return this.price-o.price;
	}

}
