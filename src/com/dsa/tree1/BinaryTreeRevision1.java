package com.dsa.tree1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class BinaryTreeRevision1 {
	
	TreeNode root;
	
	public void insert(int data) {
		root=insertUtil(root, data);
	}
	
	public TreeNode insertUtil(TreeNode node,int data) { // not proper for bt
		if(node==null) {
			return new TreeNode(data);
		}
		if(node.left==null) {
			node.left=insertUtil(node.left, data);
		}
		if(node.right==null) {
			node.right=insertUtil(node.right, data);
		}
		return node;
	}
	
	public void insertItratvie(int data) {
		TreeNode newNode= new TreeNode(data);
		if(root==null) {
			root=newNode;
			return;
		}
		Queue<TreeNode> queue= new LinkedList<TreeNode>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TreeNode current =queue.poll();
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
	
	public List<Integer> leftView() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		leftView(root, list, 0);
		
		return list;
	}
	
	public void leftView(TreeNode node,ArrayList<Integer> ans,int level) {
		if(node==null) {
			return;
		}
		if(ans.size()==level) {
			ans.add(node.data);
		}
		leftView(node.left, ans, level+1);
		leftView(node.right, ans, level+1);
	}
	
	public void leftViewItrative() {
		if(root==null) {
			return;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
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
	
	public void topView() {
		if(root==null) {
			return;
		}
		Queue<HdPair> queue= new LinkedList<HdPair>();
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		queue.add(new HdPair(0, root));
		
		while(!queue.isEmpty()) {
			HdPair pair = queue.poll();
			int hd = pair.hd;
			TreeNode current=pair.node;
			if(!map.containsKey(hd)) {
				map.put(hd, current.data);
			}
			if(current.left!=null) {
				queue.add(new HdPair(hd-1, current.left));
			}
			if(current.right!=null) {
				queue.add(new HdPair(hd+1, current.right));
			}
			
		}
		for(int data:map.values()) {
			System.out.print(data +" ");
		}
	}
	
	public void preOrderItrative() {
		if(root==null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		
		while(!stack.isEmpty()) {
			TreeNode current = stack.pop();
			System.out.print(current.data +" ");
			if(current.right!=null) {
				stack.add(current.right);
			}
			if(current.left!=null) {
				stack.add(current.left);
			}
			
		}
	}
	public void levelOrder() {
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
	
	public void inOrder() {
		if(root==null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode current=root;
		
		while(!stack.isEmpty() || current!=null) {
			while(current!=null) {
				stack.push(current);
				current =current.left;
			}
			current =stack.pop();
			System.out.print(current.data +" ");
			current=current.right;
			
		}
	}
	int ans=0;
	public int findDiameter() {
		diameter(root);
		return ans;
	}
	public int diameter(TreeNode node) {
		if(node==null) {
			return 0;
		}
		int lh=diameter(node.left);
		int rh=diameter(node.right);
		ans=Math.max(ans,1+lh+rh);
		return 1+ Math.max(lh, rh);
	}
	
	TreeNode head=null;
	TreeNode prev=null;
	public void DLL() {
		convertToDLL(root);
		printDll();
	}
	public void convertToDLL(TreeNode node) {
		if(node==null) {
			return;
		}
		convertToDLL(node.left);
		if(prev==null) {
			head=node;
		}else {
			node.left=prev;
			prev.right=node;
		}
		prev=node;
		convertToDLL(node.right);
	}
	public void printDll() {
		TreeNode current=head;
		while(current!=null) {
			System.out.print(current.data +" ");
			current=current.right;
		}
	}
	public TreeNode findLCA(int n1,int n2) {
		return lca(head, n1, n2);
	}
	
	public TreeNode lca(TreeNode node,int n1,int n2) {
		if(node==null) {
			return null;
		}
		if(node.data==n1 || node.data==n2) {
			return node;
		}
		TreeNode left = lca(node.left, n1, n2);
		TreeNode right =lca(node.right, n1, n2);
		if(left==null) {
			return right;
		}
		if(right==null) {
			return left;
		}
		return node;
	}
	int burnTime=-1;
	

	public int minTime(int target) {
		Depth depth = new Depth(-1);
		burn(root,target,depth);
		return burnTime;
	}

	private int burn(TreeNode node, int target, Depth depth) {
		if(node==null) {
			return 0;
		}
		if(node.data==target) {
			depth.d=0;
			return 1;
		}
		Depth ld = new Depth(-1);
		Depth rd= new Depth(-1);
		
		int lh=burn(node.left, target, ld);
		int rh = burn(node.right, target, rd);
		if(ld.d!=-1) {
			burnTime = Math.max(burnTime, ld.d+rh+1);
			depth.d=ld.d+1;
		}else if(rd.d!=-1) {
			burnTime = Math.max(burnTime, rd.d+lh+1);
			depth.d=rd.d+1;
		}
		
		return 1+Math.max(lh, rh);
		
	}

	public static void main(String[] args) {
		BinaryTreeRevision1 binaryTreeRevision1 = new BinaryTreeRevision1();
		binaryTreeRevision1.insertItratvie(20);
		binaryTreeRevision1.insertItratvie(30);
		binaryTreeRevision1.insertItratvie(22);
		binaryTreeRevision1.insertItratvie(25);
		binaryTreeRevision1.insertItratvie(27);
		System.out.println(binaryTreeRevision1.leftView());
		binaryTreeRevision1.leftViewItrative();
		System.out.println();
		binaryTreeRevision1.topView();
		System.out.println();
		binaryTreeRevision1.preOrderItrative();
		System.out.println();
		binaryTreeRevision1.inOrder();
		System.out.println();
		System.out.println(binaryTreeRevision1.findDiameter());
	//binaryTreeRevision1.DLL();
	//System.out.println();
	//System.out.println(binaryTreeRevision1.findLCA(25, 22).data);
		System.out.println(binaryTreeRevision1.minTime(22));
	}
}
