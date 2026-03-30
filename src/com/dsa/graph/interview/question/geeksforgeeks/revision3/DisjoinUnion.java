package com.dsa.graph.interview.question.geeksforgeeks.revision3;

public class DisjoinUnion {
	
	public int find(int x,int[] parent) {
		if(parent[x]!=x) {
			parent[x]=find(parent[x],parent);
		}
		return parent[x];
	}
	public boolean union(int x,int y,int[] parent,int[] rank) {
		int xRoot=find(x,parent);
		int yRoot = find(y,parent);
		
		if(xRoot == yRoot) {
			return true;
		}else if(rank[xRoot]> rank[yRoot]) {
			parent[yRoot]=xRoot;
		}else if(rank[yRoot]> rank[xRoot]) {
			parent[xRoot]=yRoot;
			
		}else {
			parent[yRoot]=xRoot;
			rank[xRoot]++;
		}
		return false;
		
	}

}
