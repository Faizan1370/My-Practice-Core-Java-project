package com.dsa.graph.interview.question.geeksforgeeks.revision2;

public class PairNodeW implements Comparable<PairNodeW>{
	int v;
	int w;
	
	
	public PairNodeW(int v,int w) {
		this.v= v;
		this.w=w;
	}


	@Override
	public int compareTo(PairNodeW o) {
		// TODO Auto-generated method stub
		return this.w-o.w;
	}

}
