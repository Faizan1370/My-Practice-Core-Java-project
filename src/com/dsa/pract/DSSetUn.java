package com.dsa.pract;

public class DSSetUn {
	int[] parent;
	int[] rank;
	
	public DSSetUn(int n) {
		parent = new int[n];
		rank= new int[n];
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
	public boolean unionRed(int x,int y) {
		int xRoot=find(x),yRoot=find(y);
		if(xRoot==yRoot) {
			return true;
		}
		if(rank[xRoot]>rank[yRoot]) {
			parent[yRoot]=xRoot;
		}else if(rank[xRoot]<rank[yRoot]) {
			parent[xRoot]=yRoot;
		}else {
			parent[yRoot]=xRoot;
			rank[xRoot]++;
		}
		return false;
	}

}
