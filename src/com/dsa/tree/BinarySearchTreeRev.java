package com.dsa.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class BinarySearchTreeRev {
	
	private TreeNode root;
	
	public void insert(int data) {
	   root =	insert(root, data);
	}
	
	private TreeNode insert(TreeNode node, int data) {
		if(node==null) {
			return new TreeNode(data);
		}
		if(data<node.data) {
			node.left=insert(node.left, data);
		}else {
			node.right=insert(node.right, data);
		}
		return node;
	}
	
	public void preOrder() {
		preOrderTraversal(root);
	}
	private void preOrderTraversal(TreeNode node) {
		if(node==null) {
			return;
		}
		TreeNode current=node;
		System.out.print(current.data +" ");
		preOrderTraversal(node.left);
		preOrderTraversal(node.right);
	}
	
	public void inOrder() {
		inOrderTraversal(root);
	}
	private void inOrderTraversal(TreeNode node) {
		if(node==null) {
			return;
		}
		TreeNode current=node;
		inOrderTraversal(current.left);
		System.out.print(current.data +" ");
		inOrderTraversal(current.right);
	}
	
	public void postOrder() {
		postOrderTraversal(root);
	}
	
	private void postOrderTraversal(TreeNode node) {
		if(node==null) {
			return;
		}
		TreeNode current=node;
		postOrderTraversal(current.left);
		postOrderTraversal(current.right);
		System.out.print(current.data +" ");
	}
	
	
	public void levelOrder() {
		if(root==null) {
			return;
		}
		Queue<TreeNode> queue= new LinkedList<TreeNode>();
		queue.add(root);
		while(!queue.isEmpty()) {
			TreeNode current= queue.poll();
			System.out.print(current.data +" ");
			if(current.left!=null) {
				queue.add(current.left);
			}
			if(current.right!=null) {
				queue.add(current.right);
			}
		}
	}
	
	public TreeNode search(int data) {
		TreeNode current=root;
		
		while(current !=null) {
			if(current.data==data) {
				return current;
			}else if(current.data>data) {
				current =current.left;
			}else {
				current =current.right;
			}
		}
		return null;
	}
	
	public int size() {
		return sizeUtil(root);
	}
	
	private int sizeUtil(TreeNode node) {
		if(node==null) {
			return 0;
		}
		return 1+sizeUtil(node.left)+sizeUtil(node.right);
	}
	
	public int height() {
		return heightUtil(root);
	}
	private int heightUtil(TreeNode node) {
		if(node==null) {
			return 0;
		}
		return 1 +Math.max(heightUtil(node.left), heightUtil(node.right));
	}
	
	public int findMin() {
		TreeNode current=root;
		while(current.left !=null) {
			current=current.left;
		}
		return current.data;
	}
	public int findMax() {
		TreeNode current=root;
		while(current.right !=null) {
			current=current.right;
		}
		return current.data;
	}
	
	public int countLeaves() {
		return countLeaves(root);
	}
	
	private int countLeaves(TreeNode node) {
		if(node==null) {
			return 0;
		}
		if(node.left==null && node.right==null) {
			return 1;
		}
		return countLeaves(node.left)+countLeaves(node.right);
	}
	
	public boolean isValidBst() {
		return isValidBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private boolean isValidBst(TreeNode node,int min,int max) {
		if(node== null) {
			return true;
		}
		if(node.data <=min || node.data >=max) {
			return false;
		}
		return isValidBst(node.left, min, node.data) && isValidBst(node.right, node.data, max);
	}
	
	
	private void insertIterative(int data) { // binary tree
		TreeNode newNode = new TreeNode(data);
		if(root==null) {
			root=newNode;
			return;
		}
		Queue<TreeNode> queue= new LinkedList<TreeNode>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TreeNode current=queue.poll();
			if(current.left==null) {
				current.left=newNode;
				return;
			}else {
				queue.add(current.left);
			}
			if(current.right ==null) {
				current.right=newNode;
				return;
			}else {
				queue.add(current.right);
			}
		}
	}
	
	public void itrativePreorder() {
		if(root==null) {
			System.out.println("no element present");
			return;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
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
	
	public void insertInBst(int value) {
		TreeNode newNode= new TreeNode(value);
		if(root==null) {
			root=newNode;
			return;
		}
		TreeNode current =root;
		TreeNode parent =null;
		
		while(current !=null) {
			parent=current;
			if(current.left!=null) {
				current=current.left;
			}else {
				current =current.right;
			}
		}
		if(value<parent.data) {
			parent.left=newNode;
		}else {
			parent.right=newNode;
		}
	}
	
	 public void itreativeInOrderTraversal() {
		 if(root==null) {
			 return;
		 }
		 Stack<TreeNode> stack = new Stack<TreeNode>();
		 TreeNode current =root;
		 while(!stack.isEmpty() || current !=null) {
			 if(current !=null) {
				 stack.push(current);
				 current =current.left;
			 }else {
				 current = stack.pop();
				 System.out.print(current.data +" ");
				 current=current.right;
			 }
		 }
	 }
	 
	 public TreeNode deleteN(int data) {
		 return deleteNode(root, data);
	 }
	 
	 public TreeNode deleteNode(TreeNode node ,int data) {
		 if(node==null) {
			 return root;
		 }
		 if(data<node.data) {
			 node.left=deleteNode(node.left, data);
		 }else if(data>node.data) {
			 node.right=deleteNode(node.right, data);
		 }else {
			 if(node.left==null) {
				 return node.right;
			 }else if(node.right==null){
				 return node.left;
			 }
			 node.data =findMin(node.right);
			 node.right=deleteNode(node.right, node.data);
		 }
		return node;
	 }
	 
	 public int findMin(TreeNode node) {
		 while(node.left!=null) {
			 node=node.left;
		 }
		 return node.data;
	 }
	 public boolean isBST() {
		 return isBST(root);
	 }
	 
	 TreeNode prev=null;
	 public boolean isBST(TreeNode node) {
		 if(node !=null) {
			 if(!isBST(node.left)) {
				 return false;
			 }
			 if(prev !=null && node.data<=prev.data) {
				 return false;
			 }
			 prev=node;
			 
			 return isBST(node.right);
		 }
		return true;
	 }
	
	 
	 public int floor(int key) {
		 int ans=Integer.MAX_VALUE;
		 if(root==null) {
			 return ans;
		 }
		 while(root!=null) {
			 if(root.data==key) {
				 return root.data;
			 }
			 if(root.data>key) {
					root=root.left;
				 }else {
					 ans=root.data;
					 root=root.right;
				 } 
		 }
		 
		return ans;
	 }
	 
	 public int ciel(int key) {
		 int ans=Integer.MIN_VALUE;
		 if(root==null) {
			 return ans;
		 }
		 while(root!=null) {
			 if(root.data==key) {
				 return root.data;
			 }
			 if(root.data>key) {
				   ans=root.data;
					root=root.left;
				 }else {
					 
					 root=root.right;
				 } 
		 }
		 
		return ans;
	 }
	 
	 public boolean twoSum(int target) {
		 HashSet<Integer> set = new HashSet<Integer>();
		 return twoSumUtil(root, target, set);
	 }
	 
	 private boolean twoSumUtil(TreeNode node,int sum,HashSet<Integer> set) {
		 if(node==null) {
			 return false;
		 }
		 if(twoSumUtil(node.left, sum, set)==true) {
			 return true;
		 }
		 if(set.contains(sum-node.data)) {
			 return true;
		 }
		 set.add(node.data);
		 return twoSumUtil(node.right, sum, set);
	 }
	 
	 public ArrayList<Integer> verticalOrder(){
		Queue<QueueObj> queue = new LinkedList<QueueObj>();
		Map<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		
		queue.add(new QueueObj(root, 0));
		
		while(!queue.isEmpty()) {
			QueueObj current = queue.poll();
			if(map.containsKey(current.hd)) {
				map.get(current.hd).add(current.node.data);
			}else {
				 ArrayList<Integer> temp = new ArrayList<Integer>();
				 temp.add(current.node.data);
				 map.put(current.hd, temp);
			}
			if(current.node.left !=null) {
				queue.add(new QueueObj(current.node.left, current.hd-1));
			}
			
			if(current.node.right !=null) {
				queue.add(new QueueObj(current.node.right, current.hd+1));
			}
		}
		 ArrayList<Integer> ans = new ArrayList<Integer>();
		 for(Map.Entry<Integer, ArrayList<Integer>> entry: map.entrySet()) {
			 ans.addAll(entry.getValue());
		 }
		 return ans;
	 }
	 
	 
	 
	 
	
	
	public static void main(String[] args) {
		BinarySearchTreeRev binarySearchTreeRev = new BinarySearchTreeRev();
		binarySearchTreeRev.insert(10);
		binarySearchTreeRev.insert(8);
		binarySearchTreeRev.insert(11);
		binarySearchTreeRev.insert(7);
		binarySearchTreeRev.insert(2);
		binarySearchTreeRev.insert(4);
		binarySearchTreeRev.preOrder();
		System.out.println();
		System.out.println(binarySearchTreeRev.isBST());
		//System.out.println(binarySearchTreeRev.ciel(9));
		System.out.println(binarySearchTreeRev.twoSum(9));
		System.out.println(binarySearchTreeRev.verticalOrder());
		
	}

}
