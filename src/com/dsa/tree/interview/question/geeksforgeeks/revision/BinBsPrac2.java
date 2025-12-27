package com.dsa.tree.interview.question.geeksforgeeks.revision;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.dsa.tree.interview.question.geeksforgeeks.TreeNod;

public class BinBsPrac2 {

	public static int getMaxSum(TreeNod root) {
		IncExcPair pair = maxSum(root);
		return Math.max(pair.include, pair.exclude);
	}

	private static IncExcPair maxSum(TreeNod node) {
		if (node == null) {
			return new IncExcPair(0, 0);
		}
		IncExcPair left = maxSum(node.left);
		IncExcPair right = maxSum(node.right);
		int include = node.data + left.exclude + right.exclude;
		int exclude = Math.max(left.include, left.exclude) + Math.max(right.include, right.exclude);

		return new IncExcPair(include, exclude);
	}

	public static boolean isValidBst(TreeNod node, int min, int max) {
		if (node == null) {
			return true;
		}
		if (node.data <= min || node.data >= max) {
			return false;
		}
		return isValidBst(node.left, min, node.data) && isValidBst(node.right, node.data, max);
	}
	
	public static boolean isValidBSt1(TreeNod node,TreeNod[] prev) {
		if(node==null) {
			return true;
		}
		if(!isValidBSt1(node.left, prev)) {
			return false;
		}
		if(prev[0] !=null && node.data <=prev[0].data) {
			return false;
		}
		prev[0]=node;
		
		return isValidBSt1(node.right, prev);
	}

	public static int largetBst(TreeNod root) {
		if(root==null) {
			return 0;
		}
		if(isValidBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
			return size(root);
		}
		return Math.max(largetBst(root.left), largetBst(root.right));
	}

	private static int size(TreeNod node) {
		if (node == null) {
			return 0;
		}
		return size(node.left) + size(node.right) + 1;
	}
	
	public static void printExtreameNode(TreeNod root) {
		if(root==null) {
			return;
			
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);
		boolean leftToRight=true;
		
		while(!queue.isEmpty()) {
			int size=queue.size();
			for(int i=0;i<size;i++) {
				TreeNod current = queue.poll();
				if((leftToRight && i==size-1) || (!leftToRight && i==0)) {
					System.out.print(current.data +" ");
				}
				if(current.left!=null) {
					queue.add(current.left);
				}
				if(current.right!=null) {
					queue.add(current.right);
				}
			}
			leftToRight = !leftToRight;
		}
	}
	public static void nextRuight(TreeNodNextRightNode root) {
		root.nextRight =null;
		
		buildNextRight(root);
	}

	private static void buildNextRight(TreeNodNextRightNode node) {
		if(node==null) {
			return;
		}
		if(node.left !=null) {
			node.left.nextRight=node.right;
		}
		if(node.right !=null) {
			node.right.nextRight= (node.nextRight !=null)?node.nextRight.left:null;
		}
		
		buildNextRight(node.left);
		buildNextRight(node.right);
		
	}
	
