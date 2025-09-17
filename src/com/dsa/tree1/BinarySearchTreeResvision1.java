package com.dsa.tree1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class BinarySearchTreeResvision1 {
	TreeNode root;

	public void insert(int data) {
		root = insertUtil(root, data);
	}

	public TreeNode insertUtil(TreeNode node, int data) {
		if (node == null) {
			return new TreeNode(data);
		}
		if (node.data > data) {
			node.left = insertUtil(node.left, data);
		} else {
			node.right = insertUtil(node.right, data);
		}
		return node;

	}

	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(TreeNode node) {
		if (node == null) {
			return;
		}
		System.out.print(node.data + " ");
		preOrder(node.left);
		preOrder(node.right);
	}

	public void inOrder() {
		inOrder(root);
	}

	private void inOrder(TreeNode node) {
		if (node == null) {
			return;
		}
		inOrder(node.left);
		System.out.print(node.data + " ");

		inOrder(node.right);
	}
	
	public void leverOrder() {
		if(root==null) {
			return;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TreeNode current = queue.poll();
			System.out.print(current.data +" ");
			
			if(current.left!=null) {
				queue.add(current.left);
			}
			if(current.right!=null) {
				queue.add(current.right);
			}
		}
	}
	
	public void insertItrative(int data) {
		TreeNode newNode = new TreeNode(data);
		if(root==null) {
			root=newNode;
			return;
		}
		TreeNode current =root;
		TreeNode parent=null;
		
		while(current !=null) {
			parent=current;
			if(current.data>data) {
				current=current.left;
			}else {
				current=current.right;
			}
		}
		if(parent==null) {
			parent=newNode;
		}else if(parent.left==null) {
			parent.left=newNode;
		}else {
			parent.left=newNode;
					
		}
		
	}
	
	public void itrativePreOrder() {
		if(root==null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while(!stack.isEmpty()) {
			TreeNode current = stack.pop();
			System.out.print(current.data +" ");
			if(current.right!=null) {
				stack.push(current.right);
				
			}
			if(current.left!=null) {
				stack.push(current.left);
			}
		}
	}
	
	public void itrativeInOrder() {
		if(root==null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode current =root;
		
		while(!stack.isEmpty() || current!=null) {
			while(current!=null) {
				stack.push(current);
				current =current.left;
			}
			current=stack.pop();
			System.out.print(current.data +" ");
			current =current.right;
		}
	}
	
	public void delete(int data) {
		deleteNode(root, data);
	}
	
	public TreeNode deleteNode(TreeNode node,int data) {
		if(node==null) {
			return null;
		}
		if(node.data>data) {
			node.left=deleteNode(node.left, data);
		}else if(node.data<data) {
			node.right=deleteNode(node.right, data);
		}else {
			if(node.left==null) {
				return node.right;
			}else if(node.right==null) {
				return node.left;
			}else {
				node.data = findMin(node.right);
				node.right= deleteNode(node.right, node.data);
			}
		}
		return node;
	}
	
	public int findMin(TreeNode node) {
		TreeNode current=node;
		while(current.left!=null) {
			current=current.left;
		}
		return current.data;
	}
	
	public boolean isBst() {
		return isBstUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private boolean isBstUtil(TreeNode node,int min,int max) {
		if(node== null) {
			return true;
		}
		if(node.data<min || node.data>max) {
			return false;
		}
		return (isBstUtil(node.left, min, node.data) && isBstUtil(node.right, node.data, max));
	}
	public int floor(int key) {
		return floor(root,key);
	}
	
	public int floor(TreeNode node, int key) {
		int ans=Integer.MAX_VALUE;
		if(node==null) {
			return ans;
		}
		while(node!=null) {
			if(node.data==key) {
				return node.data;
			}else if(node.data>key) {
				node=node.left;
			}else {
				ans = node.data;
				node=node.right;
			}
		}
		return ans;
	}
	public boolean twoSum(int target) {
		HashSet<Integer> set = new HashSet<Integer>();
		return twSumUtil(root, target, set);
	}
	
	public boolean twSumUtil(TreeNode node , int target,HashSet<Integer> set) {
		if(node==null) {
			return false;
		}
		if(twSumUtil(node.left, target, set)==true) {
			return true;
		}
		if(set.contains(target-node.data)) {
			return true;
		}
		set.add(node.data);
		
		return twSumUtil(node.right, target, set);
	}
	
	public void verticalTraverse() {
		if(root==null) {
			return;
		}
		Queue<HdPair> queue= new LinkedList<HdPair>();
		Map<Integer,ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		
		queue.add(new HdPair(0, root));
		while(!queue.isEmpty()) {
			HdPair pair = queue.poll();
			int hd= pair.hd;
			TreeNode current= pair.node;
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
				queue.add(new HdPair(hd+1, current.right));
			}
		}
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for(Map.Entry<Integer, ArrayList<Integer>> entry: map.entrySet()) {
			ans.addAll(entry.getValue());
		}
		 System.out.println(ans);
	}

	public static void main(String[] args) {
		BinarySearchTreeResvision1 binarySearchTreeResvision1 = new BinarySearchTreeResvision1();
		binarySearchTreeResvision1.insert(50);
		binarySearchTreeResvision1.insert(70);
		binarySearchTreeResvision1.insert(40);
		binarySearchTreeResvision1.insert(30);
		binarySearchTreeResvision1.insert(25);
		binarySearchTreeResvision1.insert(48);
		binarySearchTreeResvision1.insertItrative(10);
		binarySearchTreeResvision1.inOrder();
		System.out.println();
		binarySearchTreeResvision1.itrativeInOrder();
		System.out.println(binarySearchTreeResvision1.isBst());
		System.out.println(binarySearchTreeResvision1.floor(22));
		System.out.println(binarySearchTreeResvision1.twoSum(80));
		binarySearchTreeResvision1.verticalTraverse();
	}

}
