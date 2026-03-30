package com.dsa.pract;

public class NdStops implements Comparable<NdStops> {
	int vertex;
	int price;
	int stops;
	
	public NdStops(int vertex,int price,int stops) {
		this.vertex=vertex;
		this.price=price;
		this.stops=stops;
	}

	@Override
	public int compareTo(NdStops o) {
		// TODO Auto-generated method stub
		return this.price-o.price;
	}

}
