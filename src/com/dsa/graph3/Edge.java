package com.dsa.graph3;

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
	public int compareTo(Edge other) {
		// TODO Auto-generated method stub
		return this.w-other.w;
	}

}
