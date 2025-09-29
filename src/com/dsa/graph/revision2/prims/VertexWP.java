package com.dsa.graph.revision2.prims;

public class VertexWP implements Comparable<VertexWP>{
	int vertex;
	int weight;
	
	public VertexWP(int vertex,int weight) {
		this.vertex=vertex;
		this.weight=weight;
	}

	@Override
	public int compareTo(VertexWP other) {
		// TODO Auto-generated method stub
		return this.weight-other.weight;
	}

}
