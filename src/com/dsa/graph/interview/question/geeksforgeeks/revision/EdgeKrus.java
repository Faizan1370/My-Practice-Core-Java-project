package com.dsa.graph.interview.question.geeksforgeeks.revision;

public class EdgeKrus implements Comparable<EdgeKrus>{
	int u;
	int v;
	int w;
	
	public EdgeKrus(int u,int v,int w) {
		this.u=u;
	    this.v=v;
	    this.w=w;
	}

	@Override
	public int compareTo(EdgeKrus o) {
		return this.w-o.w;
	}

}
