package com.dsa.graph.interview.question.geeksforgeeks.revision;

public class UnionFind {
	
	int[] parent;
	
	public UnionFind(int n) {
		parent = new int[n];
		for(int i=0;i<n;i++) {
			parent[i]=i;
		}
	}
	
	public int find(int x) {
		if(parent[x]==x) {
			return x;
		}
		
		return find(parent[x]);
	}
	public void union(int i,int j) {
		int iP = find(i);
		int jP= find(j);
		
		parent[iP]=jP;
	}
	
	
	public static void main(String[] args) {
		  int size = 5;
	        UnionFind uf = new UnionFind(size);
	        uf.union(1, 2);
	        uf.union(3, 4);
	        boolean inSameSet = uf.find(4) == uf.find(3);
	        System.out.println("Are 1 and 2 in the same set? " + inSameSet);
	}

}
