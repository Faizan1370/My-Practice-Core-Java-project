package com.dsa.graph.interview.question.geeksforgeeks.revision;

public class MstPrimVertex implements Comparable<MstPrimVertex>{
	int vertex;
	int weight;
	
	public MstPrimVertex(int vertex,int weight) {
		this.vertex=vertex;
		this.weight=weight;
	}

	@Override
	public int compareTo(MstPrimVertex o) {
		
		return this.weight-o.weight;
	}

}
