package com.dsa.pract;

import java.util.Arrays;
import java.util.Comparator;

public class DSSet {
	int[] parent;
	int[] rank;
	
	public DSSet(int n) {
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
	public void union(int x,int y) {
		int xRoot=find(x),yRoot=find(y);
		if(rank[xRoot]>rank[yRoot]) {
			parent[yRoot]=xRoot;
		}else if(rank[yRoot]>rank[xRoot]) {
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
		}else if(rank[yRoot]>rank[xRoot]) {
			parent[xRoot]=yRoot;
		}else {
			parent[yRoot]=xRoot;
			rank[xRoot]++;
		}
		return false;
	}
	  public static int kruskalsMST(int V, int[][] edges) {
		  Arrays.sort(edges,Comparator.comparingInt(e->e[2]));
		  DSSetUn dsSetUn = new DSSetUn(V);
		  int cost=0,count=0;
		  
		  for (int[] e : edges) {
	            int x = e[0], y = e[1], w = e[2];
	            if (dsSetUn.find(x) != dsSetUn.find(y)) {
	            	dsSetUn.union(x, y);
	            	cost +=w;
	            	count++;
	            	if(count==V-1) {
	            		break;
	            	}
	            }
	            
		  }
		return cost;
	  }

}
