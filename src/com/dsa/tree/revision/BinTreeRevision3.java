package com.dsa.tree.revision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.dsa.tree.interview.question.geeksforgeeks.TreeNod;
import com.dsa.tree.interview.question.geeksforgeeks.TreeNodeNextRight;

import java.util.Queue;
import java.util.Stack;

public class BinTreeRevision3 {
	
	public static void levelOrder(TreeNodeRev node) {
		if (node == null) {
			return;
		}
		Queue<TreeNodeRev> queue = new LinkedList<TreeNodeRev>();
		queue.add(node);

		while (!queue.isEmpty()) {
			TreeNodeRev current = queue.poll();
			System.out.print(current.data + " ");
			if (current.left != null) {
				queue.add(current.left);
			}
			if (current.right != null) {
				queue.add(current.right);
			}
		}
	}
	
	
	public static boolean leafSimilar(TreeNodeRev node1,TreeNodeRev node2) {
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2= new ArrayList<Integer>();
		collectLeaves(node1, list1);
		collectLeaves(node2, list2);
	
		return list1.equals(list2);
	}
	private static void collectLeaves(TreeNodeRev node,ArrayList<Integer> list){
		if(node==null) {
			return;
		}
		if(isLeaf(node)) {
			list.add(node.data);
			return;
		}
			collectLeaves(node.left, list);
			collectLeaves(node.right, list);
	}

	private static boolean isLeaf(TreeNodeRev node) {
		return node.left==null && node.right==null;
	}
	
	public static int findMaxSumLeafRoot(TreeNodeRev node) {
	    int[] maxSum = new int[1];
	    maxSum[0] = Integer.MIN_VALUE;
		maxSumleafToRoot(node, 0,maxSum);
		return maxSum[0];
	}
	public static void maxSumleafToRoot(TreeNodeRev node,int currentSum,int[] maxSum) {
		if(node==null) {
			return;
		}
		currentSum +=node.data;
		if(node.left==null && node.right==null) {
			maxSum[0] = Math.max(currentSum, maxSum[0]);
			return;
		}
		maxSumleafToRoot(node.left, currentSum,maxSum);
		maxSumleafToRoot(node.right, currentSum,maxSum);
	}
	
	public static int maxSumLeadItrative(TreeNodeRev node) {
		if(node==null) {
			return 0;
		}
		int maxSum=0;
		Stack<TreeNodeSumVar> stack = new Stack<TreeNodeSumVar>();
		stack.push(new TreeNodeSumVar(0, node));
		
		while(!stack.isEmpty()) {
			TreeNodeSumVar pair = stack.pop();
			int sum = pair.sum;
			TreeNodeRev current=pair.node;
			sum=sum+current.data;
			if(current.left==null && current.right==null) {
				 maxSum = Math.max(maxSum, sum);
			}
			if(current.right!=null) {
				stack.push(new TreeNodeSumVar(sum, current.right));
			}
			if(current.left!=null) {
				stack.push(new TreeNodeSumVar(sum, current.left));
			}
			
			
		}
		return maxSum;
	}
	public static int maxSumLeafIterative1(TreeNodeRev root) {
	    if (root == null) return 0;

	    int maxSum = 0;
	    Stack<TreeNodeRev> stack = new Stack<>();
	    Map<TreeNodeRev, Integer> sumMap = new HashMap<>();

	    stack.push(root);
	    sumMap.put(root, root.data);

	    while (!stack.isEmpty()) {
	        TreeNodeRev node = stack.pop();
	        int currentSum = sumMap.get(node);

	        // If it's a leaf node → update max sum
	        if (node.left == null && node.right == null) {
	            maxSum = Math.max(maxSum, currentSum);
	        }

	        // Push children with updated sums
	        if (node.right != null) {
	            stack.push(node.right);
	            sumMap.put(node.right, currentSum + node.right.data);
	        }
	        if (node.left != null) {
	            stack.push(node.left);
	            sumMap.put(node.left, currentSum + node.left.data);
	        }
	    }

	    return maxSum;
	}
	
	public static int evenOddDiffSum(TreeNodeRev node) {
		int[] evenSum=new int[1];
		int[] oddSum= new int[1];
		evenSum[0]=0;
		oddSum[0]=0;
		evenOddDiffSumUtil(node,evenSum,oddSum,0);
		return evenSum[0]-oddSum[0];
	}

