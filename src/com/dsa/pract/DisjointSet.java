package com.dsa.pract;

public class DisjointSet {
	public int[] parent;
	public int[] rank;
	public int[] size;// using size 
	
	public DisjointSet(int n) {
		parent= new int[n];
		rank = new int[n];
		size=new int[n];
		for(int i=0;i<n;i++) {
			parent[i]=i;
			size[i]=1;
		}
	}
	public int find(int x) {
		if(parent[x] !=x) {
			parent[x]=find(parent[x]);
		}
		return parent[x];
	}
	
	public void union(int x,int y) {
		int xRoot=find(x);
		int yRoot= find(y);
		if(xRoot==yRoot) {
			return;
		}
		if(rank[xRoot]>rank[yRoot]) {
			parent[yRoot]=xRoot;
		}else if(rank[xRoot]<rank[yRoot]) {
			parent[xRoot]=yRoot;
		}else {
			parent[yRoot]=xRoot;
			rank[xRoot]++;
		}
	}
	public void unionWithSize(int x,int y) {
		int xRoot=find(x);
		int yRoot= find(y);
		if(xRoot==yRoot) {
			return;
		}
		if(size[xRoot] < size[yRoot]) {
            parent[xRoot] = yRoot;
            size[yRoot] += size[xRoot];
        } else {
            parent[yRoot] = xRoot;
            size[xRoot] += size[yRoot];
        }
		
	}

}
