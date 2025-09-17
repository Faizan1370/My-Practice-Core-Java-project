package com.dsa.tree1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class BSTRevision {
	TreeNode root;
	
	public void insert(int data) {
		root=insertUtil(root, data);
	}
	
	public TreeNode insertUtil(TreeNode node,int data) {
		if(node==null) {
			return new TreeNode(data);
		}
		if(node.data>data) {
			node.left=insertUtil(node.left, data);
		}
		else if(node.data<data) {
			node.right=insertUtil(node.right, data);
		}
		return node;
	}
	
	public void preOrder() {
	  preOrderUrtil(root);	
	}
	private void preOrderUrtil(TreeNode node) {
		if(node==null) {
			return;
		}
         TreeNode current=node;
		System.out.print(current.data +" ");
		preOrderUrtil(current.left);
		preOrderUrtil(current.right);
	}
	
	public void insertItrative(int data) {
		TreeNode newNode= new TreeNode(data);
		if(root==null) {
			root=newNode;
			return;
		}
		TreeNode current=root;
		TreeNode parent=null; 
		while(current !=null) {
			parent=current;
			if(current.data>data) {
				current=current.left;
			}else {
				current =current.right;
			}
		}
		if(parent==null) {
			parent=newNode;
		}else if(parent.data>data) {
			parent.left=newNode;
		}else {
			parent.right=newNode;	
		}
	}
	
	public void preLevelOrder() {
		if(root==null) {
			return;
		}
	Queue<TreeNode> queue= new LinkedList<TreeNode>();
	queue.add(root);
	
	while(!queue.isEmpty()) {
		TreeNode current =queue.poll();
		System.out.print(current.data +" ");
		if(current.left!=null) {
			queue.add(current.left);
		}
		if(current.right!=null) {
			queue.add(current.right);
		}
		
	}
	}
	public void preOrderItrative() {
		if(root==null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.add(root);
		
		while(!stack.isEmpty()) {
			TreeNode current =stack.pop();
			System.out.print(current.data +" ");
			if(current.right!=null) {
				stack.push(current.right);
			}
			if(current.left!=null) {
				stack.push(current.left);
			}
		}
	}
	
	public TreeNode deletNode(int data) {
		return delete(root,data);
	}
	
	public TreeNode delete(TreeNode node,int data) {
		if(node==null) {
			return node;
		}
		if(node.data>data) {
			node.left=delete(node.left, data);
		}else if(node.data<data) {
			node.right=delete(node.right, data);
		}else {
			if(node.left==null) {
				return node.right;
			}else if(node.right==null) {
				return node.left;
			}else {
				node.data=findMin(node.right);
				node.right=delete(node.right, node.data);
			}
		}
		return node;
	}
	private int findMin(TreeNode node) {
		while(node.left!=null) {
			node =node.left;
		}
		return node.data;
	}
	
	public boolean checkBst() {
		return isValidBstUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public boolean isValidBstUtil(TreeNode node,int min,int max) {
		if(node==null) {
			return true;
		}
		if(node.data<=min || node.data>=max) {
			return false;
		}
		
		return (isValidBstUtil(node.left, min, node.data) && isValidBstUtil(node.right, node.data, max));
	}
	
	public boolean isValidBSTItrative() {
		if(root==null) {
			return true;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode current =root;
		Integer prev=null;
		while(!stack.isEmpty() || current !=null) {
			
			while(current!=null) {
				stack.push(current);
				current =current.left;
			}
			
			current=stack.pop();
			if(prev !=null && current.data<=prev) {
				return false;
			}
			prev=current.data;
			current =current.right;
			
			
		}
		
		return true;
	}
	
	public int findFloor(int data) {
		return floor(root, data);
	}
	
	public int floor(TreeNode node,int data) {
		int ans=Integer.MAX_VALUE;
		if(node==null) {
			return ans;
		}
		while(node!=null) {
			if(node.data==data) {
				return node.data;
			}else if(node.data>data) {
				node=node.left;
			}else {
				ans=node.data;
				node=node.right;
			}
		}
		return ans;
	}
	
	public int findCeil(int data) {
		return ceil(root, data);
	}
	
	public int ceil(TreeNode node,int data) {
		int ans=Integer.MIN_VALUE;
		if(node==null) {
			return ans;
		}
		while(node!=null) {
			if(node.data==data) {
				return node.data;
			}else if(node.data>data) {
				ans=node.data;
				node=node.left;
			}else {
				node=node.right;
			}
		}
		return ans;
	}
	
	public boolean findTwoSum(int target) {
		HashSet<Integer> set = new HashSet<Integer>();
		return twoSum(root, target, set);
	}
	
	public boolean twoSum(TreeNode node,int target,HashSet<Integer> set) {
		if(node==null) {
			return false;
		}
		if(twoSum(node.left, target, set)== true) {
			return true;
		}
		if(set.contains(target-node.data)) {
			return true;
		}
		set.add(node.data);
		return twoSum(node.right, target, set);
		
	}
	
    public void verticalTraversal(){
    	if(root==null) {
    		return;
    	}
    	Map<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
    	ArrayList<Integer> ans = new ArrayList<Integer>();
    	Queue<HdPair> queue = new LinkedList<HdPair>();
    	queue.add(new HdPair(0, root));
    	while(!queue.isEmpty()) {
    		HdPair pair = queue.poll();
    		int hd= pair.hd;
    		TreeNode current=pair.node;
    		if(map.containsKey(hd)) {
    			map.get(hd).add(current.data);
    		}else {
    			ArrayList<Integer> tmp = new ArrayList<Integer>();
    			tmp.add(current.data);
    			map.put(hd, tmp);
    		}
    		if(current.left!=null) {
    			queue.add(new HdPair(hd-1, current.left));
    		}
    		if(current.right!=null) {
    			queue.add(new HdPair(hd+1,current.right));
    		}
    	}
    	  for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
    		  ans.addAll(entry.getValue());
    	    }
    	  System.out.println(ans);
    }
	
	public static void main(String[] args) {
		BSTRevision bstRevision = new BSTRevision();
		bstRevision.insertItrative(10);
		bstRevision.insertItrative(50);
		bstRevision.insertItrative(70);
		bstRevision.insertItrative(12);
		bstRevision.insertItrative(1);
		bstRevision.insertItrative(6);
		bstRevision.preOrder();
		System.out.println();
		//bstRevision.deletNode(12);
		bstRevision.preOrderItrative();
		System.out.println();
		//System.out.println(bstRevision.isValidBSTItrative());
		System.out.println(bstRevision.findCeil(14));
		System.out.println(bstRevision.findTwoSum(200));
		bstRevision.verticalTraversal();
		
	}

}
