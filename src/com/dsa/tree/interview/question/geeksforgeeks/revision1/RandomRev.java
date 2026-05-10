package com.dsa.tree.interview.question.geeksforgeeks.revision1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

import com.dsa.tree.interview.question.geeksforgeeks.NodeHd;
import com.dsa.tree.interview.question.geeksforgeeks.TreeNod;
import com.dsa.tree.interview.question.geeksforgeeks.revision.TreeNodNextRightNode;
import com.dsa.tree.revision.IncExcludePair;

public class RandomRev {

	public static int isSum(TreeNod root) {
		return isSumUtil(root);
	}

	private static int isSumUtil(TreeNod node) {
	if(node == null || (node.left==null && node.right==null)) {
		return 1;
	}
	int sum=0;
	if(node.left!=null) {
		sum +=node.left.data;
	}
	if(node.right!=null) {
		sum +=node.right.data;
	}
	
	return ((node.data == sum)
            && (isSumUtil(node.left) == 1)
            && (isSumUtil(node.right) == 1))
            ?
            1:0;
	}
	public static int isSumIt(TreeNod root) {
		if(root==null) {
			return 1;
		}
		Queue<TreeNod> q= new LinkedList<TreeNod>();
		q.add(root);
		
		while(!q.isEmpty()) {
			TreeNod cuurent = q.poll();
			if(cuurent.left==null && cuurent.right==null) {
				continue;
			}
			int sum=0;
			if(cuurent.left!=null) {
				sum +=cuurent.left.data;
			}
			if(cuurent.right!=null) {
				sum +=cuurent.right.data;
			}
			if(cuurent.data !=sum) {
				return 0;
			}
			if(cuurent.left!=null) {
				q.add(cuurent.left);
			}
			if(cuurent.right!=null) {
				q.add(cuurent.right);
			}
		}
		return 1;
	}
	
	public static TreeNod inOrderSucc(TreeNod root,int target) {
		    TreeNod[] prev = new TreeNod[1];
		    TreeNod[] succ = new TreeNod[1];
		 inOrderSuccUtil(root,target,prev,succ);
		 return succ[0];
	}

	private static void inOrderSuccUtil(TreeNod node, int target, TreeNod[] prev, TreeNod[] succ) {
		if(node==null || succ[0]!=null) {
			return;
		}
		inOrderSuccUtil(node.left, target, prev, succ);
		if(prev[0]!=null && prev[0].data==target && succ[0]==null) {
			succ[0]=node;
			return;
		}
		inOrderSuccUtil(node.right, target, prev, succ);
	}
	
	public static ArrayList<Integer> boundryTraversal(TreeNod root){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(root==null) {
			return list;
		}
		if (!isLeaf(root))
            list.add(root.data);
		collectLeft(root.left,list);
		collectLeaf(root, list);
		collectRight(root.right, list);
		return list;
	}

	private static void collectLeft(TreeNod node, ArrayList<Integer> list) {
		if(node ==null || isLeaf(node)) {
			return;
		}
		list.add(node.data);
			collectLeft(node.left, list);
			collectLeft(node.right, list);
	}
	
	private static void collectRight(TreeNod node, ArrayList<Integer> list) {
		if(node ==null || isLeaf(node)) {
			return;
		}
		
		collectRight(node.right, list);
		collectRight(node.left, list);
		list.add(node.data);
		
	}
	private static void collectLeaf(TreeNod node, ArrayList<Integer> list) {
		if(node ==null) {
			return;
		}
		if(isLeaf(node)) {
			list.add(node.data);
			return;
		}
		
		collectLeaf(node.left, list);
		collectLeaf(node.right, list);
		
	}

	private static boolean isLeaf(TreeNod node) {
		
		return (node.left==null && node.right==null);
	}
	
	public static int kthSmallest(TreeNod root, int k) {
		if(root==null) {
			return -1;
		}
		int[] smallest= {0};
		int[] count= {0};
		inOrderSmallest(root,k,smallest,count);
		return smallest[0];
	}

