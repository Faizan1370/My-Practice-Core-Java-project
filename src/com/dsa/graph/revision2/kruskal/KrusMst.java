package com.dsa.graph.revision2.kruskal;

import java.util.ArrayList;
import java.util.Collections;

public class KrusMst {
	public int mst(int V,ArrayList<ArrayList<ArrayList<Integer>>> adj) {
		ArrayList<EdgeKr> edges= new ArrayList<EdgeKr>();
		for(int u=0;u<V;u++) {
			for(ArrayList<Integer> neg : adj.get(u)) {
				int v=neg.get(0);
				int w=neg.get(1);
				if(u<v) {
					edges.add(new EdgeKr(u, v, w));
				}
			}
		}
		Collections.sort(edges);
		DSU dsu = new DSU(V);
		int mstW=0, count=0;
		for(EdgeKr e:edges) {
			if(dsu.find(e.u)!=dsu.find(e.v)) {
				dsu.union(e.u, e.v);
				mstW+=e.w;
				count++;
				if(count==V-1) {
					break;
				}
			}
		}
		return mstW;
	}

}
