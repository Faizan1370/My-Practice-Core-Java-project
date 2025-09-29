package com.dsa.graph.revision2.dijkstra;

public class VertexD implements Comparable<VertexD>{
	int vertex;
	int distance;
	
	public VertexD(int vertex,int distance) {
		this.vertex=vertex;
		this.distance=distance;
	}

	@Override
	public int compareTo(VertexD o) {
		// TODO Auto-generated method stub
		return this.distance-o.distance;
	}

}
