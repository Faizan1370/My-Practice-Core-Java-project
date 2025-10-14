package com.dsa.graph3;

public class DisjointSetUnion {
	int[] parent,rank;
	
	public DisjointSetUnion(int V) {
		parent = new int[V];
		rank= new int[V];
		for(int i=0;i<V;i++) {
			parent[i]=i;
			rank[i]=0;
		}
	}
	public int find(int x) {
		if(parent[x]!=x) {
			parent[x]=find(parent[x]);
		}
		return parent[x];
	}
	
	public void union(int x,int y) {
		int xRoot=find(x);
		int yRoot=find(y);
		if(rank[xRoot]>rank[yRoot]) {
			rank[yRoot]=xRoot;
		}else if(rank[xRoot]<rank[yRoot]) {
			rank[xRoot]=yRoot;
		}else {
			rank[yRoot]=xRoot;
			rank[xRoot]++;
		}
	}

}
