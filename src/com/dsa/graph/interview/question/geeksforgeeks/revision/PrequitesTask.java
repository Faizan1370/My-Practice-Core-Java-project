package com.dsa.graph.interview.question.geeksforgeeks.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class PrequitesTask {
	public static boolean canFinish(int n, int[][] prerequisites) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<n;i++) {
			adj.add(new ArrayList<Integer>());
		}
		int[] inDegree= new int[n];
		for(int[] pr:prerequisites) {
			int dest=pr[0];
			int src=pr[1];
			adj.get(src).add(dest);
			inDegree[dest]++;
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i=0;i<n;i++) {
			if(inDegree[i]==0) {
				queue.add(i);
			}
		}
		while(!queue.isEmpty()) {
			int current = queue.poll();
			for(int neg:adj.get(current)) {
				if(--inDegree[neg]==0) {
					queue.add(neg);
				}
			}
		}
		for(int i=0;i<n;i++) {
			if(inDegree[i]!=0) {
				return false;
			}
		}
		
		return true;
		
	}
	public static int[] schedduleCourseOrTask(int numTask,int[][] prerequisites) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<numTask;i++) {
			adj.add(new ArrayList<Integer>());
		}
		for(int[] pr:prerequisites) {
			int dest=pr[0];
			int src=pr[1];
			adj.get(src).add(dest);
		}
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] recStack = new boolean[numTask];
		boolean[] visited = new boolean[numTask];
		for(int i=0;i<numTask;i++) {
			if(!visited[i]) {
				if(!dfsOfNumOfTask(i,adj,visited,recStack,stack)) {
					return new int[0];
				}
			}
		}
		int[] res = new int[numTask];
        int idx = 0;
        while (!stack.isEmpty()) res[idx++] = stack.pop();
        return res;
	}
	
	private static boolean dfsOfNumOfTask(int i, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] recStack,Stack<Integer> stack) {
		visited[i]=true;
		recStack[i]=true;
		
		for(int neg:adj.get(i)) {
			if(!visited[neg]) {
				if(!dfsOfNumOfTask(neg, adj, visited, recStack,stack)) {
					return false;
				}
			}else if(recStack[neg]){
				return false;
			}
		}
		recStack[i]=false;
		stack.push(i);
		return true;
	}
	public static List<Integer> schedduleCourseOrTask1(int V, int[][] edges) {
	    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	    for (int i = 0; i < V; i++) {
	    	adj.add(new ArrayList<>());
	    }
	    int[] inDegree= new int[V];
	    for (int[] edge : edges) {
	    	adj.get(edge[1]).add(edge[0]);
	    	inDegree[edge[0]]++;
	    }
	    Queue<Integer> queue = new LinkedList<Integer>();
	    for(int i=0;i<V;i++) {
	    	if(inDegree[i]==0) {
	    		queue.add(i);
	    	}
	    }
	    ArrayList<Integer> ans = new ArrayList<Integer>();
	    while(!queue.isEmpty()) {
	    	int current = queue.poll();
	    	ans.add(current);
	    	for(int neg:adj.get(current)) {
	    		if(--inDegree[neg]==0) {
	    			queue.add(neg);
	    		}
	    	}
	    }
	    
	    return ans.size()==V?ans :new ArrayList<Integer>();
	    
	}
	public static void main(String[] args) {
		   int numTasks = 4;
	        int[][] prerequisites
	            = { { 1, 0 }, { 2, 1 }, { 3, 2 } };
	           System.out.println(schedduleCourseOrTask1(numTasks, prerequisites)); 

    }

}
