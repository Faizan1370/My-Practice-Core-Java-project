package com.dsa.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class BinaryTreeImple {
	
	private TreeNode root;
	TreeNode prev = null;
	TreeNode head = null;
	
	public void insert(int data) {
		TreeNode newNode= new TreeNode(data);
		if(root==null) {
			root=newNode;
			return;
		}
		Queue<TreeNode> queue= new LinkedList<TreeNode>();
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
	
	public void insertRec(int data) {
		root=insertUtil(root,data);
	}
	
	public TreeNode insertUtil(TreeNode node,int data) {
		if(node ==null) {
			return new TreeNode(data);
		}
		if(node.left==null) {
			node.left=insertUtil(node.left, data);
		}
		else if(node.right==null) {
			node.right=insertUtil(node.right, data);
		}else {
			node.left=insertUtil(node.left, data);
		}
		return node;
	}
	
	
	public void preOrder() {
		preOrder(root);
	}
	
	private void preOrder(TreeNode root) {
		if(root==null) {
			return;
		}
		TreeNode current = root;
		System.out.print(current.data +" ");
		preOrder(current.left);
		preOrder(current.right);
	}
	
	public void inOrder() {
		inOrder(root);
	}
	
	private void inOrder(TreeNode root) {
		if(root==null) {
			return;
		}
		TreeNode current = root;
		inOrder(current.left);
		System.out.print(current.data +" ");
		inOrder(current.right);
	}
	
	public void postOrder() {
		postOrder(root);
	}
	
	private void postOrder(TreeNode root) {
		if(root==null) {
			return;
		}
		TreeNode current= root;
		postOrder(current.left);
		postOrder(current.right);
		System.out.print(current.data +" ");
	}
	
	public void levelOrder() {
		levelOder(root);
	}
	
	private void levelOder(TreeNode root) {
		
		if(root==null) {
			return;
		}
		Queue<TreeNode> queue= new LinkedList<TreeNode>();
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
	
	public int size() {
		
		return size(root);
	}
	
	private int size(TreeNode root) {
		if(root==null) {
			return 0;
		}
		return 1+size(root.left)+size(root.right);
	}
	
  public int height() {
		
		return height(root);
	}
	
	private int height(TreeNode root) {
		if(root==null) {
			return 0;
		}
		return 1 +Math.max(height(root.left), height(root.right));
	}
	public int maximum() {
	    return max(root);
	}
	
	public int max(TreeNode root) {
		if(root==null) {
			return Integer.MIN_VALUE;
		}
		return Math.max(root.data, Math.max(max(root.left), max(root.right)));
	}
	
	public int minimum() {
	    return min(root);
	}
	
	public int min(TreeNode root) {
		if(root==null) {
			return Integer.MAX_VALUE;
		}
		return Math.min(root.data, Math.min(min(root.left), min(root.right)));
	}
	
	public void printLevel(TreeNode root,int level) {
		if(root==null) {
			return;
		}
		if(level==1) {
			System.out.print(root.data +" ");
		}else {
			printLevel(root.left, level-1);
			printLevel(root.right, level-1);
		}
	}
	
	public void printLevelOrderTraversal() {
		int height = height();
		  for (int i = 1; i <= height; i++) {
	            printLevel(root, i);
	        }
	}
	
	public  void printLeftView() {
		if(root==null) {
			return;
		}
		Queue<TreeNode> queue= new LinkedList<TreeNode>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			int levelSize = queue.size();
			
			for(int i=0;i<levelSize;i++) {
				TreeNode current=queue.poll();
				
				if(i==0) {
					System.out.print(current.data +" ");
				}
				
				  if (current.left != null) {
		                queue.add(current.left);
		            }
		            if (current.right != null) {
		                queue.add(current.right);
		            }
			}
		}
	}
	public void printLeft() {
		printLeftview1(root);
	}
	
	public void printLeftview1(TreeNode root) {
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();
		printLeftViewUtil(root, list, 0);
		for(TreeNode nod:list) {
			System.out.print(nod.data +" ");
		}
	}
	public void printLeftViewUtil(TreeNode root,ArrayList<TreeNode> list,int level) {
		if(root==null) {
			return;
		}
		//if(list.get(level)==null) {
			//list.set(level, root);
		//}
		 // Only add the node if this level is being visited the first time
	    if (list.size() == level) { // list.size==level
	        list.add(root);
	    }
	    printLeftViewUtil(root.left, list, level+1); //if we use fiusrt left will print left view if we use right will print right view
	    printLeftViewUtil(root.right, list, level+1);
		
		
	}
	
	public void printTopView() {
		if(root==null) {
			return;
		}
		Queue<QueueObj> queu= new LinkedList<QueueObj>();
		Map<Integer, Integer> topViewMap = new TreeMap();
		
		queu.add(new QueueObj(root, 0));
		
		while(!queu.isEmpty()) {
			QueueObj temp = queu.poll();
			int hd = temp.hd;
			TreeNode node = temp.node;
			if(!topViewMap.containsKey(hd)) {
				topViewMap.put(hd, node.data);
			}
			if(node.left!=null) {
				queu.add(new QueueObj(node.left, hd-1));
			}
			if(node.right!=null) {
				queu.add(new QueueObj(node.right, hd+1));
			}
		}
	       System.out.print("Top View: ");
	        for (int val : topViewMap.values()) {
	            System.out.print(val + " ");
	        }
	        System.out.println();
		
	}
	
	public void printBottomView() {
        if (root == null) return;

        Queue<QueueObj> queue = new LinkedList<>();
        Map<Integer, Integer> bottomViewMap = new TreeMap<>();

        queue.add(new QueueObj(root, 0));

        while (!queue.isEmpty()) {
            QueueObj temp = queue.poll();
            int hd = temp.hd;
            TreeNode node = temp.node;

            // Overwrite each time (last node seen at HD will be bottom view)
            bottomViewMap.put(hd, node.data);

            if (node.left != null) queue.add(new QueueObj(node.left, hd - 1));
            if (node.right != null) queue.add(new QueueObj(node.right, hd + 1));
        }

        System.out.print("Bottom View: ");
        for (int val : bottomViewMap.values()) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
	
	public void convertToDLL() {
		convertToDoubleLinkedList(root);
		printDLL();
	}
	
	public void convertToDoubleLinkedList(TreeNode node) {
		if(node==null) {
			return;
		}
		convertToDoubleLinkedList(node.left);
		if(prev==null) {
			head=node;
		}else {
			node.left=prev;
			prev.right=node;
		}
		prev=node;
		convertToDoubleLinkedList(node.right);
	}
	
	public void printDLL() {
		TreeNode temp = head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.right;
		}
		System.out.println();
	}
	int ans=0;
	public int diameter() {
		return findDiameter(root);
		
	}
	
	public int findDiameter(TreeNode node) {
		if(node==null) {
			return 0;
		}
		int lh=findDiameter(node.left);
		int rh=findDiameter(node.right);
		ans=Math.max(ans, 1+lh+rh);
		
		return 1+Math.max(lh, rh);
		
	}
	
	public TreeNode lcaProb(int n1,int n2) {
		return lca(root, n1, n2);
	}
	
	public TreeNode lca(TreeNode node, int n1,int n2 ) {
		if(node==null) {
			return null;
		}
		if(node.data==n1 || node.data==n2) {
			return node;
		}
		TreeNode left = lca(node.left,n1,n2);
		TreeNode right= lca(node.right,n1,n2);
		if(left==null) {
			return right;
		}
		if(right==null) {
			return left;
		}
		return node;
	}
	
	public int burnTime(int target) {
	   return	minTime(root, target);
	}
	
	int ans1=-1;
	
	public int minTime(TreeNode node,int target) {
		Depth depth = new Depth(-1);
		burn(node, target, depth);
		return ans1;
	}
	
	public int burn(TreeNode node,int targer,Depth depth) {
		if(node==null) {
			return 0;
		}
		if(node.data==targer) {
			depth.d=1;
			return 1;
		}
		Depth ld= new Depth(-1);
		Depth rd= new Depth(-1);
		int lh =burn(node.left, targer, ld);
		int rh =burn(node.right, targer, rd);
		if(ld.d!=-1) {
			ans1=Math.max(ans1, ld.d+rh+1);
			depth.d=ld.d+1;
		}else if(rd.d!=-1) {
			ans1=Math.max(ans1, rd.d+lh+1);
			depth.d=rd.d+1;
		}
		return Math.max(lh, rh)+1;
		
	}
	
	
	public static void main(String[] args) {
		BinaryTreeImple binaryTreeImpl = new BinaryTreeImple();
		binaryTreeImpl.insert(2);
		binaryTreeImpl.insert(4);
		binaryTreeImpl.insert(6);
		binaryTreeImpl.insert(7);
		binaryTreeImpl.insert(9);
		System.out.println(binaryTreeImpl.burnTime(6)); 
		
	}

}
