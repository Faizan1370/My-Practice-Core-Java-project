package com.dsa.graph.interview.question.geeksforgeeks;

public class VertexDistance implements Comparable<VertexDistance>{
	int vertex;
	int dist;
	
	public VertexDistance(int vertex,int dist) {
		this.vertex=vertex;
		this.dist=dist;
	}

	@Override
	public int compareTo(VertexDistance o) {
		return this.dist-o.dist;
	}

}