	private static void evenOddDiffSumUtil(TreeNodeRev node, int[] evenSum, int[] oddSum,int level) {
		if(node ==null) {
			return;
		}
		if(level % 2==0) {
			evenSum[0] +=node.data;
		}else {
			oddSum[0] +=node.data;
		}
		evenOddDiffSumUtil(node.left,evenSum,oddSum,level+1);
		evenOddDiffSumUtil(node.right,evenSum,oddSum,level+1);
	}
	public static int evenOddSumDiff(TreeNodeRev node) {
		if(node==null) {
			return 0;
		}
		int evenSum=0;
		int oddSum=0;
		int level=0;
		
		Queue<TreeNodeRev> queue = new LinkedList<TreeNodeRev>();
		queue.add(node);
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i=0;i<size;i++) {
				TreeNodeRev current = queue.poll();
				if(level %2==0) {
					evenSum +=current.data;
					
				}else {
					oddSum +=current.data;
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
	public static int LCA(TreeNodeRev node,int n1,int n2) {
		return LCAUtil(node,n1,n2).data;
	}
	private static TreeNodeRev LCAUtil(TreeNodeRev node, int n1, int n2) {
		if(node==null) {
			return null;
		}
		if(node.data ==n1 || node.data==n2) {
			return node;
		}
		TreeNodeRev left= LCAUtil(node.left, n1, n2);
		TreeNodeRev right= LCAUtil(node.right, n1, n2);
		if(left==null) {
			return right;
		}
		if(right==null) {
			return left;
		}
		return node;
	}
	public static void printAnc(TreeNodeRev node,TreeNodeRev target) {
		HashMap<TreeNodeRev, TreeNodeRev> parentMap = new HashMap<TreeNodeRev, TreeNodeRev>();
		printAncestorsUtil(node, null, parentMap);
		  TreeNodeRev temp = target;
		    while (parentMap.get(temp) != null) {
		        temp = parentMap.get(temp);
		        System.out.println(temp.data);
		    }
	}
	public static void  printAncestorsUtil(TreeNodeRev node,TreeNodeRev parent,HashMap<TreeNodeRev, TreeNodeRev> map){
		if(node==null) {
			return;
		}
			map.put(node, parent);
			printAncestorsUtil(node.left,  node, map);
			printAncestorsUtil(node.right,node, map);
		
	}
	public static boolean findPairSum(TreeNodeRev node,int target) {
		HashSet<Integer> set = new HashSet<Integer>();
		return findPairSumUtil(node,target,set);
	}
	private static boolean findPairSumUtil(TreeNodeRev node, int target, HashSet<Integer> set) {
		if(node==null) {
			return false;
		}
		if(findPairSumUtil(node.left, target, set)) {
			return true;
		}
		if(set.contains(target-node.data)) {
			return true;
		}
		set.add(node.data);
		return findPairSumUtil(node.right, target, set);
	}
	public static boolean findPairSumItrative(TreeNodeRev root, int target) {
        if (root == null) return false;

        HashSet<Integer> set = new HashSet<>();
        Queue<TreeNodeRev> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNodeRev current = queue.poll();

            // check if complement exists
            if (set.contains(target - current.data)) {
                return true;
            }

            // add current node value to set
            set.add(current.data);

            // add children to queue
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }

        return false;
    }
	public static void removeKeys(TreeNodeRev node,int l,int r) {
		removeKeysUtil(node, l, r);
	}
	public static TreeNodeRev removeKeysUtil(TreeNodeRev node,int l,int r) {
		if(node==null) {
			return null;
		}
		node.left=removeKeysUtil(node.left, l, r);
		node.right=removeKeysUtil(node.right, l, r);
		if(node.data<l) {
			return node.right;
		}else if(node.data>r) {
			return node.left;
		}
		return node;
	}
	public static void findGreatestSum(TreeNodeRev node) {
		int[] sum=new int[1];
		greatestSum(node, sum);
	}
	public static void greatestSum(TreeNodeRev node,int[] sum) {
		if(node==null) {
			return;
		}
		greatestSum(node.right, sum);
		int temp=node.data;
		node.data=sum[0];
		sum[0] +=temp;
		greatestSum(node.left, sum);
		
	}
	public static void greatesSumItrative(TreeNodeRev node) {
		if(node==null) {
			return;
		}
		Stack<TreeNodeRev> stack = new Stack<TreeNodeRev>();
		TreeNodeRev current=node;
		int sum=0;
		
		while(!stack.isEmpty() || current !=null) {
			 while(current!=null) {
				 stack.push(current);
				 current=current.right;
			 }
			 current = stack.pop();
			 int temp=current.data;
			 current.data=sum;
			 sum +=temp;
			 current =current.left;
			 
		}
	}
	public static int getMaxSum(TreeNodeRev node) {
		IncExcludePair pair = getMaxSumUtil(node);
		return Math.max(pair.include, pair.exclude);
	}
	public static IncExcludePair getMaxSumUtil(TreeNodeRev node) {
		if(node==null) {
			return new IncExcludePair(0, 0);
		}
		IncExcludePair left = getMaxSumUtil(node.left);
		IncExcludePair right = getMaxSumUtil(node.right);
		int include=node.data+left.exclude+right.exclude;
		int exclude=Math.max(left.include, left.exclude) +Math.max(right.include, right.exclude);
		
		
		return new IncExcludePair(include, exclude);
	}
	
	  public static int largestBst(TreeNodeRev node) {
		  if(node==null) {
			  return 0;
		  }
		  if(isValidBST(node, Integer.MAX_VALUE, Integer.MIN_VALUE)) {
			  return size(node);
		  }
		  
		  return Math.max(largestBst(node.left) , largestBst(node.right)); 
	  }
	
	public static int size(TreeNodeRev node) {
		if(node==null) {
			return 0;
		}
		return 1+size(node.left)+size(node.right);
	}
	
	public static boolean isValidBST(TreeNodeRev node, int max ,int min) {
		if(node==null) {
			return true;
		}
		if(node.data<min || node.data>max) {
			return false;
		}
		
		return isValidBST(node.left, node.data, min) && isValidBST(node.right, max, node.data);
	}
	
	public static BstInfo largestBSt(TreeNodeRev node) {
		return largetBst1(node);
	}
	public static BstInfo largetBst1(TreeNodeRev node) {
		if(node==null) {
			return new BstInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
		}
		BstInfo left = largetBst1(node.left);
		BstInfo right = largetBst1(node.right);
		if(left.max<node.data && right.min>node.data) {
			return new BstInfo(Math.min(left.min, node.data), Math.max(right.max, node.data), 1+left.maxSize+right.maxSize);
		}
		return new BstInfo(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.maxSize, right.maxSize));
	}
	
