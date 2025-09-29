package com.dsa.graph.dijkstra.algo;

public class VertexDistPair  implements Comparable<VertexDistPair> {
	
	int vertex;
	int dist;
	
	public VertexDistPair(int vertex,int dist) {
		this.vertex=vertex;
		this.dist=dist;
	}

	@Override
	public int compareTo(VertexDistPair other) {
		return this.dist-other.dist;
	}

}
