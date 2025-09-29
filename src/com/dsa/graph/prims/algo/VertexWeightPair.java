package com.dsa.graph.prims.algo;

public class VertexWeightPair implements Comparable<VertexWeightPair> {
	int weight;
	int vertex;
	
	public VertexWeightPair(int vertex,int weight) {
		this.vertex=vertex;
		this.weight=weight;
	}

	@Override
	public int compareTo(VertexWeightPair other) {
		
		return this.weight-other.weight;
	}
	

}
