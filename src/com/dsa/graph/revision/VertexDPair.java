package com.dsa.graph.revision;

public class VertexDPair  implements Comparable<VertexDPair> {
	
	int vertex;
	int dist;
	
	public VertexDPair(int vertex,int dist) {
		this.vertex=vertex;
		this.dist=dist;
	}

	@Override
	public int compareTo(VertexDPair other) {
		return this.dist-other.dist;
	}

}
