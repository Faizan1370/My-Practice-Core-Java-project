package com.dsa.takuforward.graph;

public class DisjointSetDS {
	int[] parent ,rank;
	
	public DisjointSetDS(int n){
		parent = new int[n];
		rank = new int[n];
		
		for(int i=0;i<n;i++) {
			parent[i]=i;
		}
	}
	public int find(int x) {
		if(parent[x] !=x) {
			parent[x]=find(parent[x]);
		}
		return parent[x];
	}
	public boolean unionWithOutSideCount(int x,int y) {
		int xRoot = find(x),yRoot=find(y);
		if(xRoot==yRoot) {
			return false;
		}
		if(rank[xRoot]>rank[yRoot]) {
			parent[yRoot]=xRoot;
		}else if(rank[yRoot]>rank[xRoot]) {
			parent[xRoot]=yRoot;
		}else {
			parent[yRoot]=xRoot;
			rank[xRoot]++;
		}
		return true;
	}
	// count var outside main code
	

}
