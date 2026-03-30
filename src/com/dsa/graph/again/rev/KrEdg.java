package com.dsa.graph.again.rev;

public class KrEdg implements Comparable<KrEdg>{
	int u;
	int v;
	int w;
	
	public KrEdg(int u,int v,int w) {
		this.u=u;
		this.v=v;
		this.w=w;
	}
	
	@Override
	public int compareTo(KrEdg o) {
		
		return this.w-o.w;
	}
	
	
	

}
