package com.dsa.graph.interview.question.geeksforgeeks.revision2;

public class DSUKrusKal {
	
	int[] parent;
	int[] rank;
	
	public DSUKrusKal(int n) {
		this.parent=new int[n];
		this.rank = new int[n];
		for(int i=0;i<n;i++) {
			parent[i]=i;
		}
	}
	
	public int find(int x) {
		if(parent[x]!=x) {
			parent[x]=find(parent[x]);
		}
		return parent[x];
	}
	public int find1(int x) {
		if(parent[x]==x) {
		return x;
		}
		return find1(parent[x]);
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