	 public static void printExtremeNodes(TreeNodeRev node) {
		 if(node==null) {
			 return;
		 }
		 Queue<TreeNodeRev> queue= new LinkedList<TreeNodeRev>();
		 queue.add(node);
		 boolean leftToRight=true;
		 
		 while(!queue.isEmpty()) {
			 int size = queue.size();
			 for(int i=0;i<size;i++) {
				 TreeNodeRev current = queue.poll();
				 if((leftToRight && i==size-1) || (!leftToRight && i==0)) {
					 System.out.print(current.data +" ");
				 }
				 if(current.left!=null) {
					 queue.add(current.left);
				 }
				 if(current.right!=null) {
					 queue.add(current.right);
				 }
			 }
			 leftToRight =!leftToRight;
		 }
	 }
	 
	 public static void connectItrative(TreeNodeRev node) {
		 if(node==null) {
			 return;
		 }
		 Queue<TreeNodeRev> queue= new LinkedList<TreeNodeRev>();
		 queue.add(node);
		 
		 while(!queue.isEmpty()) {
			 int size = queue.size();
			 for(int i=0;i<size;i++) {
				 TreeNodeRev current = queue.poll();
				 System.out.print(current.data +" ");
				 if(current.left!=null) {
					 queue.add(current.left);
				 }
				 if(current.right!=null) {
					 queue.add(current.right);
				 }
			 }
			 System.out.print("#");
		 }
	 }
	 static void connectIterative(TreeNodeNextRight node) {
		    if (node == null) return;

		   Queue<TreeNodeNextRight> queue = new LinkedList<TreeNodeNextRight>();
		   queue.add(node);
		   
		   while(!queue.isEmpty()) {
			   int size =queue.size();
			   TreeNodeNextRight prev = null;
			   
			   for(int i=0;i<size;i++) {
				   TreeNodeNextRight current = queue.poll();
				   if(prev!=null) {
					   prev.nextRight=current;
				   }
				   prev=current;
				   // Add children for next level
		            if (current.left != null) {
		            	queue.offer(current.left);
		            }
		            if (current.right != null) {
		            	queue.offer(current.right);
		            }
			   }
			   if(prev!=null) {
				   prev.nextRight=null;
			   }
		   }
		}
	 public static void printNextRights(TreeNodeNextRight root) {
		    if (root == null) return;
		    TreeNodeNextRight levelStart = root;

		    while (levelStart != null) {
		        TreeNodeNextRight current = levelStart;
		        while (current != null) {
		            System.out.print(current.data + "->" +
		                (current.nextRight != null ? current.nextRight.data : "null") + "  ");
		            current = current.nextRight;
		        }
		        System.out.println();
		        levelStart = levelStart.left; // move to next level (for perfect binary tree)
		    }
		}
	 
	 
   public static int floor(TreeNodeRev node,int key) {
	   int[] floorVal= new int[1];
	   floorUtil(node,key,floorVal);
	   return floorVal[0];
   }
	 
