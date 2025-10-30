package com.dsa.graph.interview.question.geeksforgeeks;

import java.util.Arrays;
import java.util.Comparator;

public class UnionFind {
	int[] parent;
	int[] rank;
	
	public UnionFind(int size) {
		parent = new int[size];
		rank = new int[size];
		for(int i=0;i<size;i++) {
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
	
	 public int kruskalsMST(int V, int[][] edges) {
		 
		 Arrays.sort(edges, Comparator.comparingInt(e->e[2]));
		 UnionFind find = new UnionFind(V);
		 int mstWeight=0,count=0;
		 
		 for(int[] edge:edges) {
			 int u=edge[0],v=edge[1],w=edge[2];
			 if(find.find(u)!=find.find(v)){
				 find.union(u, v);
				 mstWeight +=w;
				 count++;
				 if (count == V - 1) break;
				 
			 }
		 }
		return mstWeight;
	 }
	 
	
	public static void main(String[] args) {
		UnionFind find = new UnionFind(4);
		 int[][] edges = {
		            {0, 1, 10}, {1, 3, 15}, {2, 3, 4}, {2, 0, 6}, {0, 3, 5}
		        };
		        
		       System.out.println(find.kruskalsMST(4, edges));

	}

}
