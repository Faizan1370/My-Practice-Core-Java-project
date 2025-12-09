package com.dsa.graph.interview.question.geeksforgeeks.revision;

public class VertextDiDit implements Comparable<VertextDiDit> {
	int vertex;
	int dist;

	public VertextDiDit(int vertex, int dist) {
		this.vertex = vertex;
		this.dist = dist;
	}

	@Override
	public int compareTo(VertextDiDit o) {
		return this.dist - o.dist;
	}
}
