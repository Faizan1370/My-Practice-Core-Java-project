package com.dsa.graph.revision;

public class VertexWPair implements Comparable<VertexWPair> {
	int weight;
	int vertex;
	
	public VertexWPair(int vertex,int weight) {
		this.vertex=vertex;
		this.weight=weight;
	}

	@Override
	public int compareTo(VertexWPair other) {
		
		return this.weight-other.weight;
	}
	

}
