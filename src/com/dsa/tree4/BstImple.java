package com.dsa.tree4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class BstImple {
	TrN root;
	
	public void insert(int data) {
		root=insertUtil(root, data);
	}
	
	private TrN insertUtil(TrN node ,int data) {
		if(node==null) {
			return new TrN(data);
		}else {
			if(node.data>data) {
				node.left=insertUtil(node.left,data);
			}else if(node.data<data) {
				node.right=insertUtil(node.right,data);
			}else {
				return node;
			}
		}
		return node;
	}
	public void preOrder() {
		if(root==null) {
			return;
		}
		preOrderUtil(root);
	}

	private void preOrderUtil(TrN node) {
		if(node==null) {
			return;
		}
		System.out.print(node.data +" ");
		preOrderUtil(node.left);
		preOrderUtil(node.right);
		
	}
	public void insertIterative(int data) {
		TrN newNode = new TrN(data);
		if(root==null) {
			root=newNode;
			return;
		}
		TrN current =root;
		TrN parent =null;
		
		while(current !=null) {
			parent =current;
			if(current.data>data) {
				current=current.left;
			}else if(current.data<data) {
				current = current.right;
			}else {
				return;
			}
		}
		 if(parent.data>data) {
			parent.left=newNode;
		}else {
			parent.right=newNode;
		}
	}
	
	public void deleteNode(int key) {
		root=deleteNodeUtil(root,key);
	}
	private TrN deleteNodeUtil(TrN node, int key) {
		if(node==null) {
			return null;
		}
		if(node.data>key) {
			node.left=deleteNodeUtil(node.left, key);
		}else if(node.data<key) { 
			node.right=deleteNodeUtil(node.right, key);
		}else {
			if(node.left==null) {
				return node.right;
			}else if(node.right==null) {
				return node.left;
			}else {
			node.data =findMin(node.right);
			node.right = deleteNodeUtil(node.right, node.data);
			}
		}
		return node;
		
	}

	private int findMin(TrN node) {
		while(node.left!=null) {
			node=node.left;
		}
		return node.data;
	}
	public boolean checkBst() {
		if(root==null) {
			return true;
		}
		return isBst(root,Long.MAX_VALUE,Long.MIN_VALUE);
	}

	private boolean isBst(TrN node, long maxValue, long minValue) {
		if(node==null) {
			return true;
		}
		if(node.data <=minValue || node.data >=maxValue) {
			return false;
		}
		return isBst(node.left, node.data, minValue) && isBst(node.right, maxValue, node.data);
	}
	public boolean checkBst1() {
		if(root==null) {
			return true;
		}
		TrN[] prev = new TrN[1];
		prev[0]=null;
		return isBstUtil(root,prev);
	}

	private boolean isBstUtil(TrN node, TrN[] prev) {
		if(node==null) {
			return true;
		}
		if(!isBstUtil(node.left, prev)) {
			return false;
		}
		if(prev[0]!=null && prev[0].data>= node.data) {
			return false;
		}
		prev[0]=node;
		return isBstUtil(node.right, prev);
	}
	public int findFloor(int key) {
		int ans = -1;
		if(root==null) {
			return ans;
		}
		TrN current = root;
		while(current !=null) {
			if(current.data ==key) {
				return current.data;
			}else if(current.data>key) {
				current = current.left;
			}else {
				ans = current.data;
				current=current.right;
			}
		}
		
		return ans;
	}
	public int findCeil(int key) {
		int ans = -1;
		if(root==null) {
			return ans;
		}
		TrN current = root;
		while(current !=null) {
			if(current.data ==key) {
				return current.data;
			}else if(current.data>key) {
				ans = current.data;
				current = current.left;
			}else {
				current=current.right;
			}
		}
		
		return ans;
	}
	
	public boolean twoSum(int target) {
		if(root==null) {
			return false;
		}
		HashSet<Integer> set = new HashSet<Integer>();
		return twoSumUtil(root,target,set);
	}


	private boolean twoSumUtil(TrN node, int target, HashSet<Integer> set) {
		if(node==null) {
			return false;
		}
		if(twoSumUtil(node.left, target, set)) {
			return false;
		}
		if(set.contains(target-node.data)) {
			return true;
		}
		set.add(node.data);
		return twoSumUtil(node.right, target, set);
	}
	
	public List<Integer> verticalTraversal(){
		List<Integer> list = new ArrayList<Integer>();
		if(root==null) {
			return list;
		}
		Queue<TrNHD> queue = new LinkedList<TrNHD>();
		Map<Integer,ArrayList<Integer>> map = new TreeMap<>();
	    queue.add(new TrNHD(0, root));
	    
	    while(!queue.isEmpty()) {
	    	TrNHD pair = queue.poll();
	    	int hd = pair.hd;
	    	TrN cur =pair.node;
	    	if(map.containsKey(hd)) {
	    		map.get(hd).add(cur.data);
	    	}else {
	    		ArrayList<Integer> temp = new ArrayList<>();
	    		temp.add(cur.data);
	    		map.put(hd, temp);

	    	}
	    	if(cur.left!=null) {
	    		queue.add(new TrNHD(hd-1, cur.left));
	    	}
	    	if(cur.right!=null) {
	    		queue.add(new TrNHD(hd+1, cur.right));
	    	}
	    }
	    for(ArrayList<Integer> l :map.values()) {
	    	list.addAll(l);
	    }
		return list;
	}

	public static void main(String[] args) {
		BstImple binaryTree = new BstImple();
		binaryTree.insertIterative(5);
		binaryTree.insertIterative(7);
		binaryTree.insertIterative(3);
		binaryTree.insertIterative(10);
		binaryTree.insertIterative(2);
		binaryTree.insertIterative(9);
		System.out.println(binaryTree.findCeil(4));
		System.out.println(binaryTree.verticalTraversal());
		
	}

}
