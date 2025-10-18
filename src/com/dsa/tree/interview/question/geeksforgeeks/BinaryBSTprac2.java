package com.dsa.tree.interview.question.geeksforgeeks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class BinaryBSTprac2 {

	TreeNod root;

	public boolean printAncestors(TreeNod node, int key) {
		if (node == null) {
			return false;
		}
		if (node.data == key) {
			return true;
		}
		if (printAncestors(node.left, key) || printAncestors(node.right, key)) {
			System.out.print(node.data + " ");
			return true;
		}
		return false;
	}

	public void printAncestorsItrative(int key) {
		if (root == null) {
			return;
		}
		Stack<TreeNod> stack = new Stack<TreeNod>();
		HashMap<TreeNod, TreeNod> parentMap = new HashMap<TreeNod, TreeNod>();
		stack.push(root);
		parentMap.put(root, null);
		TreeNod keyNode = null;

		while (!stack.isEmpty()) {
			TreeNod current = stack.pop();

			if (current.data == key) {
				keyNode = current;
				break;
			}
			if (current.right != null) {
				stack.push(current.right);
				parentMap.put(current.right, current);
			}
			if (current.left != null) {
				stack.push(current.left);
				parentMap.put(current.left, current);
			}
		}
		// ✅ print ancestors only if key was actually found
		if (keyNode != null) {
			TreeNod parentNode = parentMap.get(keyNode);
			while (parentNode != null) {
				System.out.print(parentNode.data + " ");
				parentNode = parentMap.get(parentNode);
			}
		} else {
			System.out.println("Key not found in the tree!");
		}

	}
	

	public TreeNod removeKeys(TreeNod node, int l, int r) {
		if (node == null) {
			return null;
		}
		TreeNod left = removeKeys(node.left, l, r);
		TreeNod right = removeKeys(node.right, l, r);

		if (node.data >= l && node.data <= r) {
			node.left = left;
			node.right = right;

			return node;
		} else if (node.data < l) {
			return right;
		} else {
			return left;
		}
	}
	public boolean checkPairSum(int target) {
		HashSet<Integer> set = new HashSet<Integer>();
		return findPair(root, target, set);
	}
	
	public boolean findPair(TreeNod node,int target,HashSet<Integer> set) {
		if(node==null) {
			return false;
		}
		if(findPair(node.left, target, set)) {
			return true;
		}
		if(set.contains(target-node.data)) {
			System.out.println("Pair found: " + node.data + " + " + (target - node.data));
			return true;
		}
		set.add(node.data);
		
		return findPair(node.right, target, set);
	}
	int sum=0;
	public void findGreatestSum() {
		greatestSum(root);
	}
	public void greatestSum(TreeNod node) {
		if(node==null) {
			return;
		}
		greatestSum(node.right);
		int temp=node.data;
		node.data=sum;
		sum +=temp;
		greatestSum(node.left);
	}
	public void updateTree() {
		updateTree(root);
	}
	public void updateTree(TreeNod node) {
		if(node==null) {
			return;
		}
		updateTree(node.right);
		
		sum +=node.data;
		node.data=sum-node.data;
		updateTree(node.left);
	}
	public void printInorder(TreeNod node) {
	    if(node == null) return;
	    printInorder(node.left);
	    System.out.print(node.data + " ");
	    printInorder(node.right);
	}
	
	Map<TreeNod, Integer> sumMap = new HashMap<>();
	void storeSum(TreeNod node) {
	    if (node == null) return;
	    storeSum(node.right);
	    sum += node.data;
	    sumMap.put(node, sum);
	    storeSum(node.left);
	}

	void updateTree1(TreeNod node) {
	    if (node == null) return;
	    updateTree(node.right);
	    node.data = sumMap.get(node) - node.data;
	    updateTree(node.left);
	}
	
	void transformToGreaterSumTree(TreeNod root) {
        if (root == null) return;

        Stack<TreeNod> stack = new Stack<>();
        TreeNod curr = root;
        int sum = 0;

        while (!stack.isEmpty() || curr != null) {
            // Step 1: Go as far right as possible
            while (curr != null) {
                stack.push(curr);
                curr = curr.right;
            }

            // Step 2: Process the node
            curr = stack.pop();
            int temp = curr.data;
            curr.data = sum;
            sum += temp;

            // Step 3: Move to left subtree
            curr = curr.left;
        }
    }
	
	public IncludeExcludePair maxSum(TreeNod node) {
		if(node==null) {
			return new IncludeExcludePair(0, 0);
		}
		IncludeExcludePair left = maxSum(node.left);
		IncludeExcludePair right = maxSum(node.right);
		int include = node.data+left.exclude+right.exclude;
		  int exclude = Math.max(left.include, left.exclude) + Math.max(right.include, right.exclude);
		  
		  return new IncludeExcludePair(include, exclude);
	}
	
	public int getMaxSum(TreeNod root) {
		IncludeExcludePair pair = maxSum(root);
		return Math.max(pair.include, pair.exclude);
	}
	
	public boolean isValidBST(TreeNod node,int min,int max) {
		if(node==null) {
			return true;
		}
		if(node.data<min || node.data>max) {
			return false;
		}
		return isValidBST(node.left, min, node.data) && isValidBST(node.right, node.data, max);
	}
	
	public int size(TreeNod node) {
		if(node==null) {
			return 0;
		}
		return 1+Math.max(size(node.left), size(node.right));
	}
	
	public int largestBst(TreeNod node) {
		if(node==null) {
			return 0;
		}
		if(isValidBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
			return size(node);
		}
		return Math.max(largestBst(node.left), largestBst(node.right));
	}
	
	
	  public BSTInfo largestBSTBT(TreeNod node) {
		  if(node==null) {
			  return new BSTInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
		  }
		  BSTInfo left= largestBSTBT(node.left);
		  BSTInfo right = largestBSTBT(node.right);
		  
		  if(left.max<node.data && right.min>node.data) {
			  return new BSTInfo(Math.min(left.min, node.data), Math.max(right.max, node.data),1+left.maxSz+right.maxSz);
		  }
		  
		  return new BSTInfo(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.maxSz, right.maxSz));
	  }
	  
	  public int largestBstOpti(TreeNod node) {
		  if(node==null) {
			  return 0;
		  }
		  return largestBSTBT(node).maxSz;
	  }
     public void printExtremeNodes(TreeNod node) {
    	 if(node==null) {
    		 return;
    	 }
    	 Queue<TreeNod> queue = new LinkedList<TreeNod>();
    	 queue.add(node);
    	 boolean printRight=true;
    	 
    	 while(!queue.isEmpty()) {
    		 int size = queue.size();
    		 for(int i=0;i<size;i++) {
    			 TreeNod currrent = queue.poll();
    			 if(currrent.left!=null) {
    				 queue.add(currrent.left);
    			 }
    			 if(currrent.right!=null) {
    				 queue.add(currrent.right);
    			 }
    			 if((printRight && i==size-1) || (!printRight && i==0)) {
    				 System.out.print(currrent.data +" ");
    			 }
    		 }
    		 printRight=!printRight;
    	 }
     }
     
     public void connect(TreeNodeNextRight node) {
    	 if(node==null) {
    		 return;
    	 }
    	 node.nextRight=null;
    	 
    	 connectRecursion(node);
     }


	private void connectRecursion(TreeNodeNextRight node) {
		if(node==null) {
			return;
		}
		if(node.left!=null) {
			node.left.nextRight=node.right;
		}
		if(node.right!=null) {
			node.right.nextRight=(node.nextRight !=null)?node.nextRight.left:null;
		}
		connectRecursion(node.left);
		connectRecursion(node.right);
	}
	
	 static java.util.List<String> getNextRightArray(TreeNodeNextRight root) {
	        java.util.List<String> result = new java.util.ArrayList<>();
	        if (root == null) return result;

	        java.util.Queue<TreeNodeNextRight> queue = new java.util.LinkedList<>();
	        queue.offer(root);
	        queue.offer(null);  

	        while (!queue.isEmpty()) {
	        	TreeNodeNextRight node = queue.poll();

	            if (node != null) {
	              
	                // Add the current node's data
	                result.add(Integer.toString(node.data));

	                // If nextRight is null, add '#'
	                if (node.nextRight == null) {
	                    result.add("#");
	                }

	                // Push the left and right children to the 
	              	// queue (next level nodes)
	                if (node.left != null) queue.offer(node.left);
	                if (node.right != null) queue.offer(node.right);
	            } else if (!queue.isEmpty()) {
	              
	                // Add level delimiter for the next level
	                queue.offer(null);
	            }
	        }

	        return result;
	    }
	 
	 static void connectIterative(TreeNodeNextRight root) {
		    if (root == null) return;

		    Queue<TreeNodeNextRight> queue = new LinkedList<>();
		    queue.offer(root);

		    while (!queue.isEmpty()) {
		        int size = queue.size();
		        TreeNodeNextRight prev = null;

		        for (int i = 0; i < size; i++) {
		            TreeNodeNextRight curr = queue.poll();

		            // Connect previous node to current
		            if (prev != null) prev.nextRight = curr;
		            prev = curr;

		            // Add children for next level
		            if (curr.left != null) queue.offer(curr.left);
		            if (curr.right != null) queue.offer(curr.right);
		        }

		        // Last node in level → nextRight = null
		        if (prev != null) prev.nextRight = null;
		    }
		}


	public static void main(String[] args) {
		 BinaryBSTprac2 tree = new BinaryBSTprac2();
		 TreeNodeNextRight root = new TreeNodeNextRight(1);
		 root.left = new TreeNodeNextRight(2);
		 root.right = new TreeNodeNextRight(3);

		 root.left.left = new TreeNodeNextRight(4);
		 root.left.right = new TreeNodeNextRight(5);

		 root.right.left = new TreeNodeNextRight(6);
	     tree.connect(root);
	     java.util.List<String> output = getNextRightArray(root);
	        for (String s : output) {
	            System.out.print(s + " ");
	        }
	        System.out.println();

	}
}
