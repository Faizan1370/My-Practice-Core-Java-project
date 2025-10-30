package com.dsa.graph.interview.question.geeksforgeeks;
public class VWPair implements Comparable<VWPair>{
	int weight;
	int vertex;
	
	public VWPair(int vertex,int weight) {
		this.vertex=vertex;
		this.weight=weight;
	}

	@Override
	public int compareTo(VWPair other) {
		
		return this.weight-other.weight;
	}
	
}
