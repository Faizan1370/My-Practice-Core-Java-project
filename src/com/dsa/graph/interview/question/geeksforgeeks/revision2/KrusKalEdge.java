package com.dsa.graph.interview.question.geeksforgeeks.revision2;

public class KrusKalEdge implements Comparable<KrusKalEdge>{
	int u;
	int v;
	int w;
	
	public KrusKalEdge(int u,int v,int w) {
		this.u=u;
		this.v=v;
		this.w=w;
	}

	@Override
	public int compareTo(KrusKalEdge o) {
		// TODO Auto-generated method stub
		return this.w-o.w;
	}

}
