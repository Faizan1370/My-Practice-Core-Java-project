package com.dsa.tree.again.rev;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import com.dsa.tree.Depth;
import com.dsa.tree.TreeNode;

public class BnTreeImpl {
	
	public void insert(TreeNode root, int data) {
		TreeNode newNode = new TreeNode(data);
		if(root==null) {
			root=newNode;
			return;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TreeNode current = queue.poll();
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
	public void preOrder(TreeNode root) {
		prOrderUtil(root);
	}
	private void prOrderUtil(TreeNode node) {
		if(node==null) {
			return;
		}
		System.out.print(node.data +" ");
		prOrderUtil(node.left);
		prOrderUtil(node.right);
	}
	
	public void preOrderItrat(TreeNode root) {
		if(root==null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.add(root);
		
		while(!stack.isEmpty()) {
			TreeNode current = stack.pop();
			System.out.print(current.data +" ");
			if(current.right !=null) {
				stack.add(current.right);
			}
			if(current.left !=null) {
				stack.add(current.left);
			}
		}
	}
	public void inOrder(TreeNode root) {
		inOrderUtil(root);
	}
	private void inOrderUtil(TreeNode root) {
		if(root==null) {
			return;
		}
		inOrderUtil(root.left);
		System.out.print(root.data +" ");
		inOrderUtil(root.right);
		
	}
	
	public void inorderItr(TreeNode root) {
		if(root==null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode current =root;
		
		while(!stack.isEmpty() || current !=null) {
			while(current!=null) {
				stack.push(current);
				current =current.left;
			}
			current = stack.pop();
			System.out.print(current.data +" ");
			current =current.right;
		}
	}
	public void postOrder(TreeNode root) {
		postOrderUtil(root);
	}
	private void postOrderUtil(TreeNode root) {
		if(root==null) {
			return;
		}
		postOrderUtil(root.left);
		postOrderUtil(root.right);
		System.out.print(root.data +" ");
		
	}
	public void postOrderIt(TreeNode root) {
		if(root==null) {
			return;
		}
		Stack<TreeNode> stack1 = new Stack<TreeNode>();
		Stack<TreeNode> stack2= new Stack<TreeNode>();
		stack1.push(root);
		
		while(!stack1.isEmpty()) {
			TreeNode currnt = stack1.pop();
			stack2.push(currnt);
			
			if(currnt.left!=null) {
				stack1.push(currnt.left);
			}
			if(currnt.right!=null) {
				stack1.push(currnt.right);
			}
		}
		while(!stack2.isEmpty()) {
			System.out.print(stack2.pop().data +" ");
		}
	}
	
	public ArrayList<Integer> printLeftView(TreeNode root){
		ArrayList<Integer> list = new ArrayList<Integer>();
		pritnLeftUtil(root,0,list);
		return list;
	}
	private void pritnLeftUtil(TreeNode root, int level, ArrayList<Integer> list) {
		if(root==null) {
			return;
		}
		if(list.size()==level) {
			list.add(root.data);
			return;
		}
		pritnLeftUtil(root.left, level+1, list);
		pritnLeftUtil(root.right, level+1, list);
		
	}
	
	public ArrayList<Integer> printRightView(TreeNode root){
		ArrayList<Integer> list = new ArrayList<Integer>();
		pritnRightUtil(root,0,list);
		return list;
	}
	private void pritnRightUtil(TreeNode root, int level, ArrayList<Integer> list) {
		if(root==null) {
			return;
		}
		if(list.size()==level) {
			list.add(root.data);
			return;
		}
		pritnRightUtil(root.right, level+1, list);
		pritnRightUtil(root.left, level+1, list);
		
	}
	
	public ArrayList<Integer> leftViewItrative(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(root==null) {
			return list;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while(!queue.isEmpty()) {
			int size=queue.size();
			for(int i=0;i<size;i++) {
				TreeNode curr = queue.poll();
				if(i==0) {
					list.add(curr.data);
				}
				if(curr.left!=null) {
					queue.add(curr.left);
				}
				if(curr.right!=null) {
					queue.add(curr.right);
				}
			}
		}
		return list;
	}
	public ArrayList<Integer> rightViewItrative(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(root==null) {
			return list;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while(!queue.isEmpty()) {
			int size=queue.size();
			for(int i=0;i<size;i++) {
				TreeNode curr = queue.poll();
				if(i==size-1) {
					list.add(curr.data);
				}
				if(curr.left!=null) {
					queue.add(curr.left);
				}
				if(curr.right!=null) {
					queue.add(curr.right);
				}
			}
		}
		return list;
	}
	
	public ArrayList<Integer> printTopView(TreeNode root){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(root==null) {
			return list;
		}
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		Queue<HdVPair> queue = new LinkedList<HdVPair>();
		queue.add(new HdVPair(0, root));
		
		while(!queue.isEmpty()) {
			HdVPair pair = queue.poll();
			int hd= pair.hd;
			TreeNode curr = pair.node;
			
			if(!map.containsKey(hd)) {
				map.put(hd, curr.data);
			}
			if(curr.left!=null) {
				queue.add(new HdVPair(hd-1, curr.left));
			}
			if(curr.right!=null) {
				queue.add(new HdVPair(hd+1, curr.right));
			}
		}
		for(Map.Entry<Integer, Integer> entry:map.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}
	
	public ArrayList<Integer> printBottonView(TreeNode root){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(root==null) {
			return list;
		}
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		Queue<HdVPair> queue = new LinkedList<HdVPair>();
		queue.add(new HdVPair(0, root));
		
		while(!queue.isEmpty()) {
			HdVPair pair = queue.poll();
			int hd= pair.hd;
			TreeNode curr = pair.node;
			
			//if(!map.containsKey(hd)) {
				map.put(hd, curr.data);
			//}
			if(curr.left!=null) {
				queue.add(new HdVPair(hd-1, curr.left));
			}
			if(curr.right!=null) {
				queue.add(new HdVPair(hd+1, curr.right));
			}
		}
		for(Map.Entry<Integer, Integer> entry:map.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}
	TreeNode head=null;
	TreeNode prev=null;
	public void convertDll(TreeNode root) {
		convertDllUtil(root);
	}
	private void convertDllUtil(TreeNode node) {
	 if(node==null) {
		 return;
	 }
	 convertDllUtil(node.left);
	 if(prev==null) {
		 head=node;
	 }else {
		 node.left=prev;
		 prev.right=node;
	 }
	 prev=node;
	 convertDllUtil(node.right);
		
	}
	
	public int diameter(TreeNode root) {
		int[] ans  = {0};
		diameterUtil(root,ans);
		return ans[0];
	}
	private int diameterUtil(TreeNode node, int[] ans) {
		if(node==null) {
			return 0;
		}
		int lh = diameterUtil(node.left, ans);
		int rh =diameterUtil(node.right, ans);
		ans[0]=Math.max(ans[0], 1+lh+rh);
		
		return 1+Math.max(lh, rh);
		
	}
	
	public TreeNode lca(TreeNode root,int n1,int n2) {
		return lcaUtil(root,n1,n2);
	}
	private TreeNode lcaUtil(TreeNode node, int n1, int n2) {
		if(node==null) {
			return null;
		}
		if(node.data==n1 || node.data==n2) {
			return node;
		}
		TreeNode left = lcaUtil(node.left, n1, n2);
		TreeNode right= lcaUtil(node.right, n1, n2);
		if(left==null) {
			return right;
		}
		if(right==null) {
			return left;
		}
		
		return node;
	}
	
	public int burnTime(TreeNode root,int target) {
		return minTime(root,target);
	}
	int ans1=-1;
	private int minTime(TreeNode node, int target) {
		Depth depth = new Depth(-1);
		burn(node, target, depth);
		return ans1;
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
		Depth rd = new Depth(-1);
		int lh =burn(node.left, target, ld);
		int rh =burn(node.right, target, rd);
		if(ld.d!=-1) {
			ans1= Math.max(ans1, rh+ld.d+1);
			depth.d= ld.d+1;
		}else if(rd.d != -1) { // target in right
			ans1=Math.max(ans1, rd.d+lh+1);
			depth.d=rd.d+1;
		}else {
			 depth.d = -1; // target not in this subtree
		}
		
		return  Math.max(lh, rh)+1;
		
	}



}
