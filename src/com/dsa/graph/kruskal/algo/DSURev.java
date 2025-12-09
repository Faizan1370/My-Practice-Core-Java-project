package com.dsa.graph.kruskal.algo;

public class DSURev {
	int[] parent,rank;
	int n;
	
	public DSURev(int n) {
		parent = new int[n];
		rank=new int[n];
		for(int i=0;i<n;i++) {
			parent[i]=i;
			rank[i]=0;
		}
		this.n=n;
	}
	
	public int find(int x) {
		if(parent[x]!=x) {
			parent[x]=find(parent[x]);
		}
		return parent[x];
	}
	
	public void union(int x,int y) {
		int xRoot=find(x),yRoot=find(y);
		if(rank[xRoot]>rank[yRoot]) {
			parent[yRoot]=xRoot;
		}else if(rank[xRoot]<rank[yRoot]) {
			parent[xRoot]=yRoot;
		}else {
			parent[yRoot]=xRoot;
			rank[xRoot]++;
		}
	}
	

}