	private static void floorUtil(TreeNodeRev node, int key, int[] floorVal) {
		if(node==null) {
			return;
		}
		TreeNodeRev current = node;
		while(current !=null) {
			if(current.data==key) {
				floorVal[0]=current.data;
				return;
			}else
			if(current.data>key) {
				current =current.left;
			}else if(current.data<key){
				floorVal[0]=current.data;
				current=current.right;
			}
		}
	
}
	
	public static int ceil(TreeNodeRev node,int key) {
		   int[] ceilVal= new int[1];
		   ceilVal[0] = -1; // Optional: if no ceil exists
		   ceilUtil(node,key,ceilVal);
		   return ceilVal[0];
	   }
		 
		private static void ceilUtil(TreeNodeRev node, int key, int[] ceilVal) {
			if(node==null) {
				return;
			}
			TreeNodeRev current = node;
			while(current !=null) {
				if(current.data==key) {
					ceilVal[0]=current.data;
					return;
				}else
				if(current.data>key) {
					ceilVal[0]=current.data;
					current =current.left;
				}else if(current.data<key){
					
					current=current.right;
				}
			}
		
	}
		
		public static int deleteNode(TreeNodeRev node,int data) {
			 return deleteNodeUtil(node, data).data;
		}


	private static TreeNodeRev deleteNodeUtil(TreeNodeRev node, int data) {
		if(node==null) {
			return null;
		}
		if(node.data>data) {
			node.left=deleteNodeUtil(node.left, data);
		}else if(node.data<data) {
			node.right=deleteNodeUtil(node.right, data);
		}else {
			  if(node.left==null) {
				  return node.right;
			  }else if(node.right==null) {
				  return node.left;
			  }else {
				  node.data=findMin(node.right);
				  node.right = deleteNodeUtil(node.right, node.data);
			  }
		}
		return node;
			
		}


	private static int findMin(TreeNodeRev node) {
		while(node.left!=null) {
			node=node.left;
		}
		return node.data;
	}
	public static TreeNodeRev deleteNodeBNT(TreeNodeRev node,int key) {
		return deleteNode1(node, key);
	}
	public static TreeNodeRev deleteNode1(TreeNodeRev node,int key) {
		if(node==null) {
			return null;
		}
		if(node.data==key) {
			if(node.left !=null) {
				//node=node.left;
				return node.left;
			}else {
				//node=node.right;
				return node.right;
			}
		}
	node.left=	deleteNode1(node.left, key);
	node.right=	deleteNode1(node.right, key);
		return node;
	}


	public static void main(String[] args) {
		TreeNodeRev root = new TreeNodeRev(8);
		root.left = new TreeNodeRev(4);
		root.right = new TreeNodeRev(12);
		root.left.left = new TreeNodeRev(2);
		root.left.right = new TreeNodeRev(6);
		root.right.left = new TreeNodeRev(10);
		root.right.right = new TreeNodeRev(14);

		levelOrder(root);
		deleteNodeBNT(root, 6);
		System.out.println();
		levelOrder(root);



	}


}
