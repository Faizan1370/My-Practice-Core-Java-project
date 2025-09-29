package com.dsa.graph.kruskal.algo;

public class Edge implements Comparable<Edge>{
	int u;
	int v;
	int w;
	
	public Edge(int u,int v,int w) {
		this.u=u;
		this.v=v;
		this.w=w;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.w-o.w;
	}

}
