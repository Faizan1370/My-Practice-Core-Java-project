package com.dsa.tree.revision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import com.dsa.tree.TreeNode;

public class BinaryTreeReviIml {
	TreeNodeRev root;
	
	public void insertLevelOrder(int data) {
		TreeNodeRev newNode= new TreeNodeRev(data);
		if(root==null) {
				root=newNode;
				return;
		}
		Queue<TreeNodeRev> queue = new LinkedList<TreeNodeRev>();
		queue.add(root);
		while(!queue.isEmpty()) {
			TreeNodeRev curr = queue.poll();
			if(curr.left==null) {
				curr.left=newNode;
				return;
			}else {
				queue.add(curr.left);
			}
			if(curr.right==null) {
				curr.right=newNode;
				return;
			}else {
				queue.add(curr.right);
			}
		}
	}
	
	public void inOrder() {
		inorderUtil(root);
	}

	private void inorderUtil(TreeNodeRev node) {
     		if(node==null) {
     			return;
     		}
     		inorderUtil(node.left);
     		System.out.print(node.data +" ");
     		inorderUtil(node.right);
	}
	
	public void preorder() {
		preorderUtil(root);
	}

	private void preorderUtil(TreeNodeRev node) {
     		if(node==null) {
     			return;
     		}
     		System.out.print(node.data +" ");
     		preorderUtil(node.left);
     		preorderUtil(node.right);
	}
	
	public void postOrder() {
		postOrder(root);
	}
	
	private void postOrder(TreeNodeRev node) {
		if(node==null) {
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.data +" ");
	}
	
