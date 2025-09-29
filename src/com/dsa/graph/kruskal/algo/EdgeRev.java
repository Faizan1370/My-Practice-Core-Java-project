package com.dsa.graph.kruskal.algo;

public class EdgeRev implements Comparable<EdgeRev>{
	int u,v,w;
	
	public EdgeRev(int u,int v,int w){
		this.u=u;
		this.v=v;
		this.w=w;
	}

	@Override
	public int compareTo(EdgeRev other) {
		// TODO Auto-generated method stub
		return this.w-other.w;
	}

}