	public static void builNextRightItrative(TreeNodNextRightNode root) {
		if(root==null) {
			return;
		}
		Queue<TreeNodNextRightNode> queue = new LinkedList<TreeNodNextRightNode>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TreeNodNextRightNode prev = null;
			int size = queue.size();
			
			for(int i=0;i<size;i++) {
				TreeNodNextRightNode current = queue.poll();
				if(prev !=null) {
					prev.nextRight=current;
				}
				prev=current;
				if(current.left!=null) {
					queue.add(current.left);
				}
				if(current.right!=null) {
					queue.add(current.right);
				}
			}
			if(prev !=null) {
				prev.nextRight=null;
			}
		}
	}
	
	public static void printNextRights(TreeNodNextRightNode root) {
	    if (root == null) return;
	    Queue<TreeNodNextRightNode> q = new LinkedList<>();
	    q.add(root);

	    while(!q.isEmpty()) {
	        int size = q.size();
	        for(int i=0;i<size;i++) {
	            TreeNodNextRightNode node = q.poll();
	            System.out.print(node.data + "->" + (node.nextRight != null ? node.nextRight.data : "null") + "  ");
	            if(node.left != null) q.add(node.left);
	            if(node.right != null) q.add(node.right);
	        }
	        System.out.println();
	    }
	}
	  public static boolean canFinish(int n, int[][] prerequisites) {
		  ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		  for(int i=0;i<n;i++) {
			  adj.add(new ArrayList<Integer>());
		  }
		  for(int[] ed:prerequisites) {
			  adj.get(ed[0]).add(ed[1]);
		  }
		  boolean[] visited = new boolean[n];
		  boolean[] recStack = new boolean[n];
		  for(int i=0;i<n;i++) {
			  if(!visited[i]) {
				  if(dfsCyclyiRec(i,adj,visited,recStack)){
					  return true;
				  }
			  }
		  }
		return false;
		  
	   }

	private static boolean dfsCyclyiRec(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited,
			boolean[] recStack) {
		visited[v]=true;
		recStack[v]=true;
		
		for(int neg:adj.get(v)) {
			if(!visited[neg]) {
				if(dfsCyclyiRec(neg, adj, visited, recStack)) {
					return true;
				}
			}else if(recStack[neg]) {
				return true;
			}
		}
		recStack[v]=false;
		return false;
	}
	public static boolean canFinish1(int n, int[][] prerequisites) {
		  ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		  boolean[] visited = new boolean[n];
		  int[] inDeg= new int[n];
		  for(int i=0;i<n;i++) {
			  adj.add(new ArrayList<Integer>());
		  }
		  for(int[] ed:prerequisites) {
			  adj.get(ed[0]).add(ed[1]);
			  inDeg[ed[1]]++;
		  }
		  Queue<Integer> q = new LinkedList<>();
		  for(int i=0;i<n;i++) {
			  if(inDeg[i]==0) {
				  q.add(i);
			  }
		  }
		  while(!q.isEmpty()) {
			  int curr = q.poll();
			  for(int neg:adj.get(curr))
			  {	 
				  if(!visited[neg]) {
					  if(--inDeg[neg]==0) {
						  visited[neg]=true;
						  q.add(neg);
					  }
				  }
				  }
			  }
		  for(int deg:inDeg) {
			  if(deg!=0) {
				  return false;
			  }
		  }
		return true;
		
	}
	
	 public static ArrayList<Integer> scheduleTask(int n, int[][] prerequisites) {
		  ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		  ArrayList<Integer> list = new ArrayList<Integer>();
		  for(int i=0;i<n;i++) {
			  adj.add(new ArrayList<Integer>());
		  }
		  for(int[] ed:prerequisites) {
			  adj.get(ed[0]).add(ed[1]);
		  }
		  boolean[] visited = new boolean[n];
		  boolean[] recStack = new boolean[n];
		  Stack<Integer> stack = new Stack<Integer>();
		  for(int i=0;i<n;i++) {
			  if(!visited[i]) {
				  if(dfsScheduleTask(i,adj,visited,recStack,stack)){
					  return new ArrayList<Integer>();
				  }
			  }
		  }
		  while(!stack.isEmpty()) {
			  list.add(stack.pop());
		  }
		return list;
		  
	   }

	private static boolean dfsScheduleTask(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited,
			boolean[] recStack,Stack<Integer> stack) {
		visited[v]=true;
		recStack[v]=true;
		
		for(int neg:adj.get(v)) {
			if(!visited[neg]) {
				if(dfsScheduleTask(neg, adj, visited, recStack,stack)) {
					return true;
				}
			}else if(recStack[neg]) {
				return true;
			}
		}
		
		stack.push(v);
		recStack[v]=false;
		return false;
	}
	public  static boolean checkPath(ArrayList<ArrayList<Integer>> adj, int u, int v) {
		int n=adj.size();
		boolean[] visited = new boolean[n];
	  return dfscheckPath(u,v,adj,visited);
	}

	private static boolean dfscheckPath(int u, int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        if (visited[u]) return false;  
		
	   if(u==v) {
		   return true;
	   }
	   visited[u]=true;
	   for(int neg:adj.get(u)) {
		   if(!visited[neg]) {
			   if(dfscheckPath(neg, v, adj, visited)) {
				   return true;
			   }
		   }
	   }
		return false;
	}

	public static void main(String[] args) {

		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

		int V = 4;
		for (int i = 0; i < V; i++) {
		    adj.add(new ArrayList<>());
		}

		adj.get(0).add(1);
		adj.get(2).add(3);

		System.out.println(checkPath(adj, 0, 3));  // false


	}
}
