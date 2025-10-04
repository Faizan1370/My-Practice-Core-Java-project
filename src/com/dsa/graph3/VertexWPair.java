package com.dsa.graph3;

public class VertexWPair implements Comparable<VertexWPair>{
	 public int v;
	public int w;
	
	public VertexWPair(int v,int w) {
		this.v=v;
		this.w=w;
	}

	@Override
	public int compareTo(VertexWPair other) {
		// TODO Auto-generated method stub
		return this.w-other.w;
	}

}
