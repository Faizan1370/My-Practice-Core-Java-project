package com.dsa.graph.dijkstra.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class DijkstraOtimizeForAnySource {
	
	public static Map<Integer, Integer> dijkstra(int V, int[][] edges, int src) {
        // Step 1: Create adjacency list
        ArrayList<ArrayList<VertexDistPair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            adj.get(u).add(new VertexDistPair(v, w));
            adj.get(v).add(new VertexDistPair(u, w));
        }

        // Step 2: Use Map instead of arrays for dist and visited
        Map<Integer, Integer> dist = new HashMap<>();
        Map<Integer, Boolean> visited = new HashMap<>();

        for (int i = 0; i < V; i++) {
            dist.put(i, Integer.MAX_VALUE);
            visited.put(i, false);
        }

        PriorityQueue<VertexDistPair> pq = new PriorityQueue<>();
        pq.add(new VertexDistPair(src, 0));
        dist.put(src, 0);

        // Step 3: Normal Dijkstra logic
        while (!pq.isEmpty()) {
        	VertexDistPair pair = pq.poll();
            int vertex = pair.vertex;

            if (visited.get(vertex)) continue;
            visited.put(vertex, true);

            for (VertexDistPair neighbor : adj.get(vertex)) {
                if (!visited.get(neighbor.vertex)) {
                    int newDist = dist.get(vertex) + neighbor.dist;
                    if (newDist < dist.get(neighbor.vertex)) {
                        dist.put(neighbor.vertex, newDist);
                        pq.add(new VertexDistPair(neighbor.vertex, newDist));
                    }
                }
            }
        }

        return dist;
    }

}
