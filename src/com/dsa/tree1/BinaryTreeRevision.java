package com.dsa.tree1;

import java.lang.module.ModuleDescriptor.Builder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class BinaryTreeRevision {
	TreeNode root;

	public void addNode(int data) {
		root = insertNode(root, data);

	}

	private TreeNode insertNode(TreeNode node, int data) {
		if (node == null) {
			return new TreeNode(data);
		}
		if (node.left == null) {
			node.left = insertNode(node.left, data);
		} else if (node.right == null) {
			node.right = insertNode(node.right, data);
		} else {
			node.left = insertNode(node.left, data);
		}
		return node;
	}

	public void preOrder() {
		preOrderUtil(root);
	}

	private void preOrderUtil(TreeNode node) {
		if (node == null) {
			return;
		}
		System.out.print(node.data + " ");
		preOrderUtil(node.left);
		preOrderUtil(node.right);
	}

	public void insertItrative(int data) {
		TreeNode newNode = new TreeNode(data);
		if (root == null) {
			root = newNode;
			return;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode current = queue.poll();
			if (current.left == null) {
				current.left = newNode;
				return;
			} else {
				queue.add(current.left);
			}
			if (current.right == null) {
				current.right = newNode;
				return;
			} else {
				queue.add(current.right);
			}
		}
	}

	public void printLeftView() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		leftViewUtil(root, list, 0);
		for (int data : list) {
			System.out.print(data + " ");
		}

	}

	private void leftViewUtil(TreeNode node, ArrayList<Integer> list, int level) {
		if (node == null) {
			return;
		}
		if (list.size() == level) {
			list.add(node.data);
		}
		leftViewUtil(node.left, list, level + 1);
		leftViewUtil(node.right, list, level + 1);
	}

	public void printLeftViewUsingMap() {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		leftViewUtilUsingMap(root, map, 0);
		for (int data : map.values()) {
			System.out.print(data + " ");
		}

	}

	private void leftViewUtilUsingMap(TreeNode node, HashMap<Integer, Integer> map, int level) {
		if (node == null) {
			return;
		}
		if (!map.containsKey(level)) {
			map.put(level, node.data);
		}
		leftViewUtilUsingMap(node.left, map, level + 1);
		leftViewUtilUsingMap(node.right, map, level + 1);
	}

	public void printRighttView() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		rightViewUtil(root, list, 0);
		for (int data : list) {
			System.out.print(data + " ");
		}

	}

	private void rightViewUtil(TreeNode node, ArrayList<Integer> list, int level) {
		if (node == null) {
			return;
		}
		if (list.size() == level) {
			list.add(node.data);
		}
		rightViewUtil(node.right, list, level + 1);
		rightViewUtil(node.left, list, level + 1);

	}

	public void topView() {
		if (root == null) {
			return;
		}
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		Queue<HdPair> queue = new LinkedList<HdPair>();
		queue.add(new HdPair(0, root));

		while (!queue.isEmpty()) {
			HdPair curr = queue.poll();
			int hd = curr.hd;
			TreeNode node = curr.node;
			if (!map.containsKey(hd)) {
				map.put(hd, node.data);
			}
			if (node.left != null) {
				queue.add(new HdPair(hd - 1, node.left));
			}
			if (node.right != null) {
				queue.add(new HdPair(hd + 1, node.right));
			}
		}
		for (int data : map.values()) {
			System.out.print(data + " ");
		}
	}

	public void bottomView() {
		if (root == null) {
			return;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		Queue<HdPair> queue = new LinkedList<HdPair>();
		queue.add(new HdPair(0, root));

		while (!queue.isEmpty()) {
			HdPair curr = queue.poll();
			int hd = curr.hd;
			TreeNode node = curr.node;
			map.put(hd, node.data);
			if (node.left != null) {
				queue.add(new HdPair(hd - 1, node.left));
			}
			if (node.right != null) {
				queue.add(new HdPair(hd + 1, node.right));
			}
		}
		for (int data : map.values()) {
			System.out.print(data + " ");
		}
	}

	TreeNode prev = null;
	TreeNode head;

	public void convertToDll() {
		convertDLLUTil(root);
		printDLL();
	}

	public void convertDLLUTil(TreeNode node) {
		if (node == null) {
			return;
		}
		convertDLLUTil(node.left);
		if (prev == null) {
			head = node;
		} else {
			node.left = prev;
			prev.right = node;
		}
		prev = node;
		convertDLLUTil(node.right);
	}

	public void printDLL() {
		TreeNode current = head;
		while (current != null) {
			System.out.print(current.data + " ");
			current = current.right;
		}

	}

	public void convertToDLL() {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode current = root;

		while (!stack.isEmpty() || current != null) {
			while (current != null) {
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();
			current = stack.pop();
			if (prev == null) {
				head = current;
			} else {
				current.left = prev;
				prev.right = current;
			}
			prev = current;

			current = current.right;
		}

	}

	int ans = 0;

	public int diameterOfBinaryTree() {
		diametter(root);
		return ans;
	}

	public int diametter(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int lh = diametter(node.left);
		int rh = diametter(node.right);
		ans = Math.max(ans, lh + rh + 1);

		return 1 + Math.max(lh, rh);

	}

	public TreeNode lca(int n1, int n2) {
		return lcaUtil(root,n1, n2);
	}

	public TreeNode lcaUtil(TreeNode node, int n1, int n2) {
		if (node == null) {
			return null;
		}
		if (node.data == n1 || node.data == n2) {
			return node;
		}
		TreeNode left = lcaUtil(node.left, n1, n2);
		TreeNode right = lcaUtil(node.right, n1, n2);
		if (left == null) {
			return right;
		}
		if (right == null) {
			return left;
		}
		return node;
	}
	int burnTime=-1;
	public int minTimeBurn(int taraget) {
		return minTime(root, taraget);
	}
	public int minTime(TreeNode node,int target) {
		RevDepth depth= new RevDepth(-1);
		burn(node,target,depth);
		return burnTime;
	}
	public int burn(TreeNode node,int target,RevDepth depth) {
		if(node==null) {
			return 0;
		}
		if(node.data==target) {
			depth.d=1;
			return 1;
		}
		RevDepth ld= new RevDepth(-1);
		RevDepth rd= new RevDepth(-1);
		
		int lh=burn(node.left, target, ld);
		int rh=burn(node.right,target,rd);
		if(ld.d!=-1) {
			burnTime= Math.max(burnTime, ld.d+rh+1);
			depth.d=ld.d+1;
		}else if(rd.d!=-1) {
			burnTime= Math.max(burnTime, rd.d+lh+1);
			depth.d=rd.d+1;
		}
		return Math.max(lh, rh)+1;
	}
	
	public void leftViewItratvie() {
		if(root==null) {
			return;
		}
		Queue<TreeNode> queue= new LinkedList<TreeNode>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			int levelSize=queue.size();
			for(int i=0;i<levelSize;i++) {
				TreeNode current = queue.poll();
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
	
	public void rightViewItratvie() {
		if(root==null) {
			return;
		}
		Queue<TreeNode> queue= new LinkedList<TreeNode>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			int levelSize=queue.size();
			for(int i=0;i<levelSize;i++) {
				TreeNode current = queue.poll();
				if(i==levelSize-1) {
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

	public static void main(String[] args) {
		BinaryTreeRevision binaryTreeRevision = new BinaryTreeRevision();
		binaryTreeRevision.insertItrative(3);
		binaryTreeRevision.insertItrative(7);
		binaryTreeRevision.insertItrative(6);
		binaryTreeRevision.insertItrative(8);
		binaryTreeRevision.insertItrative(10);
		binaryTreeRevision.insertItrative(16);
		// binaryTreeRevision.preOrder();
		// System.out.println();
		// binaryTreeRevision.printRighttView();
		System.out.println();
		// binaryTreeRevision.bottomView();
		// binaryTreeRevision.convertToDll();;
		//System.out.println(binaryTreeRevision.diameterOfBinaryTree());
		//System.out.println(binaryTreeRevision.lca(16, 6).data);
		//System.out.println(binaryTreeRevision.minTimeBurn(8));
		binaryTreeRevision.printLeftView();
		System.out.println();
		binaryTreeRevision.leftViewItratvie();
		System.out.println();
		binaryTreeRevision.rightViewItratvie();
		binaryTreeRevision.printRighttView();

	}

}
