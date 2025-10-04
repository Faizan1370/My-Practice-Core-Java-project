package com.dsa.tree3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import com.dsa.tree2.TreeN;

public class BSTImpl {
	
	TreeNod root;
	
	public void insert(int data) {
		root=insert(root,data);
	}
	
	public TreeNod insert(TreeNod node,int data) {
		if(node==null) {
			return new TreeNod(data);
		}
		if(node.data>data) {
			node.left=insert(node.left, data);
		}else if(node.data<data) {
			node.right=insert(node.right, data);
		}else {
			//node.left=insert(node.left, data); // deuplicate not allowed
			return node;
		}
		return node;
	}
	
	public void insertItrative(int data) {
		TreeNod newNode= new TreeNod(data);
		if(root==null) {
			root=newNode;
			return;
		}
		TreeNod current =root;
		TreeNod parent=null;
		
		while(current !=null) {
			parent=current;
			if(current.data>data) {
				current=current.left;
			}else if(current.data<data){
				  current=current.right;
			  }else {
				  return;//avoid duplicate
			  }
		}
		if(parent==null) {
			parent=newNode;
			return;
		}else if(parent.data>data) {
			parent.left=newNode;
			return;
		}else {
			parent.right=newNode;
			return;
		}
	}
	
	public void prOrder() {
		preOrderUtil(root);
	}

	private void preOrderUtil(TreeNod node) {
		if(node==null) {
			return;
		}
		System.out.print(node.data +" ");
		preOrderUtil(node.left);
		preOrderUtil(node.right);
	}
	
	public void preOrderItrative() {
		if(root==null) {
			return;
		}
		Stack<TreeNod> stack = new Stack<TreeNod>();
		stack.push(root);
		
		while(!stack.isEmpty()) {
			TreeNod current = stack.pop();
			System.out.print(current.data +" ");
			
			if(current.right!=null) {
				stack.push(current.right);
			}
			if(current.left!=null) {
				stack.push(current.left);
			}
		}
	}
	
	public void inOrder() {
		inOrder(root);
	}
	
	public void inOrder(TreeNod node) {
		if(node==null) {
			return;
		}
		inOrder(node.left);
		System.out.print(node.data+" ");
		inOrder(node.right);
	}
	
	public void inOrderItrative() {
		if(root==null) {
			return;
		}
		Stack<TreeNod> stack = new Stack<TreeNod>();
		TreeNod current = root;
		
		while(!stack.isEmpty() || current !=null) {
			while(current!=null) {
				stack.push(current);
				current=current.left;
			}
			current =stack.pop();
			System.out.print(current.data+" ");
			current =current.right;
		}
	}
	
	public void postOrder() {
		postOrderUtil(root);
	}
	
	private void postOrderUtil(TreeNod node) {
		if(node==null) {
			return;
		}
		postOrderUtil(node.left);
		postOrderUtil(node.right);
		System.out.print(node.data+" ");
	}
	
	public void postOrderItrative() {
		if(root==null) {
			return;
		}
		Stack<TreeNod> stack1 = new Stack<TreeNod>();
		Stack<TreeNod> stack2 = new Stack<TreeNod>();
		stack1.push(root);
		
		while(!stack1.isEmpty()) {
			TreeNod current = stack1.pop();
			stack2.push(current);
			
			if(current.left!=null) {
				stack1.push(current.left);
			}
			if(current.right!=null) {
				stack1.push(current.right);
			}
		}
		
		while(!stack2.isEmpty()) {
			System.out.print(stack2.pop().data +" ");
		}
	}
	
	public void postOrderItrative1() {
		if(root==null) {
			return;
		}
		Stack<TreeNod> stack = new Stack<TreeNod>();
		TreeNod current =root;
		TreeNod lastVisited=null;
		
		while(!stack.isEmpty() || current !=null) {
			while(current!=null) {
				stack.push(current);
				current=current.left;
			}
			TreeNod peekNode = stack.peek();
			if(peekNode.right !=null && lastVisited !=peekNode.right) {
				current=peekNode.right;
			}else {
				System.out.print(peekNode.data+" ");
				lastVisited=stack.pop();
			}
		}
	}
	
	public void deleteNode(int key) {
		delete(root, key);
	}
	
	public TreeNod delete(TreeNod node,int key) {
		if(node==null) {
			return null;
		}
		
		if(node.data>key) {
			node.left=delete(node.left, key);
		}else if(node.data<key) {
			node.right=delete(node.right, key);
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

	private int findMin(TreeNod node) {
		while(node.left!=null) {
			node=node.left;
		}
		return node.data;
	}
	
	public boolean isBSt() {
		return checkBst(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}

	private boolean checkBst(TreeNod node,int min,int max) {
		if(node==null) {
			return true;
		}
		if(node.data<min || node.data>max) {
			return false;
		}
		return checkBst(node.left, min, node.data) && checkBst(node.right, node.data, max);
	}
	
	public int findFloor(int key) {
		return floor(root, key);
	}
	public int floor(TreeNod node, int key) {
		int floor=Integer.MAX_VALUE;
		if(root==null) {
			return floor;
		}
		TreeNod current= root;
		while(node!=null) {
			if(node.data ==key) {
				return node.data;
			}else if(node.data>key){
				node=node.left;
			}else {
				floor=node.data;
				node=node.right;
			}
		}
		return floor;
	}
	
	public boolean twoSum(int target) {
		HashSet<Integer> set = new HashSet<Integer>();
		return twoSumUtil(root,target,set);
	}

	private boolean twoSumUtil(TreeNod node, int target, HashSet<Integer> set) {
		if(node==null) {
			return false;
		}
		if(twoSumUtil(node.left, target, set)==true) {
			return true;
		}
		if(set.contains(target-node.data)) {
			return true;
		}
		set.add(node.data);
		return twoSumUtil(node.right, target, set);
	}
	
	public boolean twoSumIt(int target) {
		if(root==null) {
			return false;
		}
		Stack<TreeNod> stack = new Stack<TreeNod>();
		HashSet<Integer> set = new HashSet<>();
		TreeNod current=root;
		while(!stack.isEmpty() || current !=null) {
			while(current!=null) {
				stack.push(current);
				current=current.left;
			}
			current = stack.pop();
			if(set.contains(target-current.data)) {
				return true;
			}
			set.add(current.data);
			current = current.right;
			
		}
		return false;
	}
	
	public void verticalTraverse() {
		if(root==null){
			return;
		}
		Map<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		Queue<HdNPair> queue = new LinkedList<HdNPair>();
		queue.add(new HdNPair(0, root));
		while(!queue.isEmpty()) {
			HdNPair pair = queue.poll();
			int hd= pair.hd;
			TreeNod node = pair.node;
			if(map.containsKey(hd)) {
				map.get(hd).add(node.data);
			}else {
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				tmp.add(node.data);
				map.put(hd, tmp);
			}
			if(node.left !=null) {
				queue.add(new HdNPair(hd-1,node.left));
			}
			if(node.right !=null) {
				queue.add(new HdNPair(hd+1,node.right));
			}
			
			
		}
		
	}

	public static void main(String[] args) {
		BSTImpl bstImpl = new BSTImpl();
		bstImpl.insertItrative(50);
		bstImpl.insertItrative(40);
		bstImpl.insertItrative(60);
		bstImpl.insertItrative(35);
		bstImpl.insertItrative(70);
		bstImpl.insertItrative(55);
		
		System.out.println(bstImpl.isBSt());
		System.out.println(bstImpl.findFloor(38));
		System.out.println(bstImpl.twoSumIt(100));
		bstImpl.verticalTraverse();
		
	}

}
