package com.dsa.graph.interview.question.geeksforgeeks.revision3;

public class PriceNodePair implements Comparable<PriceNodePair> {
	public int v;
	public int price;
	public int stops;

	public PriceNodePair(int v, int price, int stops) {
		this.v = v;
		this.price = price;
		this.stops = stops;
	}

	@Override
	public int compareTo(PriceNodePair o) {
		return this.price - o.price;
	}
}
