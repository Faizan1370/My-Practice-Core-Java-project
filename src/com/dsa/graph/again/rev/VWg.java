package com.dsa.graph.again.rev;

public class VWg implements Comparable<VWg>{
	int v;
	int w;
	
	public VWg(int v,int w) {
		this.w=w;
		this.v=v;
	}

	@Override
	public int compareTo(VWg o) {
		return this.w-o.w;
	}
	

}
