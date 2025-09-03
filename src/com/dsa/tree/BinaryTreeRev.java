package com.dsa.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeRev {
	
	private TreeNode root;
	
	public void insert(int data) {
		TreeNode newNode= new TreeNode(data);
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
			if(current.right==null) {
				current.right=newNode;
				return;
			}else {
				queue.add(current.right);
			}
		}
	}
	
	public void preOrder() {
		preOrder(root);
	}
	public void preOrder(TreeNode node) {
		if(node==null) {
			return;
		}
		TreeNode current=node;
		System.out.print(current.data +" ");
		preOrder(current.left);
		preOrder(current.right);
	}
	
	public void inOrder() {
		inOrder(root);
	}
	private void inOrder(TreeNode node) {
		if(node==null) {
			return;
		}
		TreeNode current=node;
		inOrder(current.left);
		System.out.print(current.data +" ");
		inOrder(current.right);
	}
	
	public void postOrder() {
		postOrder(root);
	}
	private void postOrder(TreeNode node) {
		if(node==null) {
			return;
		}
		TreeNode current=node;
		postOrder(current.left);
		postOrder(current.right);
		System.out.print(current.data +" ");
	}
	
	public void levelOrder() {
		if(root==null) {
			return;
		}
		Queue<TreeNode> queue= new LinkedList<TreeNode>();
		queue.add(root);
		while(!queue.isEmpty()) {
			TreeNode current=queue.poll();
			System.out.print(current.data +" ");
			if(current.left!=null) {
				queue.add(current.left);
			}
			if(current.right!=null) {
				queue.add(current.right);
			}
		}
	}
	
	public void levelOrder1() { // not working
		if(root==null) {
			return;
		}
		Stack<TreeNode> queue=new Stack<TreeNode>();
		queue.add(root);
		while(!queue.isEmpty()) {
			TreeNode current=queue.pop();
			System.out.print(current.data +" ");
			if(current.right!=null) {
				queue.add(current.right);
			}
			if(current.left!=null) {
				queue.add(current.left);
			}
			
		}
	}
	public int size() {
		return size(root);
	}
	private int size(TreeNode node) {
		if(node==null) {
			return 0;
		}
		return 1+size(node.left)+size(node.right);
	}
	
	public int height() {
		return height(root);
	}
	private int height(TreeNode node) {
		if(node== null) {
			return 0;
		}
		return 1 +Math.max(height(node.left), height(node.right));
	}
	
	public int maximum() {
		
		return max(root);
	}
	private int max(TreeNode node) {
		if(node==null) {
			return Integer.MIN_VALUE;
		}
		return Math.max(node.data, Math.max(max(node.left), max(node.right)));
	}
	
	public int minimum() {
		return min(root);
	}
	private int min(TreeNode node) {
		if(node==null) {
			return Integer.MAX_VALUE;
		}
		
		return Math.min(node.data, Math.min(min(node.left), min(node.right)));
	}
	
	public void levelTraversal() {
		int height=height();
		for(int i=1;i<=height;i++) {
			printLevel(root, i);
		}
	}
	public void printLevel(TreeNode node,int level) {
		if(node==null) {
			return;
		}
		if(level==1) {
			System.out.print(node.data +" ");
		}else {
			printLevel(node.left, level-1);
			printLevel(node.right, level-1);
		}
	}
	
	public void printLeftView() {
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();
		printLeftViewUtil(root, list, 0);
		for(TreeNode node:list) {
			System.out.print(node.data +" ");
		}
	}
	private void printLeftViewUtil(TreeNode node,ArrayList<TreeNode> list,int level) {
		if(node==null) {
			return;
		}
		if(list.size()==level) {
			list.add(node);
		}
		printLeftViewUtil(node.left, list, level+1);
		printLeftViewUtil(node.right, list, level+1);
	}
	
	
	public void printRightView() {
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();
		printRightViewUtil(root, list, 0);
		for(TreeNode node:list) {
			System.out.print(node.data +" ");
		}
	}
	private void printRightViewUtil(TreeNode node,ArrayList<TreeNode> list,int level) {
		if(node==null) {
			return;
		}
		if(list.size()==level) {
			list.add(node);
		}
		printRightViewUtil(node.right, list, level+1);
		printRightViewUtil(node.left, list, level+1);
	}
	
	public void printLeftView1() {
	   if(root==null) {
		   return;
	   }
	   Queue<TreeNode> queue= new LinkedList<TreeNode>();
	   queue.add(root);
	   
	   while(!queue.isEmpty()) {
		   int levelSize=queue.size();
		   
		   for(int i=0;i<levelSize;i++) {
			   TreeNode current=queue.poll();
			   if(i==0) {
				   System.out.print(current.data +" ");
			   }
			   if(current.left!=null) {
				   queue.add(current.left);
			   }
			   if(current.right!=null) {
				   queue.add(current.right);
			   }
		   }
	   }
	}
	
	public void printRightView1() {
		   if(root==null) {
			   return;
		   }
		   Queue<TreeNode> queue= new LinkedList<TreeNode>();
		   queue.add(root);
		   
		   while(!queue.isEmpty()) {
			   int levelSize=queue.size();
			   
			   for(int i=0;i<levelSize;i++) {
				   TreeNode current=queue.poll();
				   if(i==0) {
					   System.out.print(current.data +" ");
				   }
				   if(current.right!=null) {
					   queue.add(current.right);
				   }
				   if(current.left!=null) {
					   queue.add(current.left);
				   }
			   }
		   }
		}
	
	
	
	public void topView() {
		if(root==null) {
			return;
		}
		Queue<QueueObj> queue= new LinkedList<QueueObj>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		queue.add(new QueueObj(root, 0));
		
		while(!queue.isEmpty()) {
			QueueObj qobj = queue.poll();
			int hd=qobj.hd;
			TreeNode node = qobj.node;
			if(!map.containsKey(hd)) {
				map.put(hd, node.data);
			}
			if(node.left!=null) {
				queue.add(new QueueObj(node.left, hd-1));
			}
			
			if(node.right!=null) {
				queue.add(new QueueObj(node.right, hd+1));
			}
		}
		for(int data :map.values()) {
			System.out.print(data +" ");
		}
	}
	
	public void bottomView() {
		if(root==null) {
			return;
		}
		Queue<QueueObj> queue= new LinkedList<QueueObj>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		queue.add(new QueueObj(root, 0));
		
		while(!queue.isEmpty()) {
			QueueObj qobj = queue.poll();
			int hd=qobj.hd;
			TreeNode node = qobj.node;
		    map.put(hd, node.data);
			if(node.left!=null) {
				queue.add(new QueueObj(node.left, hd-1));
			}
			
			if(node.right!=null) {
				queue.add(new QueueObj(node.right, hd+1));
			}
		}
		for(int data :map.values()) {
			System.out.print(data +" ");
		}
	}
	TreeNode prev=null,head=null;
	public void convertToDLL() {
		convertDLlUtil(root);
		printDLL();
	}
	public void convertDLlUtil(TreeNode node) {
		if(node ==null) {
			return;
		}
		convertDLlUtil(node.left);
		if(prev==null) {
			head=node;
		}else {
			node.left=prev;
			prev.right=node;
		}
		prev=node;
		convertDLlUtil(node.right);
		
		
	}
	
	public void printDLL() {
		TreeNode temp = head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.right;
		}
		System.out.println();
	}
	
	public int findDiameter() {
		return diameter(root);
		}
	public int diameter(TreeNode node) {
		if(node==null) {
			return 0;
		}
		int dl=diameter(node.left);
		int dr=diameter(node.right);
		int curr=1+height(node.left) +height(node.right);
		
		return Math.max(curr, Math.max(dl, dr));
	}
	
	int ans=0;
	public int diameter1() {
		findDiameter1(root);
		return ans;
		
	}
	
	public int findDiameter1(TreeNode node) {
		if(node==null) {
			return 0;
		}
		int lh=findDiameter1(node.left);
		int rh=findDiameter1(node.right);
		ans=Math.max(ans, 1+lh+rh);
		
		return 1+Math.max(lh, rh);
		
	}
	public TreeNode lca(int n1,int n2) {
		
		return lcaProb(root, n1, n2);
	}
	
	private TreeNode lcaProb(TreeNode node ,int n1,int n2) {
		if(node==null) {
			return null;
		}
		if(node.data==n1 || node.data==n2) {
			return node;
		}
		TreeNode left=lcaProb(node.left, n1, n2);
		TreeNode right= lcaProb(node.right, n1, n2);
		
		if(left==null) {
			return right;
		}
		if(right==null) {
			return left;
		}
		return node;
	}
	public int burnTime(int target) {
		return minTime(root,target);
	}
	int ans1=-1;
	public int minTime(TreeNode node,int target) {
		Depth depth= new Depth(-1);
		burn(node, target, depth);
		return ans1;
	}
	public int burn(TreeNode node,int target,Depth depth) {
		if(node==null) {
			return 0;
		}
		if(node.data==target) {
			depth.d=1;
			return 1;
		}
		Depth ld= new Depth(-1);
		Depth rd= new Depth(-1);
		int lh = burn(node.left, target, ld);
		int rh= burn(node.right, target, rd);
		if(ld.d!=-1) {
			ans1=Math.max(ans1, ld.d+1+rh);
			depth.d= ld.d+1;
		}else if(rd.d!=-1){
			ans1= Math.max(ans1, rd.d+1+lh);
			depth.d=rd.d+1;
			
		}
		return Math.max(lh, rh)+1;
	}
	
	public static void main(String[] args) {
		BinaryTreeRev binaryTreeRev = new BinaryTreeRev();
		binaryTreeRev.insert(3);
		binaryTreeRev.insert(8);
		binaryTreeRev.insert(7);
		binaryTreeRev.insert(5);
		binaryTreeRev.insert(19);
		binaryTreeRev.levelOrder();
		System.out.println();
		//System.out.println(binaryTreeRev.lca(7, 19).data);
		System.out.println(binaryTreeRev.burnTime(5));
		//binaryTreeRev.printRightView();
		//System.out.println();
		//binaryTreeRev.printRightView1();
		//System.out.println();
		//System.out.println(binaryTreeRev.diameter1());
		//System.out.println(binaryTreeRev.findDiameter());
		//binaryTreeRev.convertToDLL();
	}

}
