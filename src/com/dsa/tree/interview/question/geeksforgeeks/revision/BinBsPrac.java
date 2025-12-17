package com.dsa.tree.interview.question.geeksforgeeks.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.dsa.tree.interview.question.geeksforgeeks.TreeNod;

public class BinBsPrac {

	public static int height(TreeNod root) {
		if (root == null) {
			return 0;
		}

		return heightUtil(root);
	}

	private static int heightUtil(TreeNod node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(heightUtil(node.left), heightUtil(node.right));
	}
	
	public static int heightItrative(TreeNod root) {
		if(root==null) {
			return 0;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);
		int height=0;
		
		while(!queue.isEmpty()) {
			int size =queue.size();
			for(int i=0;i<size;i++) {
				TreeNod curr = queue.poll();
				if(curr.left!=null) {
					queue.add(curr.left);
				}
				if(curr.right!=null) {
					queue.add(curr.right);
				}
			}
			height++;
		}
		return height;
	}
	public static boolean isIdentical(TreeNod r1, TreeNod r2) {
	
		return isIdenticalUtil(r1,r2);
		
	}

	private static boolean isIdenticalUtil(TreeNod r1, TreeNod r2) {
		if(r1==null && r2==null ) {
			return true;
		}
		if(r1==null || r2==null || r1.data !=r2.data) {
			return false;
		}
		
	
		return isIdenticalUtil(r1.left, r2.left) && isIdenticalUtil(r1.right, r2.right);
	}
	