	private static void inOrderSmallest(TreeNod node, int k, int[] smallest, int[] count) {
		if(node==null) {
			return;
		}
		inOrderSmallest(node.left, k, smallest,count);
		count[0]++;
		if(count[0]==k) {
			smallest[0]=node.data;
			return;
		}
		inOrderSmallest(node.right, k, smallest,count);
	}
	
	public static int kthLargest(TreeNod root, int k) {
		if(root==null) {
			return -1;
		}
		int[] smallest= {0};
		int[] count= {0};
		reverseinOrderLergset(root,k,smallest,count);
		return smallest[0];
	}

	private static void reverseinOrderLergset(TreeNod node, int k, int[] smallest, int[] count) {
		if(node==null) {
			return;
		}
		reverseinOrderLergset(node.right, k, smallest,count);
		count[0]++;
		if(count[0]==k) {
			smallest[0]=node.data;
			return;
		}
		reverseinOrderLergset(node.left, k, smallest,count);
		
		
	}
	

	public static int diameter(TreeNod root) {
		if(root==null) {
			return 0;
		}
		int[] dia= {0};
		diameterUtil(root,dia);
		return dia[0];
	}

	private static int diameterUtil(TreeNod node, int[] dia) {
		if(node==null) {
			return 0;
		}
		int lh= diameterUtil(node.left, dia);
		int rh =diameterUtil(node.right, dia);
		dia[0]=Math.max(dia[0], 1+lh+rh);
		
		return 1+Math.max(lh, rh);
		
	}
	 public static int maxPathSum(TreeNod root) {
		 if(root==null) {
			 return 0;
		 }
		 int[] maxSum= {0};
		 sumMax(root,0,maxSum);
		 return maxSum[0];
	 }
	
	private static void sumMax(TreeNod node, int currenSum, int[] maxSum) {
		if(node==null) {
			return;
		}
		currenSum +=node.data;
		if(node.left==null && node.right==null) {
			if(currenSum>maxSum[0]) {
				maxSum[0]=currenSum;
			}
		}
		sumMax(node.left,currenSum,maxSum);
		sumMax(node.right,currenSum,maxSum);
		
	}
	 public static int findDistance(TreeNod root, int a, int b) {
	TreeNod lcaNode=LCA(root,a,b);
	int d1=findLevel1(lcaNode,a);
	int d2 = findLevel1(lcaNode,b);
	 
	 return d1+d2;
	 }

