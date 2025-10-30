package com.dsa.graph.interview.question.geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class DijktrasMinDistance {
	
	 static int[] dijkstra(int V, int[][] edges, int src) {
		 ArrayList<ArrayList<VertexDistance>> adj = new ArrayList<ArrayList<VertexDistance>>();
		 for(int i=0;i<V;i++) {
			 adj.add(new ArrayList<VertexDistance>());
		 }
		 for(int[] edge:edges) {
			 int u=edge[0],v=edge[1],d=edge[2];
			 adj.get(u).add(new VertexDistance(v, d));
			 adj.get(v).add(new VertexDistance(u, d));
		 }
		 boolean[] visited= new boolean[V];
		 int[] distance= new int[V];
		 Arrays.fill(distance, Integer.MAX_VALUE);
		 
		 PriorityQueue<VertexDistance> queue = new PriorityQueue<VertexDistance>();
		 queue.add(new VertexDistance(src, 0));
		 distance[src]=0;
		 while(!queue.isEmpty()) {
			 VertexDistance pair = queue.poll();
			 int ver= pair.vertex;
			 int dis=pair.dist;
			 if(visited[ver]) {
				 continue;
			 }
			 visited[ver]=true;
			 for(VertexDistance neg:adj.get(ver)) {
				 int negVertex=neg.vertex;
				 int negDist=neg.dist;
				 if((distance[ver]+negDist)<distance[negVertex]) {
					 distance[negVertex]= distance[ver]+negDist;
					 queue.add(new VertexDistance(negVertex, distance[negVertex]));
				 }
			 }
		 }
		return distance;
	 }
	 public static void main(String[] args) {
		 int V = 5;
		int edges[][] = {{0, 1, 4}, {0, 2, 8}, {1, 4, 6}, {2, 3, 2}, {3, 4, 10}};

			int[] dist = dijkstra(V, edges, 0);
			System.out.println(Arrays.toString(dist));
	}

}