	public void leverOrderPrint() {
		if(root==null) {
			return;
		}
		Queue<TreeNodeRev> queue = new LinkedList<TreeNodeRev>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TreeNodeRev curr = queue.poll();
			System.out.print(curr.data +" ");
			if(curr.left!=null) {
				queue.add(curr.left);
			}
			if(curr.right!=null) {
				queue.add(curr.right);
			}
		}
	}
	
	public int size() {
		return size(root);
	}
	
	
	private int size(TreeNodeRev node) {
		if(node==null) {
			return 0;
		}
		return 1+size(node.left)+size(node.right);
	}
	
	
	public int height() {
		return height(root);
	}

	private int height(TreeNodeRev node) {
		if(node==null) {
			return 0;
		}
		return 1 +Math.max(height(node.left), height(node.right));
	}
	
	
	public int max() {
		return max(root);
	}

	private int max(TreeNodeRev node) {
		if(node==null) {
			return Integer.MIN_VALUE;
		}
		return Math.max(node.data, Math.max(max(node.left), max(node.right)));
	}
	
	public int min() {
		return min(root);
	}

	private int min(TreeNodeRev node) {
		if(node==null) {
			return Integer.MAX_VALUE;
		}
		return Math.min(node.data, Math.max(min(node.left), min(node.right)));
	}
	
	public void printLevelOrdeRecursive() {
		int height=height();
		for(int i=1;i<=height;i++) {
			printLevelOrderRec(root,i);
		}
	}

	private void printLevelOrderRec(TreeNodeRev node, int level) {
		if(node==null) {
			return;
		}
		if(level==1) {
			System.out.print(node.data +" ");
		}else {
			printLevelOrderRec(node.left, level-1);
			printLevelOrderRec(node.right, level-1);
		}
	}
	public void traverseItrativePreorder() {
		if(root==null) {
			return;
		}
		Stack<TreeNodeRev> stack = new Stack<TreeNodeRev>();
		stack.add(root);
		while(!stack.isEmpty()){
			TreeNodeRev current = stack.pop();
			System.out.print(current.data +" ");
			if(current.right!=null) {
				stack.add(current.right);
			}
			if(current.left!=null) {
				stack.add(current.left);
			}
		}
	}
	public void inorderItrative() {
		if(root==null) {
			return;
		}
		Stack<TreeNodeRev> stack = new Stack<TreeNodeRev>();
		TreeNodeRev current =root;
		
		while(!stack.isEmpty() || current !=null) {
			if(current!=null) {
				stack.push(current);
				current=current.left;
			}else {
				current = stack.pop();
				System.out.print(current.data +" ");
				current=current.right;
			}
		}
	}
	public void itrativePostorder() {
		if(root==null) {
			return;
		}
		Stack<TreeNodeRev> stack1 = new Stack<TreeNodeRev>();
		Stack<TreeNodeRev> stack2= new Stack<TreeNodeRev>();
		
		stack1.push(root);
		
		while(!stack1.isEmpty()) {
			TreeNodeRev current = stack1.pop();
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
	
	public void printLeftView() {
		//ArrayList<Integer> ans = new ArrayList<Integer>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int level=0;
		printLeftViewUtil(root,map,level);
		for(Map.Entry<Integer, Integer> entry:map.entrySet()) {
			System.out.print(entry.getValue() +" ");
		}
	}

	private void printLeftViewUtil(TreeNodeRev node, HashMap<Integer, Integer> map, int level) {
		if(node==null) {
			return;
		}
		if(!map.containsKey(level)) {
			map.put(level, node.data);
		}
		printLeftViewUtil(node.left, map, level+1);
		printLeftViewUtil(node.right, map, level+1);
	}
	
	public void printRightView() {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		int level=0;
		printRightViewUtil(root,ans,level);
		for(int an:ans) {
			System.out.print(an +" ");
		}
	}

	private void printRightViewUtil(TreeNodeRev node, ArrayList<Integer> ans, int level) {
		if(node==null) {
			return;
		}
		if(ans.size()==level) {
			ans.add(node.data);
		}
		printRightViewUtil(node.right, ans, level+1);
		printRightViewUtil(node.left, ans, level+1);
		
	}
	public void printLeftViewItrative() {
		if(root==null) {
			return;
		}
		Queue<TreeNodeRev> queue = new LinkedList<TreeNodeRev>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i=0;i<size;i++) {
				TreeNodeRev current = queue.poll();
				if(i==size-1) {
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
	
	public void printTopView() {
		if(root==null) {
			return;
		}
		Map<Integer,Integer> map = new TreeMap<Integer, Integer>();
		Queue<HdPairObj> queue = new LinkedList<HdPairObj>();
		queue.add(new HdPairObj(0, root));
		
		while(!queue.isEmpty()) {
			HdPairObj pair = queue.poll();
			int hd = pair.hd;
			TreeNodeRev node = pair.node;
			if(!map.containsKey(hd)) {
				map.put(hd, node.data);
			}
			if(node.left!=null) {
				queue.add(new HdPairObj(hd-1, node.left));
			}
			if(node.right!=null) {
				queue.add(new HdPairObj(hd+1,node.right));
			}
		}
		for(Map.Entry<Integer, Integer> entry:map.entrySet()) {
			System.out.print(entry.getValue() +" ");
		}
	}
	TreeNodeRev head=null;
	TreeNodeRev prev=null;
	public void convertDll() {
		convertDllUtil(root);
		printDll();
		
	}

	private void convertDllUtil(TreeNodeRev node) {
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
	
	public void printDll() {
		if(head==null) {
			return;
		}
		TreeNodeRev current =head;
		while(current !=null) {
			System.out.print(current.data +" ");
			current=current.right;
		}
	}
	int dia=0;
	public int diameter() {
		diameteUtil(root);
		return dia;
	}

	private int diameteUtil(TreeNodeRev node) {
		if(node==null) {
			return 0;
		}
		int ld=diameteUtil(node.left);
		int rd=diameteUtil(node.right);
		dia=Math.max(dia, 1+ld+rd);
		
		return 1+Math.max(ld, rd);
	}
	
	public int LCA(int n1,int n2) {
		if(root==null) {
			return -1;
		}
		TreeNodeRev node = LCAUtil(root,n1,n2);
		return node.data;
	}

	private TreeNodeRev LCAUtil(TreeNodeRev node, int n1, int n2) {
		if(node==null) {
			return null;
		}
		if(node.data==n1 || node.data==n2) {
			return node;
		}
		TreeNodeRev left = LCAUtil(node.left,n1,n2);
		TreeNodeRev right = LCAUtil(node.right,n1,n2);
		if(left!=null && right !=null) {
			return node;
		}
		if(left==null) {
			return right;
		}
		if(right==null) {
			return left;
		}
		return node;
	}
	int ans=-1;
	public int minTimeBurn(int target) {
		TreeDepth depth = new TreeDepth(-1);
		burn(root,depth,target);
		return ans;
	}

	private int burn(TreeNodeRev node, TreeDepth depth, int target) {
		if(node==null) {
			return 0;
		}
		if(node.data==target) {
			depth.d=0;
			return 1;
		}
		TreeDepth ld = new TreeDepth(-1);
		TreeDepth rd = new TreeDepth(-1);
		int lh = burn(node.left, ld, target);
		int rh = burn(node.right, rd, target);
		if(ld.d!=-1) {
			ans=Math.max(ans, 1+ld.d+rh);
			depth.d=ld.d+1;
		}else if(rd.d!=-1) {
			ans=Math.max(ans, 1+rd.d+lh);
			depth.d=rd.d+1;
		}
		
		return Math.max(lh, rh)+1;
		
	}

	public static void main(String[] args) {
		BinaryTreeReviIml binaryTreeReviIml = new BinaryTreeReviIml();
		binaryTreeReviIml.insertLevelOrder(8);
		binaryTreeReviIml.insertLevelOrder(7);
		binaryTreeReviIml.insertLevelOrder(9);
		binaryTreeReviIml.insertLevelOrder(12);
		System.out.println(binaryTreeReviIml.LCA(9, 12));
		System.out.println(binaryTreeReviIml.minTimeBurn(12));
		
	}

}
