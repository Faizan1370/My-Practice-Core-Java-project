package com.dsa.graph.revision2.kruskal;


public class EdgeKr implements Comparable<EdgeKr>{
	int u;
	int v;
	int w;
	
	public EdgeKr(int u,int v,int w) {
		this.u=u;
		this.v=v;
		this.w=w;
	}

	@Override
	public int compareTo(EdgeKr o) {
		// TODO Auto-generated method stub
		return this.w-o.w;
	}

}