	private static int findLevel(TreeNod lcaNode, int k, int level) {
		if(lcaNode==null) {
			return -1;
		}
		if (lcaNode.data == k) {
            return level;
        }
		int left =findLevel(lcaNode.left, k, level+1);
		if(left ==-1) {
			return findLevel(lcaNode.right, k, level+1);
		}
		return left;
	}
	private static int findLevel1(TreeNod lcaNode, int k) {
		if(lcaNode==null) {
			return 0;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(lcaNode);
		int level=0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i=0;i<size;i++) {
			   TreeNod current = queue.poll();
			   if(current.data==k) {
				   return level;
			   }
			   if(current.left!=null) {
				   queue.add(current.left);
			   }
			   if(current.right!=null) {
				   queue.add(current.right);
			   }
			}
			level++;
		}
		return level;
	}

	private static TreeNod LCA(TreeNod node, int a, int b) {
		if(node==null) {
			return null;
		}
		if(node.data ==a || node.data==b) {
			return node;
		}
		TreeNod left = LCA(node.left, a, b);
		TreeNod right= LCA(node.right, a, b);
		if(left==null) {
			return right;
		}
		if(right==null) {
			return left;
		}
		return node;
	}
	
	public static TreeNod getSucc(TreeNod root,int target) {
		if(root==null) {
			return null;
		}
		   TreeNod[] prev = new TreeNod[1];
		    TreeNod[] succ = new TreeNod[1];
		inOrderSuccUtil1(root,target,prev,succ);
		return succ[0];
	}

	

	private static void inOrderSuccUtil1(TreeNod node, int target, TreeNod[] prev, TreeNod[] succ) {
		if(node==null || succ[0]!=null) {
			return;
		}
		inOrderSuccUtil1(node.left, target, prev, succ);
		if(prev[0]!=null && prev[0].data==target && succ[0]== null) {
			succ[0]=node;
			return;
		}
		inOrderSuccUtil1(node.right, target, prev, succ);
				
		
	}
	public static TreeNod getSuccItrative(TreeNod root,int target) {
		if(root==null) {
			return null;
		}
		Stack<TreeNod> stack = new Stack<TreeNod>();
		TreeNod current =root;
		TreeNod prev=null;
		while(!stack.isEmpty() || current !=null) {
			while(current !=null) {
				stack.push(current);
				current =current.left;
			}
			current = stack.pop();
			if(prev !=null && prev.data==target) {
				return current;
			}
			prev =current;
			
			current =current.right;
			
			
		}
		return null;
	}
	
	public static boolean isSubtree(TreeNod root1,TreeNod root2) {
		if(root1==null) {
			return false;
		}
		if(root2==null) {
			return true;
		}
		if(areIden(root1,root2)) {
			return true;
		}
		return  isSubtree(root1.left, root2)|| isSubtree(root1.right, root2);
	}

	private static boolean areIden(TreeNod root1, TreeNod root2) {
		if(root1==null && root2==null) {
			return true;
		}
		 if (root1 == null || root2 == null) {
			  return false; 
		 }
	          
		return (root1.data==root2.data) && areIden(root1.left, root2.left) && areIden(root1.right, root2.right);
	}
	public static boolean isSubtreeIterative(TreeNod root1, TreeNod root2) {
	    if (root2 == null) return true;
	    if (root1 == null) return false;

	    Queue<TreeNod> queue = new LinkedList<>();
	    queue.add(root1);

	    while (!queue.isEmpty()) {
	        TreeNod current = queue.poll();

	        if (current.data == root2.data) {
	            if (isSubTreeItrativeUtil(current, root2)) {
	                return true;
	            }
	        }

	        if (current.left != null) queue.add(current.left);
	        if (current.right != null) queue.add(current.right);
	    }
	    return false;
	}
	

	public static boolean isSubTreeItrativeUtil(TreeNod root1,TreeNod root2) {
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root1);
		queue.add(root2);
		
		while(!queue.isEmpty()) {
			TreeNod r1 = queue.poll();
			TreeNod r2 = queue.poll();
			if(r1==null && r2==null) {
				continue;
			}
			if(r1==null || r2==null || r1.data !=r2.data) {
				return false;
			}
			queue.add(r1.left);
			queue.add(r2.left);
			
			queue.add(r1.right);
			queue.add(r2.right);
			
		}
		return true;
	}
	

	public static boolean checkBst(TreeNod root) {
		if(root ==null) {
			return true;
		}
		TreeNod[] prev= {null};
		return checkBStUtil(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}

	
	private static boolean checkBstInorder(TreeNod node, TreeNod[] prev) {
		if(node==null) {
			return true;
		}
		if(!checkBstInorder(node.left, prev)) {
			return false;
		}
		if(prev[0] !=null && prev[0].data>=node.data) {
			return false;
		}
		prev[0]=node;
		return checkBstInorder(node.right, prev);
	}
	public static boolean checkBStUtil(TreeNod node,int min,int max) {
		if(node==null) {
			return true;
		}
		if(node.data <=min || node.data>=max) {
			return false;
		}
		
		return checkBStUtil(node.left,min,node.data) && checkBStUtil(node.right,node.data,max);
	}
	
	public static ArrayList<Integer> zigzagTraversal(TreeNod root){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(root==null) {
			return list;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);
		boolean leftToRight=true;
		
		while(!queue.isEmpty()) {
			int size=queue.size();
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
			leftToRight =!leftToRight;  
		}
		return list;
	}
	public static boolean targetSum(TreeNod root,int target) {
		if(root==null) {
			return false;
		}
		HashSet<Integer> set =new HashSet<Integer>();
		return tagetSumUtil(root,set,target);
	}

	private static boolean tagetSumUtil(TreeNod node, HashSet<Integer> set,int target) {
		if(node==null) {
			return false;
		}
		if(tagetSumUtil(node.left, set,target)) {
			return true;
		}
		if(set.contains(target-node.data)) {
			return true;
		}
		set.add(node.data);
		return tagetSumUtil(node.right, set,target);
	}
	public static boolean targetSumLevelOrder(TreeNod root, int target) {
	    if (root == null) return false;

	    Set<Integer> set = new HashSet<>();
	    Queue<TreeNod> queue = new LinkedList<>();
	    queue.add(root);
	    
	    while(!queue.isEmpty()) {
	    	TreeNod current = queue.poll();
	    	
	    	if(set.contains(target-current.data)) {
	    		return true;
	    	}
	    	set.add(current.data);
	    	
	    	 if (current.left != null) queue.add(current.left);
	         if (current.right != null) queue.add(current.right);
	    }
		return false;
	    
	}
	
	public static TreeNod convertIntoBalnaceTree(TreeNod root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		buildInorder(root,list);
		
		return builBalanceUtil(list,0,list.size()-1);
	}

	private static TreeNod builBalanceUtil(ArrayList<Integer> list, int start, int end) {
		if(start>end) {
			return null;
		}
		int mid= start+(end-start)/2;
		TreeNod rootNode= new TreeNod(list.get(mid));
		rootNode.left= builBalanceUtil(list, start, mid-1);
		rootNode.right= builBalanceUtil(list, mid+1, end);
		return rootNode;
	}

	private static void buildInorder(TreeNod node, ArrayList<Integer> list) {
		if(node==null) {
			return;
		}
		buildInorder(node.left, list);
		list.add(node.data);
		buildInorder(node.right, list);
		
	}
	public static boolean isBalanceBSTCheck(TreeNod root) {
		if(root==null) {
			return true;
		}
		return isBalanceBSTCheckUtil(root)!=-1;
	}

	private static int isBalanceBSTCheckUtil(TreeNod node) {
		if(node==null) {
			return 0;
		}
		int lh=isBalanceBSTCheckUtil(node.left);
		int rh =isBalanceBSTCheckUtil(node.right);
		if(lh ==-1 || rh==-1 || Math.abs(lh-rh)>1) {
			return -1;
		}
		return 1+Math.max(lh, rh);
	}
	
	public static int oddEvenLevelNodesDiff(TreeNod root) {
		if(root==null) {
			return 0;
		}
		Queue<TreeNod> queue= new LinkedList<TreeNod>();
		queue.add(root);
		int level =0,oddSum=0,evenSum=0;
		
		
		while(!queue.isEmpty()) {
			int size=queue.size();
			for(int i=0;i<size;i++) {
				TreeNod current = queue.poll();
				if((level & 1)==0) {
					evenSum +=current.data;
				}else {
					oddSum  +=current.data;
				}
				if(current.left!=null) {
					queue.add(current.left);
				}
				if(current.right!=null) {
					queue.add(current.right);
				}
			}
			level++;
		}
		return evenSum-oddSum;
	}
	
	public static int oddEvenLevelNodesDiffRec(TreeNod root) {
		int[] evenSum= {0},oddSum= {0};
		oddEvenDiffUtilRec(root,evenSum,oddSum,0);
		return evenSum[0]-oddSum[0];
	}

	private static void oddEvenDiffUtilRec(TreeNod node, int[] evenSum, int[] oddSum, int level) {
		if(node==null) {
			return;
		}
		if((level & 1)==0) {
			evenSum[0] +=node.data;
		}else {
			oddSum[0] +=node.data;
		}
		oddEvenDiffUtilRec(node.left, evenSum, oddSum, level+1);
		oddEvenDiffUtilRec(node.right, evenSum, oddSum, level+1);
	}
	public static TreeNod buildTree(int[] inorder, int[] preorder) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0;i<inorder.length;i++) {
			map.put(inorder[i], i);
		}
		int[] preIndex= {0};
	return	buildTreeUtil(preorder,map,0,preorder.length-1,preIndex);
	}

	private static TreeNod buildTreeUtil(int[] preorder, HashMap<Integer, Integer> map,int start,int end,int[] preIndex) {
		if(start>end) {
			return null;
		}
		int rootValue= preorder[preIndex[0]++];
		int index =map.get(rootValue);
		TreeNod rootNode = new TreeNod(rootValue);
		rootNode.left= buildTreeUtil(preorder, map, start, index-1,preIndex);
		rootNode.right= buildTreeUtil(preorder, map, index+1, end,preIndex);
		
		return rootNode;
		
	}
	public static TreeNod construct(List<Integer> pre) {
		TreeNod root=null;
		for(int key:pre) {
		root=	builTreeUsingPreOrder(root,key);
		}
		return root;
	}

	private static TreeNod builTreeUsingPreOrder(TreeNod root, int key) {
		if(root==null) {
			return new TreeNod(key);
		}else if(root.data>key) {
			root.left=builTreeUsingPreOrder(root.left, key);
		}else if(root.data<key) {
			root.right=builTreeUsingPreOrder(root.right, key);
		}
		return root;
		
	}
	public static TreeNod deleteNode(TreeNod root,int key) {
		  if (root == null) return null;

		    if (key < root.data) {
		        root.left = deleteNode(root.left, key);
		    } 
		    else if (key > root.data) {
		        root.right = deleteNode(root.right, key);
		    } 
		    else {
		        // Case 1 & 2: 0 or 1 child
		        if (root.left == null) return root.right;
		        if (root.right == null) return root.left;
			root.data=findMin(root.right);
			root.right=deleteNode(root.right, root.data);
		}
		return root;
	}

	private static int findMin(TreeNod node) {
		while(node.left!=null) {
			node=node.left;
		}
		return node.data;
	}
	
	public static ArrayList<Integer> verticalTraversal(TreeNod root){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(root==null) {
			return list;
		}
		Queue<NodeHd> queue = new LinkedList<NodeHd>();
		queue.add(new NodeHd(0, root));
		Map<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		
		while(!queue.isEmpty()) {
			NodeHd pair = queue.poll();
			int hd = pair.hd;
			TreeNod node = pair.node;
			if(map.containsKey(hd)) {
				map.get(hd).add(node.data);
			}else {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(node.data);
				map.put(hd, temp);
			} 
			if(node.left !=null) {
				queue.add( new NodeHd(hd-1, node.left));
			}
			if(node.right !=null) {
				queue.add( new NodeHd(hd+1, node.right));
			}
			
		}
		for(Map.Entry<Integer, ArrayList<Integer>> entry :map.entrySet()) {
			list.addAll(entry.getValue());
		}
		return list;
	}
	public TreeNod removeKeysOptimz(TreeNod node, int l, int r) {
		if(node==null) {
			return null;
		}
		TreeNod left = removeKeys(node.left, l, r);
		TreeNod right = removeKeys(node.right, l, r);
		if(node.data>=l && node.data <=r) {
			node.left=left;
			node.right=right;
			return node;
		}else if(node.data<l) {
			return right;
		}else {
			return left;
		}
	}
	
	public TreeNod removeKeys(TreeNod node, int l, int r) {
	    if (node == null) {
	        return null;
	    }

	    if (node.data < l) {
	        return removeKeys(node.right, l, r);
	    }

	    if (node.data > r) {
	        return removeKeys(node.left, l, r);
	    }

	    node.left = removeKeys(node.left, l, r);
	    node.right = removeKeys(node.right, l, r);
	    return node;
	}
	public static TreeNod removeKeysIterative(TreeNod root, int l, int r) {

	    // Step 1: Fix root
	    while (root != null && (root.data < l || root.data > r)) {
	        if (root.data < l) {
	            root = root.right;
	        } else {
	            root = root.left;
	        }
	    }

	    TreeNod curr = root;

	    // Step 2: Fix left subtree
	    while (curr != null) {
	        while (curr.left != null && curr.left.data < l) {
	            curr.left = curr.left.right;
	        }
	        curr = curr.left;
	    }

	    curr = root;

	    // Step 3: Fix right subtree
	    while (curr != null) {
	        while (curr.right != null && curr.right.data > r) {
	            curr.right = curr.right.left;
	        }
	        curr = curr.right;
	    }

	    return root;
	}
	public boolean checkPairSum(TreeNod root,int target) {
		HashSet<Integer> set =new HashSet<Integer>();
		return findPair(root,target,set);
	}



	private boolean findPair(TreeNod node, int target, HashSet<Integer> set) {
		if(node==null) {
			return false;
		}
		if(findPair(node.left, target, set)) {
			return true;
		}
		if(set.contains(target-node.data)) {
			return true;
		}
		set.add(node.data);
		return findPair(node.right, target, set);
	}
	public boolean checkPairSumItrative(TreeNod root,int target) {
		if(root==null) {
			return false;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		HashSet<Integer> set = new HashSet<Integer>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TreeNod current = queue.poll();
			if(set.contains(target-current.data)) {
				return true;
			}
			set.add(current.data);
			if(current.left!=null) {
				queue.add(current.left);
			}
			if(current.right!=null) {
				queue.add(current.right);
			}
		}
		return false;
	}
	public static ArrayList<Integer> KDistanceNodes(TreeNod root, int target, int k) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		HashMap<TreeNod,TreeNod> parentMap = new HashMap<TreeNod, TreeNod>();
		buildParent(root,null,parentMap);
	TreeNod targetNode=	findTragetNode(root,target);
	if(targetNode==null) {
		return list;
	}
	HashSet<TreeNod> visited = new HashSet<TreeNod>();
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
			TreeNod parentNode = parentMap.get(curr);
			if(parentNode !=null && !visited.contains(parentNode)) {
				queuue.add(parentNode);
				visited.add(parentNode);
			}
		}
		dist++;
	}
	for(TreeNod n:queuue) {
		list.add(n.data);
	}
	return list;
		
		
	}


	private static TreeNod findTragetNode(TreeNod root,int target) {
		if(root==null) {
			return null;
		}
		if(root.data==target) {
			return root;
		}
		TreeNod left = findTragetNode(root.left, target);
		if(left!=null) {
			return left;
		}
		return findTragetNode(root.right, target);
	}

	private static void buildParent(TreeNod root, TreeNod parent, HashMap<TreeNod, TreeNod> parentMap) {
	if(root==null) {
		return;
	}
	parentMap.put(root, parent);
	 buildParent(root.left, root, parentMap);
	 buildParent(root.right, root, parentMap);
		
	}
	public  ArrayList<Integer> printAncestors(TreeNod node, int target) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(node==null) {
			return list;
		}
		HashMap<TreeNod, TreeNod> parentMap = new HashMap<TreeNod, TreeNod>();
		buildParent(node, null, parentMap);
		TreeNod targetNode = findTragetNode(node, target);
		if(targetNode==null) {
			return list;
		}
		 TreeNod current = parentMap.get(targetNode);
		while(current!=null) {
			list.add(current.data);
			current=parentMap.get(current);
		}
		return list;
	}
	public static boolean printAncestorsdfs(TreeNod node, int target) {
		if(node==null) {
			return false;
		}
		if(node.data==target) {
			return true;
		}
		if(printAncestorsdfs(node.left, target) || printAncestorsdfs(node.right, target)) {
			System.out.print(node.data +" ");
			return true;
		}
		return false;
	}
	public static ArrayList<Integer> printAns(TreeNod root,int target){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(root==null) {
			return list;
		}
		HashMap<Integer, Integer> parentMap = new HashMap<Integer, Integer>();
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);
		parentMap.put(root.data, null);
		
		while(!queue.isEmpty()) {
			TreeNod current = queue.poll();
			if(current.data==target) {
				break;
			}
			if(current.left!=null) {
				parentMap.put(current.left.data, current.data);
				queue.add(current.left);
			}
			if(current.right!=null) {
				parentMap.put(current.right.data, current.data);
				queue.add(current.right);
			}
		}
		Integer curr=target;
		  while (parentMap.get(curr) != null) {
	            curr = parentMap.get(curr);
	            list.add(curr);
	        }
			
		return list;
}
	
	public static void findGreaterSum(TreeNod root) {
		if(root==null) {
			return;
		}
		int[] sum= {0};
		greaterSum(root,sum);
	}
	private static void greaterSum(TreeNod node, int[] sum) {
		if(node==null) {
			return;
		}
		greaterSum(node.right, sum);
		int temp=node.data;
		node.data=sum[0];
		sum[0] +=temp;
		greaterSum(node.left, sum);
		
	}
	public static void findGreaterSumIt(TreeNod root) {
		if(root==null) {
			return;
		}
		int sum=0;
		TreeNod current =root;
		Stack<TreeNod> stack = new Stack<TreeNod>();
		stack.push(root);
		
		while(current !=null || !stack.isEmpty()) {
			
			while(current!=null) {
				stack.push(current);
				current =current.right;
			}
			 current = stack.pop();
			 int temp=current.data;
			 current.data=sum;
			 sum +=temp;
			 
			 current=current.left;
		}
	}
	public static ArrayList<Integer> extremeNode(TreeNod root){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(root==null) {
			return list;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);
		boolean leftRight=true;
		
		while(!queue.isEmpty()) {
			int size=queue.size();
			for(int i=0;i<size;i++) {
				TreeNod current = queue.poll();
				if(leftRight && i==0) {
					list.add(current.data);
				}else if(!leftRight && i==size-1) {
					list.add(current.data);
				}
				if(current.left!=null) {
					queue.add(current.left);
				}
				if(current.right!=null) {
					queue.add(current.right);
				}
				
			}
			leftRight =!leftRight;
		
		}
		return list;
	}
	
	public static int largetBst(TreeNod root) {
		if(root==null) {
			return 0;
		}
		if(isValidBst(root,Integer.MIN_VALUE,Integer.MAX_VALUE)) {
			return size(root);
		}
		
		return Math.max(largetBst(root.left), largetBst(root.right));
	}

	private static int size(TreeNod root) {
		if(root==null) {
			return 0;
		}
		return size(root.left) + size(root.right) + 1;
	}

	private static boolean isValidBst(TreeNod root, int minValue, int maxValue) {
		if(root==null) {
			return true;
		}
		if(root.data<=minValue || root.data>=maxValue) {
			return false;
		}
		return isValidBst(root.left,  minValue,  root.data) && isValidBst(root.right,  root.data,  maxValue);
	}
	public static void greaterSumPr(TreeNod root) {
		int[] sum= {0};
		gretestSumUtil(root,sum);
	}

	private static void gretestSumUtil(TreeNod node, int[] sum) {
		if(node==null) {
			return;
		}
		gretestSumUtil(node.right, sum);
		int temp=node.data;
		node.data=sum[0];
		sum[0] +=temp;
		gretestSumUtil(node.right, sum);
		
	}
	
	public static int maxSum(TreeNod root) {
		IncExcludePair max = maxSumUtil(root);
		return Math.max(max.include,max.exclude);
	}
	public static  IncExcludePair maxSumUtil(TreeNod node) {
		if(node==null) {
			return new IncExcludePair(0, 0);
		}
		IncExcludePair left = maxSumUtil(node.left);
		IncExcludePair right = maxSumUtil(node.right);
		int iclude=node.data +left.exclude + right.exclude;
		int exclude= Math.max(left.include, left.exclude)+Math.max(right.include, right.exclude);
		
		return new IncExcludePair(iclude, exclude);
	}
	
	public static void builNextRightItrative(TreeNodNextRightNode root) {
		if(root==null) {
			return;
		}
		Queue<TreeNodNextRightNode> queue = new LinkedList<TreeNodNextRightNode>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TreeNodNextRightNode prev = null;
			int size=queue.size();
			
			for(int i=0;i<size;i++) {
				TreeNodNextRightNode current = queue.poll();
				if(prev !=null) {
					prev.nextRight=current;
				}
				prev=current;
				if(current.left!=null) {
					queue.add(current.left);
				}
				if(current.right !=null) {
					queue.add(current.right);
				}
			}
			//if(prev !=null) {
				prev.nextRight=null;
			//}
		}
	}

	public static void main(String[] args) {
		TreeNod root = new TreeNod(1);           
        root.left = new TreeNod(2);             
        root.right = new TreeNod(3);           
        root.right.left = new TreeNod(4);     
        root.right.right = new TreeNod(5);     
        root.left.left = new TreeNod(1);   
      
      	System.out.print(maxSum(root));
      

}
}
