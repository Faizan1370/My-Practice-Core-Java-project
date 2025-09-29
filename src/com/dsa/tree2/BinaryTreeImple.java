package com.dsa.tree2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class BinaryTreeImple {
	TreeN root;

	public void insertNode(int data) {
		TreeN newNode = new TreeN(data);
		if (root == null) {
			root = newNode;
			return;
		}
		Queue<TreeN> queue = new LinkedList<TreeN>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeN current = queue.poll();
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

	public void preOrder() {
		preOrderUtil(root);
	}

	public void preOrderUtil(TreeN node) {
		if (node == null) {
			return;
		}
		System.out.print(node.data + " ");
		preOrderUtil(node.left);
		preOrderUtil(node.right);
	}

	public void preOderIntratvie() {
		if (root == null) {
			return;
		}
		Stack<TreeN> stack = new Stack<TreeN>();
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeN curr = stack.pop();
			System.out.print(curr.data + " ");
			if (curr.right != null) {
				stack.push(curr.right);
			}
			if (curr.left != null) {
				stack.push(curr.left);
			}
		}
	}

	public void inOrder() {
		inOrderUtil(root);
	}

	public void inOrderUtil(TreeN node) {
		if (node == null) {
			return;
		}
		inOrderUtil(node.left);
		System.out.print(node.data + " ");
		inOrderUtil(node.right);
	}

	public void inOderItrative() {
		if (root == null) {
			return;
		}
		Stack<TreeN> stack = new Stack<TreeN>();
		TreeN current = root;

		while (!stack.isEmpty() || current != null) {
			while (current != null) {
				stack.push(current);
				current = current.left;
			}

			current = stack.pop();
			System.out.print(current.data + " ");

			current = current.right;
		}
	}
	
	public void postOrder() {
		postOrderUtil(root);
	}
	
	public void postOrderUtil(TreeN node) {
		if(node==null) {
			return;
		}
		
		postOrderUtil(node.right);
		System.out.print(node.data +" ");
		postOrderUtil(node.left);
	}
	
	public List<Integer> leftView(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		leftViewUtil(root,list,0);
		return list;
	}

	private void leftViewUtil(TreeN node, ArrayList<Integer> list, int level) {
		if(node==null) {
			return;
		}
		if(list.size()==level) {
			list.add(node.data);
		}
		leftViewUtil(node.left, list, level+1);
		leftViewUtil(node.right, list, level+1);
	}
	
	public void leftView1(){
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		leftViewUtil1(root,map,0);
		for(Integer data :map.values()) {
			System.out.print(data +" ");
		}
	}

	private void leftViewUtil1(TreeN node, HashMap<Integer, Integer> map, int level) {
		if(node==null) {
			return;
		}
		if(!map.containsKey(level)) {
			map.put(level, node.data);
		}
		leftViewUtil1(node.left, map, level+1);
		leftViewUtil1(node.right, map, level+1);
	}
	
	public void leftViewItrative() {
		if(root==null) {
			return;
		}
		Queue<TreeN> queue = new LinkedList<TreeN>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			int levelSize=queue.size();
			for(int i=0;i<levelSize;i++) {
			TreeN current=	queue.poll();
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
	
	public List<Integer> rightView(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		rightViewUtil(root,list,0);
		return list;
	}

	private void rightViewUtil(TreeN node, ArrayList<Integer> list, int level) {
		if(node==null) {
			return;
		}
		if(list.size()==level) {
			list.add(node.data);
		}
		rightViewUtil(node.right, list, level+1);
		rightViewUtil(node.left, list, level+1);
		
	}
	
	public void rightView1(){
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		rightView1(root,map,0);
		for(Integer data :map.values()) {
			System.out.print(data +" ");
		}
	}

	private void rightView1(TreeN node, HashMap<Integer, Integer> map, int level) {
		if(node==null) {
			return;
		}
		if(!map.containsKey(level)) {
			map.put(level, node.data);
		}
		rightView1(node.right, map, level+1);
		rightView1(node.left, map, level+1);
		
	}
	
	public void rightViewItrative(){
		if(root==null) {
			return;
		}
		Queue<TreeN> queue = new LinkedList<TreeN>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			int levelSize=queue.size();
			for(int i=0;i<levelSize;i++) {
			TreeN current=	queue.poll();
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
	
	public void topView() {
		if(root==null) {
			return;
		}
		Map<Integer, Integer> map = new TreeMap<>();
		Queue<HdNodePair> queue= new LinkedList<HdNodePair>();
		queue.add(new HdNodePair(0, root));
		
		while(!queue.isEmpty()) {
			HdNodePair pair = queue.poll();
			int hd=pair.hd;
			TreeN current = pair.node;
			if(!map.containsKey(hd)) {
				map.put(hd, current.data);
			}
			if(current.left!=null) {
				queue.add(new HdNodePair(hd-1, current.left));
			}
			if(current.right!=null) {
				queue.add(new HdNodePair(hd+1, current.right));
			}
		}
		for(int data:map.values()) {
			System.out.print(data +" ");
		}
	}
	
	public void bottomView() {
		if(root==null) {
			return;
		}
		Map<Integer, Integer> map = new TreeMap<>();
		Queue<HdNodePair> queue= new LinkedList<HdNodePair>();
		queue.add(new HdNodePair(0, root));
		
		while(!queue.isEmpty()) {
			HdNodePair pair = queue.poll();
			int hd=pair.hd;
			TreeN current = pair.node;
				map.put(hd, current.data);
			if(current.left!=null) {
				queue.add(new HdNodePair(hd-1, current.left));
			}
			if(current.right!=null) {
				queue.add(new HdNodePair(hd+1, current.right));
			}
		}
		for(int data:map.values()) {
			System.out.print(data +" ");
		}
	}
	
	TreeN head=null;
	TreeN prev=null;
	public void covertDLL() {
		convertDll(root);
		printDll();
	}
	public void convertDll(TreeN node) {
		if(node==null) {
			return;
		}
		convertDll(node.left);
		if(prev==null) {
			head=node;
		}else {
			node.left=prev;
			prev.right=node;
		}
		prev=node;
		convertDll(node.right);
	}
	public void printDll() {
		TreeN current=head;
		while(current !=null) {
			System.out.print(current.data +" ");
			current=current.right;
		}
	}
	int ans=0;
	public int diameter() {
		diameter(root);
		return ans;
	}

	private int diameter(TreeN node) {
		if(node==null) {
			return 0;
		}
		int lh = diameter(node.left);
		int rh = diameter(node.right);
		ans= Math.max(ans, 1+lh+rh);
		return 1+Math.max(lh, rh);
	}
	
	public int LCA(int n1,int n2) {
		return LCA(root,n1, n2).data;
	}
	public TreeN LCA(TreeN node,int n1,int n2) {
		if(node==null) {
			return null;
		}
		if(node.data==n1 || node.data==n2) {
			return node;
		}
		TreeN left = LCA(node.left, n1, n2);
		TreeN right=LCA(node.right,n1,n2);
		if(left==null) {
			return right;
		}
		if(right==null) {
			return left;
		}
		return node;
	}
	
	public int burnTime(int target) {
		return minTime(root, target);
	}
	
	public int minTime(TreeN node, int target) {
		Depth depth= new Depth(-1);
	return	burn(node,target,depth);
	}
    int burnTime=0;
	private int burn(TreeN node, int target, Depth depth) {
		if(node==null) {
			return 0;
		}
		if(node.data==target) {
			depth.d=0;
			return 1;
		}
		Depth ld= new Depth(-1);
		Depth rd= new Depth(-1);
		
		int lh=burn(node.left, target, ld);
		int rh=burn(node.right, target, rd);
		if(ld.d!=-1) {
			burnTime=Math.max(burnTime, 1+ld.d+rh);
			depth.d=ld.d+1;
		}else {
			burnTime=Math.max(burnTime, 1+rd.d+lh);
			depth.d=rd.d+1;
		}
		
		return 1 + Math.max(lh, rh);
	}

	public static void main(String[] args) {
		BinaryTreeImple binaryTreeImple = new BinaryTreeImple();
		binaryTreeImple.insertNode(3);
		binaryTreeImple.insertNode(4);
		binaryTreeImple.insertNode(7);
		binaryTreeImple.insertNode(12);
		binaryTreeImple.insertNode(10);
		binaryTreeImple.insertNode(8);
		binaryTreeImple.inOrder();
		System.out.println();
		System.out.println(binaryTreeImple.diameter());
		System.out.println(binaryTreeImple.LCA(12, 8));
		System.out.println(binaryTreeImple.burnTime(7));
	}

}
