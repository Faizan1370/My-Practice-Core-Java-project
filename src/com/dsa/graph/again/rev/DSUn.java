package com.dsa.graph.again.rev;

public class DSUn {
	int[] parent;
	int[] rank;
	
	public DSUn(int n) {
		rank= new int[n];
		parent= new int[n];
		for(int i=0;i<n;i++) {
			parent[i]=i;
			rank[i]=0; // can remove this line
		}
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
