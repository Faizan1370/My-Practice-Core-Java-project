package com.dsa.tree2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import com.dsa.tree1.TreeNode;

public class BSTImpemention {
	TreeN root;
	
	
	public boolean search(int key) {
		return search(root,key);
	}
	public boolean search(TreeN node,int key) {
		if(node==null) {
			return false;
		}
		if(node.data==key) {
			return true;
		}
		if(node.data>key) {
			return search(node.left, key);
		}
		return search(node.right, key);
	}
	
	public void insert(int data) {
		root=insert(root,data);
	}
	private TreeN insert(TreeN node, int data) {
		if(node==null) {
			return new TreeN(data);
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
	
	public void insertItrative(int data) {
	  TreeN newNode = new TreeN(data);
	  if(root==null) {
		  root=newNode;
		  return;
	  }
	  TreeN current =root;
	  TreeN parent=null;
	  
	  while(current!=null) {
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
	  }
	  else if(parent.data>data) {
		  parent.left=newNode;
		  return;
	  }else {
		  parent.right=newNode;
		  return;
	  }
	}
	
	public void delete(int target) {
		delete(root,target);
	}
	
	private TreeN delete(TreeN node, int target) {
		if(node==null) {
			return null;
		}
		if(node.data>target) {
			node.left=delete(node.left, target);
		}else if(node.data<target) {
			node.right=delete(node.right, target);
		}else {
			if(node.left==null) {
				return node.right;
			}else if(node.right==null) {
				return node.left;
			}else {
				node.data= findMin(node.right);
				 node.right=delete(node.right, node.data);
			}
		}
		return node;
		
	}
	private int findMin(TreeN node) {
		while(node.left!=null) {
			node =node.left;
		}
		return node.data;
	}
	
	public boolean isBST() {
		int min=Integer.MIN_VALUE,max=Integer.MAX_VALUE;
	return	isValidBST(root,min,max);
	}
	private boolean isValidBST(TreeN node , int min, int max) {
		if(node==null) {
			return true;
		}
		if(node.data<min || node.data>max) {
			return false;
		}
		return isValidBST(node.left , min,  node.data) && isValidBST(node.right, node.data, max);
	}
	
	public boolean isBSTItrative() {
		if(root==null) {
			return true;
		}
		Stack<MInMaxISBstCheck> stack = new Stack<MInMaxISBstCheck>();
		stack.push(new MInMaxISBstCheck(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
		while(!stack.isEmpty()) {
			 MInMaxISBstCheck nb = stack.pop();
		        TreeN node = nb.node;
		        int min = nb.min;
		        int max = nb.max;

		        if (node == null) continue;

		        if (node.data < min || node.data > max) return false; // short-circuit!

		        // push children
		        stack.push(new MInMaxISBstCheck(node.right, node.data, max));
		        stack.push(new MInMaxISBstCheck(node.left, min, node.data));
		}
		return true;
	}
	
	TreeN prev=null;
	public boolean isBSTSecondAp() {
		return isBSTSecondAp(root);
	}
	private boolean isBSTSecondAp(TreeN node) {
		if(node!=null) {
			if(!isBSTSecondAp(node.left)) {
				return false;
			}
			if(prev!=null && node.data<=prev.data) {
				return false;
			}
			prev=node;
			return isBSTSecondAp(node.right);
		}
		return true;
	}
	
	public int floor(int target) {
		int ans = Integer.MIN_VALUE;
		if(root==null) {
			return 0;
		}
		TreeN current=root;
		while(current!=null) {
			if(current.data==target) {
				return current.data;
			}else if(current.data>target) {
				current= current.left;
			}else {
				ans=current.data;
				current=current.right;
			}
		}
		return ans;
	}
	
	public int ceil(int target) {
		int ans = Integer.MAX_VALUE;
		if(root==null) {
			return 0;
		}
		TreeN current=root;
		while(current!=null) {
			if(current.data==target) {
				return current.data;
			}else if(current.data>target) {
				ans=current.data;
				current= current.left;
			}else {
				
				current=current.right;
			}
		}
		return ans;
	}
	
	Integer floor = null;

	private void inorderFloor(TreeN node, int target) {
	    if(node == null) return;
	    inorderFloor(node.left, target);
	    if(node.data <= target) floor = node.data;
	    else return; // stop, because all next nodes are bigger
	    inorderFloor(node.right, target);
	}
	
	public boolean twoSum(int target) {
		HashSet<Integer> set = new HashSet<Integer>();
		return twoSumUtil(root,target,set);
	}

	private boolean twoSumUtil(TreeN node, int target, HashSet<Integer> set) {
		if(node==null) {
			return false;
		}
		if(twoSumUtil(node.left, target, set)) {
			return true;
		}
		if(set.contains(target-node.data)) {
			return true;
		}
		set.add(node.data);
		
		return twoSumUtil(node.right, target, set);
	}
	
	public void verticalTraversal() {
		if(root==null) {
			return;
		}
		Map<Integer,ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		Queue<HdNodePair> queue= new LinkedList<HdNodePair>();
		queue.add(new HdNodePair(0, root));
		
		while(!queue.isEmpty()) {
			HdNodePair pair = queue.poll();
			int hd=pair.hd;
			TreeN node = pair.node;
			if(map.containsKey(hd)) {
				map.get(hd).add(node.data);
			}else {
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				tmp.add(node.data);
				map.put(hd, tmp);
			}
			if(node.left!=null) {
				queue.add(new HdNodePair(hd-1, node.left));
			}
			if(node.right!=null) {
				queue.add(new HdNodePair(hd+1, node.right));
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(Map.Entry<Integer, ArrayList<Integer>> entry:map.entrySet()) {
			list.addAll(entry.getValue());
		}
		System.out.println(list);
		
	}
	public static void main(String[] args) {
		BSTImpemention bstImpemention = new BSTImpemention();
		bstImpemention.insertItrative(50);
		bstImpemention.insertItrative(60);
		bstImpemention.insertItrative(57);
		bstImpemention.insertItrative(45);
		bstImpemention.insertItrative(40);
		bstImpemention.insertItrative(48);
		bstImpemention.insertItrative(70);
		bstImpemention.preOrder();
		System.out.println();
		System.out.println(bstImpemention.floor(46));
		System.out.println(bstImpemention.ceil(46));
		System.out.println(bstImpemention.twoSum(100));
		bstImpemention.verticalTraversal();
		
	
	}

}