	public static boolean isIdenticalItrative(TreeNod r1,TreeNod r2) {
		if(r1==null && r2==null ) {
			return true;
		}
		if(r1==null || r2==null || r1.data !=r2.data) {
			return false;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(r1);
		queue.add(r2);
		
		while(!queue.isEmpty()) {
			TreeNod n1 = queue.poll();
			TreeNod n2 = queue.poll();
			if(n1==null && n2==null ) {
				return true;
			}
			if(n1==null || n2==null || n1.data !=n2.data) {
				return false;
			}
			queue.add(n1.left);
			queue.add(n2.left);
			queue.add(n1.right);
			queue.add(n2.right);
		}
		return queue.isEmpty();
	}
	public static void mirror(TreeNod root) {
		if(root==null) {
			return;
		}
		TreeNod temp = root.left;
		root.left=root.right;
		root.right=temp;
		mirror(root.left);
		mirror(root.right);
	}
	
	public void mirrorItrative(TreeNod root) {
		if(root==null) {
			return;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TreeNod current = queue.poll();
			
			TreeNod temp = current.left;
			current.left =current.right;
			current.right = temp;
			if(current.left!=null) {
				queue.add(current.left);
			}
			if(current.right!=null) {
				queue.add(current.right);
			}
		}
	}
	public static void levelOrder(TreeNod root) {
		if (root == null) {
			return;

		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNod current = queue.poll();
			System.out.print(current.data + " ");
			if (current.left != null) {
				queue.add(current.left);
			}
			if (current.right != null) {
				queue.add(current.right);
			}
		}
	}
	public static boolean isSym(TreeNod root) {
		if(root==null) {
			return true;
		}
		return isSymUtil(root.left,root.right);
	}
	

	private static boolean isSymUtil(TreeNod node1, TreeNod node2) {
		if(node1==null && node2==null ) {
			return true;
		}
		if(node1==null || node2==null || node1.data !=node2.data) {
			return false;
		}
		
		return isSymUtil(node1.left, node2.right) && isSymUtil(node1.right, node2.left);
	}
	
	public  static boolean isBalanced(TreeNod root) {
		if(root==null) {
			return true;
		}
		int lh = heightBalance(root.left);
		int rh=heightBalance(root.right);
		if(Math.abs(lh-rh)>1) {
			return false;
		}
		return isBalanced(root.left) && isBalanced(root.right);
	}

	private static int heightBalance(TreeNod node) {
		if(node==null) {
			return 0;
		}
		return Math.max(heightBalance(node.left), heightBalance(node.right))+1;
	}
	public  static boolean isBalanced1(TreeNod root) {
		return heightBalnceUtil(root) !=-1;
	}

	private static int heightBalnceUtil(TreeNod node) {
		if(node== null) {
			return 0;
		}
		int lh = heightBalnceUtil(node.left);
		if(lh==-1) {
			return -1;
		}
		int rh = heightBalnceUtil(node.right);
		if(rh==-1) {
			return -1;
		}
		if(Math.abs(lh-rh)>1) {
			return -1;
		}
		return 1+Math.max(lh, rh);
	}
	public static boolean isSum1(TreeNod root) {
		return isSumProperty1(root) !=-1;
	}
	public static int isSumProperty1(TreeNod root) {
		if(root==null) {
			return 0;
		}
		int lh = isSumProperty1(root.left);
		int rh = isSumProperty1(root.right);
		if(lh==-1 || rh ==-1 || Math.abs(lh-rh)>1) {
			return -1;
		}
		
		return 1+Math.max(lh, rh);
		
	}
	public static boolean isSumProperty(TreeNod root) {
		if(root==null) {
			return true;
		}
		 // leaf node
	    if (root.left == null && root.right == null) {
	        return true;
	    }
	    int left = (root.left != null) ? root.left.data : 0;
	    int right = (root.right != null) ? root.right.data : 0;
	    
	    if(root.data !=left+right) {
	    	return false;
	    }
		return isSumProperty(root.left) && isSumProperty(root.right);
	}
	public static boolean isSumItrative(TreeNod root) {
		if(root==null) {
			return true;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TreeNod current = queue.poll();
			
			if(current.left ==null && current.right==null) {
				continue;
			}
			if(current.left.data +current.right.data != current.data) {
				return false;
			}
			if(current.left!=null) {
				queue.add(current.left);
			}
			if(current.right!=null) {
				queue.add(current.right);
			}
		}
		return true;
	}
	
	public static TreeNod bstCreation(int[] array) {
		return bstCreUtil(array,0,array.length-1);
	}

	private static TreeNod bstCreUtil(int[] array, int start, int end) {
		if(start>end) {
			return null;
		}
		int mid = start+(end-start)/2;
		TreeNod root = new TreeNod(array[mid]);
		root.left=bstCreUtil(array, start, mid-1);
		root.right=bstCreUtil(array, mid+1, end);
		return root;
	}
	
	 static boolean isSubtree(TreeNod root1, TreeNod root2) {
		 if(root2==null) {
			 return true;
		 }
		 if(root1==null) {
			 return false;
		 }
		 if(areIden(root1,root2)) {
			 return true;
		 }
		 return isSubtree(root1.left, root2) || isSubtree(root1.right, root2);
	 }

	private static boolean areIden(TreeNod root1, TreeNod root2) {
		if(root1==null && root2==null) {
			return true;
		}
		if(root1==null || root2==null || root1.data !=root2.data) {
			return false;
		}
		return areIden(root1.left, root2.left) && areIden(root1.right, root2.right);
	}
	public static List<TreeNod> findNodes(TreeNod root,int target,int k){
		HashMap<TreeNod, TreeNod> parentMap = new HashMap<TreeNod, TreeNod>();
		Set<TreeNod> visited = new HashSet<TreeNod>();
		List<TreeNod> list = new ArrayList<TreeNod>();
		buildParent(root,null,parentMap);
		TreeNod targetNode = findTargetNode(root,target);
		 if (targetNode == null) return list;
		Queue<TreeNod> queuue = new LinkedList<TreeNod>();
		queuue.add(targetNode);
		visited.add(targetNode);
		int dist =0;
		while(!queuue.isEmpty()) {
			int size = queuue.size();
			
			if(dist==k) {
				break;
			}
			for(int i=0;i<size;i++) {
				TreeNod curr = queuue.poll();
				if(curr.left !=null && !visited.contains(curr.left)) {
					queuue.add(curr.left);
					visited.add(curr.left);
				}
				if(curr.right !=null && !visited.contains(curr.right)) {
					queuue.add(curr.right);
					visited.add(curr.right);
				}
				TreeNod parNod = parentMap.get(curr);
				if(parNod !=null && !visited.contains(parNod)) {
					queuue.add(parNod);
					visited.add(parNod);
				}
			}
			dist++;
		}
		for(TreeNod n:queuue) {
			list.add(n);
		}
		return list;
		
	}

	private static TreeNod findTargetNode(TreeNod node, int target) {
		 if(node==null) {
			 return null;
		 }
		 if(node.data == target) {
			 return node;
		 }
		 TreeNod left = findTargetNode(node.left, target);
		 if(left !=null) {
			 return left;
		 }
		 
		 return findTargetNode(node.right, target);
		 
		
	}

	private static void buildParent(TreeNod root, TreeNod parent, HashMap<TreeNod, TreeNod> parentMap) {
		if(root==null) {
			return;
		}
		parentMap.put(root, parent);
		buildParent(root.left, root,parentMap);
		buildParent(root.right, root,parentMap);
		
	}
	public static TreeNod getSucc(TreeNod root, int target) {
		 if(root==null) {
			 return null;
		 }
		 if(root.data==target && root.right !=null) {
			 return leftMost(root);
		 }
		 TreeNod succ=null;
		 TreeNod current =root;
		 while(current !=null) {
			 if(current.data >target) {
				 succ=current;
				 current = current.left;
			 }else if(current.data <= target){
				 current =current.right;
			 }
		 }
		return succ;
	 }

	private static TreeNod leftMost(TreeNod node) {
		while(node.left!=null) {
			node=node.left;
		}
		return node;
	}
	public static TreeNod findSucc(TreeNod root, int target) {
	    TreeNod[] prev = new TreeNod[1];
	    TreeNod[] succ = new TreeNod[1];
	    inorderRec(root, target, prev, succ);
	    return succ[0];
	}

	private static void inorderRec(TreeNod node, int target,
	                               TreeNod[] prev, TreeNod[] succ) {
	    if (node == null || succ[0] != null) {
	        return;
	    }

	    inorderRec(node.left, target, prev, succ);

	    if (prev[0] != null && prev[0].data == target && succ[0] == null) {
	        succ[0] = node;
	        return;
	    }

	    prev[0] = node;

	    inorderRec(node.right, target, prev, succ);
	}
	public  static TreeNod balanceBST(TreeNod root) {
		ArrayList<TreeNod> list = new ArrayList<TreeNod>();
		inorderStore(root,list);
		
		return builBSTInorder(root,list,0,list.size()-1);
	}


	private static TreeNod builBSTInorder(TreeNod node, ArrayList<TreeNod> list, int start, int end) {
		if(start>end) {
			return null;
		}
		int mid =start +(end-start)/2;
		TreeNod rootNode = list.get(mid);
		rootNode.left = builBSTInorder(rootNode.left, list, start, mid-1);
		rootNode.right = builBSTInorder(rootNode.right, list, mid+1, end);
		return rootNode;
	}

	private static void inorderStore(TreeNod node, ArrayList<TreeNod> list) {
		if(node==null) {
			return;
		}
		inorderStore(node.left, list);
		list.add(node);
		inorderStore(node.right, list);
		
	}
	public static void inorderStoreTraverse(TreeNod root) {
		if(root==null) {
			return;
		}
		inorderStoreTraverse(root.left);
		System.out.println(root.data +" ");
		inorderStoreTraverse(root.right);
		
	}
	
	public static boolean isValidBst(TreeNod root) {
		if(root==null) {
			return true;
		}
		return isValidUtil(root,Integer.MAX_VALUE,Integer.MIN_VALUE);
	}
	

	private static boolean isValidUtil(TreeNod node, int maxValue, int minValue) {
		if(node==null) {
			return true;
		}
		if(node.data <=minValue || node.data>=maxValue) {
			return false;
		}
		return isValidUtil(node.left,node.data,minValue) && isValidUtil(node.right, maxValue, node.data);
	}
	
	public static boolean isValid1(TreeNod root) {
		if(root==null) {
			return true;
		}
		TreeNod[] prev = new TreeNod[1];
		prev[0]=null;
		return isValidUtil1(root,prev);
	}

	private static boolean isValidUtil1(TreeNod node, TreeNod[] prev) {
		if(node==null) {
			return true;
		}
		if(!isValidUtil1(node.left, prev)) {
			return false;
		}
		if(prev[0]!=null && node.data <=prev[0].data) {
			return false;
		}
		prev[0]=node;
		return isValidUtil1(node.right, prev);
	}
	public static List<Integer> zigZagTraversal(TreeNod root){
		List<Integer> list = new ArrayList<Integer>();
		if(root==null) {
			return list;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);
		boolean leftToRight=true;
		
		while(!queue.isEmpty()) {
			int size= queue.size();
			LinkedList<Integer> temp = new LinkedList<Integer>();
			for(int i=0;i<size;i++) {
				
				TreeNod current = queue.poll();
				if(leftToRight) {
					temp.addLast(current.data);
				}else {
					temp.addFirst(current.data);
				}
				if(current.left!=null) {
					queue.add(current.left);
				}
				if(current.right!=null) {
					queue.add(current.right);
				}
			}
			list.addAll(temp);
			leftToRight= !leftToRight;
		}
		return list;
	}
	
	public static List<Integer> zigZagRecur(TreeNod root){
		List<Integer> list = new ArrayList<Integer>();
		if(root==null) {
			return list;
		}
		boolean leftToRight =true;
		int height= height(root);
		
		for(int i=1;i<=height;i++) {
			if(leftToRight) {
				leftToRightUtil(root,i,list);
			}else {
				rightToLeftUtil(root,i,list);
			}
			 // Flip the value of leftToRight
            leftToRight = !leftToRight;
		}
		return list;
	}

	private static void leftToRightUtil(TreeNod node, int level, List<Integer> list) {
		if (node==null) {
			return;
		}
		if(level==1) {
			list.add(node.data);
		}
		leftToRightUtil(node.left, level-1, list);
		leftToRightUtil(node.right, level-1, list);
		
	}
	
	private static void rightToLeftUtil(TreeNod node, int level, List<Integer> list) {
		if (node==null) {
			return;
		}
		if(level==1) {
			list.add(node.data);
		}
		rightToLeftUtil(node.right, level-1, list);
		rightToLeftUtil(node.left, level-1, list);
		
		
	}
	
	public static List<Integer> boundryTraversal(TreeNod root){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(root==null) {
			return list;
		}
		if(!isLeaf(root)) {
			list.add(root.data);
		}
		collectLeft(root.left,list);
		collectLeaf(root,list);
		collectRight(root.right, list);
		
		return list;
	}

	private static void collectLeaf(TreeNod node, List<Integer> list) {
		if(node==null) {
			return;
		}
		if(isLeaf(node)) {
			list.add(node.data);
			return;
		}
		collectLeaf(node.left, list);
		collectLeaf(node.right, list);
		
	}

	 private static void collectLeft(TreeNod root, ArrayList<Integer> res) {
	        
	        // exclude leaf node
	        if (root == null || isLeaf(root))
	            return;
	        
	        res.add(root.data);
	        if (root.left != null) 
	            collectLeft(root.left, res);
	        
	        else if (root.right != null)
	            collectLeft(root.right, res);
	    }

	private  static void collectRight(TreeNod root, ArrayList<Integer> res) {
	        
	        // exclude leaf nodes
	        if (root == null || isLeaf(root)) 
	            return;

	        if (root.right != null)
	            collectRight(root.right, res);

	        else if (root.left != null) 
	            collectRight(root.left, res);
	        
	        res.add(root.data);  
	    }
	private static boolean isLeaf(TreeNod node) {
		return (node.left ==null && node.right==null);
	}
	public static TreeNod buildTree(int[] inorder, int[] preorder) {
		 HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		 for(int i=0;i<inorder.length;i++) {
			 map.put(inorder[i], i);
		 }
		 int[] preIndex= {0};
		 return buildTree(map,preIndex,preorder,0,inorder.length-1);
	 }

	private static TreeNod buildTree(HashMap<Integer, Integer> map, int[] preIndex, int[] preorder, int left, int right) {
		 if(left>right) {
			 return null;
		 }
		 int rootVal= preorder[preIndex[0]];
		 preIndex[0]++;
		 TreeNod rootNode = new TreeNod(rootVal);
		 int index=map.get(rootVal);
		 
		 rootNode.left = buildTree(map, preIndex, preorder, left, index-1);
		 rootNode.right = buildTree(map, preIndex, preorder, index+1, right);
		return rootNode;
	}
	
	public static TreeNod construct(List<Integer> pre) {
		TreeNod root=null;
		
		for(int key:pre) {
			root=insertBST(root,key);
		}
		return root;
	}

	private static TreeNod insertBST(TreeNod node, int key) {
		if(node==null) {
			return new TreeNod(key);
		}else if(key<node.data) {
			node.left=insertBST(node.left, key);
		}else {
			node.right=insertBST(node.right, key);
		}
		return node;
		
	}
	public static TreeNod constructBST(List<Integer> pre) {
		int[] index=new int[1];
		index[0]=0;
		return buildBSTT(pre,index,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}

	private static TreeNod buildBSTT(List<Integer> pre, int[] index,int minVal,int maxVal) {
		if(index[0]>=pre.size()) {
			return null;
		}
		int val = pre.get(index[0]);
		if(val<minVal || val >maxVal) {
			return null;
		}
		TreeNod node = new TreeNod(val);
		index[0]++;
		node.left=buildBSTT(pre, index, minVal, val);
		node.right=buildBSTT(pre, index, val, maxVal);
		return node;
	}
	public static int findDistance(TreeNod root, int a, int b) {
	
		TreeNod lcaNode= LCA(root,a,b);
		
		int d1= findLevelIt(lcaNode,a,0);
		int d2= findLevelIt(lcaNode,b,0);
		
		return d1+d2;
	}

	private static int findLevel(TreeNod node, int k, int level) {
		if(node==null) {
			return -1;
		}
		if(node.data == k) {
			return level;
		}
		int left = findLevel(node.left, k, level+1);
		if(left==-1) {
			return findLevel(node.right, k, level+1);
		}
		return left;
	}
	
	private static int findLevelIt(TreeNod root, int target,int level) {
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);
		while (!queue.isEmpty()) {
	        int size = queue.size();
	        for (int i = 0; i < size; i++) {
	            TreeNod curr = queue.poll();
	            if (curr.data == target) return level;

	            if (curr.left != null) queue.offer(curr.left);
	            if (curr.right != null) queue.offer(curr.right);
	        }
	        level++;
	    }
	    return level;
	}

	private static TreeNod LCA(TreeNod node, int a, int b) {
		if(node==null) {
			return null;
		}
		if(node.data==a || node.data==b) {
			return node;
		}
		TreeNod left = LCA(node.left, a, b);
		TreeNod right = LCA(node.right, a, b);
		if(left!=null && right !=null) {
			return node;
		}
		if(left ==null) {
			return right;
		}
		if(right==null) {
			return left;
		}
		return node;
	}
	
	public static int maxPathSum(TreeNod root) {
		if(root==null) {
			return 0;
		}
		int[] maxSum= {Integer.MIN_VALUE};
		findMaxSum(root,0,maxSum);
		return maxSum[0];
	}

	private static void findMaxSum(TreeNod node, int currentSum, int[] maxSum) {
		if(node==null) {
			return;
		}
		currentSum +=node.data;
		if(node.left==null && node.right==null) {
			if(currentSum >maxSum[0]) {
				maxSum[0]=currentSum;
			}	
		}
		
		findMaxSum(node.left, currentSum, maxSum);
		findMaxSum(node.right, currentSum, maxSum);
		
		
	}
	public static int getLevelEvenOddDiff(TreeNod root) {
		if(root==null) {
			return 0;
		}
		int[] evenSum = {0};
		int[] oddSum = {0};
		findDiff(root,evenSum,oddSum,0);
		return evenSum[0]-oddSum[0];
	}

	private static void findDiff(TreeNod node, int[] evenSum, int[] oddSum, int level) {
	  if(node==null) {
		  return;
	  }
	  if(level % 2==0) {
		  evenSum[0] +=node.data;
	  }else {
		  oddSum[0] +=node.data;
	  }
	  findDiff(node.left, evenSum, oddSum, level+1);
	  findDiff(node.right, evenSum, oddSum, level+1);
		
	}

	public static void main(String[] args) {
		TreeNod root = new TreeNod(10);
        root.left = new TreeNod(20);
        root.right = new TreeNod(30);
        root.left.left = new TreeNod(40);
        root.left.right = new TreeNod(60);

        System.out.println(getLevelEvenOddDiff(root));

	        
	}

}
