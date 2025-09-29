package com.dsa.graph.revision2.kruskal;

public class DSU {
	int[] parent,rank;
	
	public DSU(int n) {
		for(int i=0;i<n;i++) {
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
		if(rank[xRoot]<rank[yRoot]) {
			rank[xRoot]=yRoot;
		}else if(rank[xRoot]>rank[yRoot]) {
			rank[yRoot]=xRoot;
		}else {
			rank[yRoot]=xRoot;
			rank[xRoot]++;
			}
	}

}
