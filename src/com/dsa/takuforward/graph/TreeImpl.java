package com.dsa.takuforward.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.dsa.pract.TNode;

public class TreeImpl {
	
	public int maxWidth(TNode root) {
		if (root == null) return 0;

	    Queue<NodeIndexPair> q = new LinkedList<>();
	    q.add(new NodeIndexPair(root, 0));
	    
	    int maxWidth = 0;
	    while(!q.isEmpty()){
	    	int size=q.size();
	    	int minIndex=q.peek().index;// avoid overflow
	    	  int first = 0, last = 0;
	    	  
	    	  for(int i=0;i<size;i++) {
	    		  NodeIndexPair pair = q.poll();
	    		  int currIndex=pair.index-minIndex;
	    		  
	    		  if (i == 0) first = currIndex;
	              if (i == size - 1) last = currIndex;
	              
	              if(pair.node.left !=null) {
	            	  q.add(new NodeIndexPair(pair.node.left, 2*currIndex));
	              }
	              if(pair.node.right !=null) {
	            	  q.add(new NodeIndexPair(pair.node.right, 2*currIndex+1));
	              }
	    	  }
	    	  maxWidth = Math.max(maxWidth, last - first + 1);
	    }
		return maxWidth;

	}
	int maxSum=Integer.MIN_VALUE;
	 public int maxPathSum(TNode root) {
		 maxSumUtil(root);
		 return maxSum;
		 
	 }
	private int maxSumUtil(TNode root) {
		if(root==null) {
			return 0;
		}
		int leftSum =Math.max(0, maxSumUtil(root.left));
		int rightSum =Math.max(0, maxSumUtil(root.right));
		
		int currentSum=root.data+leftSum+rightSum;
		maxSum =Math.max(maxSum, currentSum);
		return root.data+Math.max(leftSum, rightSum);
		
	}
	public static int burnTreeBfs(TNode root,int target) {
		if(root==null) {
			return 0;
		}
		HashMap<TNode, TNode> parentMap = new HashMap<TNode, TNode>();
		TNode targetNode =buildParentMapBFS(root,target,parentMap);
		HashSet<TNode> visited = new HashSet<TNode>();
		int burnTime=0;
		Queue<TNode> q = new LinkedList<TNode>();
		q.add(targetNode);
		visited.add(targetNode);
		
		while(!q.isEmpty()) {
			int size =q.size();
			boolean burnFlag=false;
			
			for(int i=0;i<size;i++) {
				TNode current = q.poll();
				if(current.left !=null && !visited.contains(current.left)) {
					visited.add(current.left);
					q.add(current.left);
					burnFlag=true;
				}
				if(current.right !=null && !visited.contains(current.right)) {
					visited.add(current.right);
					q.add(current.right);
					burnFlag=true;
				}
				TNode parentNode=parentMap.get(current);
				if(parentNode !=null && !visited.contains(parentNode)) {
					visited.add(parentNode);
					q.add(parentNode);
					burnFlag=true;
				}
			}
			if(burnFlag) {
				burnTime++;
			}
		}
		return burnTime;
		
	}
	private static TNode buildParentMapBFS(TNode root, int target, HashMap<TNode, TNode> parentMap) {
		 Queue<TNode> queue = new LinkedList<TNode>();
		 queue.add(root);
		 TNode targetNode=null;
		 parentMap.put(root, null);
		 
		 while(!queue.isEmpty()) {
			 TNode current = queue.poll();
			 if(current.data==target) {
				 targetNode=current;
			 }
			 if(current.left !=null) {
				 parentMap.put(current.left, current);
				 queue.add(current.left);
			 }
			 if(current.right !=null) {
				 parentMap.put(current.right, current);
				 queue.add(current.right);
			 }
			 
		 }
		return targetNode;
	}
	int preIndex=0;
	 public TNode buildTree(int[] preorder, int[] inorder) {
		 HashMap<Integer, Integer> indexMap= new HashMap<Integer, Integer>();
		 for(int i=0;i<inorder.length;i++) {
			 indexMap.put(inorder[i], i);
		 }
		 return build(preorder,0,inorder.length-1,indexMap);
	 }
	private TNode build(int[] preorder, int start, int end, HashMap<Integer, Integer> indexMap) {
		if(start>end) {
			return null;
		}
		int rootVal=preorder[preIndex++];
		TNode rootNode= new TNode(rootVal);
		int index=indexMap.get(rootVal);
		rootNode.left=build(preorder, start, index-1, indexMap);
		rootNode.right = build(preorder, index+1, end, indexMap);
		return rootNode;
	}
	int postIndex=0;
	 public TNode buildTreeP(int[] postorder, int[] inorder) {
		 HashMap<Integer, Integer> indexMap= new HashMap<Integer, Integer>();
		 postIndex= postorder.length-1;
		 for(int i=0;i<inorder.length;i++) {
			 indexMap.put(inorder[i], i);
		 }
		 return buildP(postorder,0,inorder.length-1,indexMap);
	 }
	private TNode buildP(int[] postorder, int start, int end, HashMap<Integer, Integer> indexMap) {
		if(start>end) {
			return null;
		}
		int rootVal=postorder[postIndex--];
		TNode rootNode= new TNode(rootVal);
		int index=indexMap.get(rootVal);
		rootNode.right = buildP(postorder, index+1, end, indexMap);
		rootNode.left=buildP(postorder, start, index-1, indexMap);
		
		return rootNode;
	}
	
	public static void morrisInorder(TNode root) {
	    TNode curr = root;

	    while (curr != null) {

	        // Case 1: no left
	        if (curr.left == null) {
	            System.out.print(curr.data + " ");
	            curr = curr.right;
	        } 
	        else {
	            // find predecessor
	            TNode pred = curr.left;

	            while (pred.right != null && pred.right != curr) {
	                pred = pred.right;
	            }

	            // Case 2: create thread
	            if (pred.right == null) {
	                pred.right = curr;
	                curr = curr.left;
	            } 
	            else {
	                // remove thread
	                pred.right = null;
	                System.out.print(curr.data + " ");
	                curr = curr.right;
	            }
	        }
	    }
	}
	public static void morrisInorderP(TNode root) {
		TNode curr = root;
		
		while(curr !=null) {
			if(curr.left==null) {
				System.out.print(curr.data  +" ");
				curr=curr.right;
			}else {
				TNode pred =curr.left;
				while(pred.right !=null && pred.right!=curr) {
					pred =pred.right;
				}
				if(pred.right==null) {
					pred.right=curr;
					curr =curr.left;
				}else {
					pred.right=null;
					System.out.print(curr.data +" ");
					curr=curr.right;
				}
			}
		}
	}
	public TNode lcaBst(TNode root,TNode p,TNode q) {
		if(root==null) {
			return null;
		}
		int current =root.data;
		if(current<p.data && current<q.data) {
			return lcaBst(root.right,p,q);
		}
		if(current>p.data && current>q.data) {
			return lcaBst(root.left,p,q);
		}
		return root;
	}
	public TNode lcaBstIterative(TNode root, TNode p, TNode q) {
	    while (root != null) {

	        if (root.data < p.data && root.data < q.data) {
	            root = root.right;
	        } 
	        else if (root.data > p.data && root.data > q.data) {
	            root = root.left;
	        } 
	        else {
	            return root;
	        }
	    }
	    return null;
	}
	public String serialize(TNode root) {
		if(root==null) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		Queue<TNode> queue = new LinkedList<TNode>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TNode current = queue.poll();
			if(current==null) {
				builder.append("null, ");
				continue;
			}
			builder.append(current.data).append(",");
			queue.add(current.left);
			queue.add(current.right);
		}
		return builder.toString();
	}
	public TNode deserialize(String data) {
		if(data==null || data.isBlank()) {
			return null;
		}
		String[] values = data.split(",");
		TNode root= new TNode(Integer.parseInt(values[0]));
		
		Queue<TNode> queue = new LinkedList<TNode>();
		queue.add(root);
		int i=1;
		
		while(!queue.isEmpty()) {
			TNode parent = queue.poll();
			
			if(!values[i].equals("null")) {
				TNode left = new TNode(Integer.parseInt(values[i]));
				parent.left=left;
				queue.add(left);
			}
			i++;
			if(!values[i].equals("null")) {
				TNode right = new TNode(Integer.parseInt(values[i]));
				parent.right=right;
				queue.add(right);
			}
			i++;
			
		}
		return root;
	}
	

	    Stack<TNode> stack = new Stack<>();

	    public TreeImpl(TNode root) {
	        pushLeft(root);
	    }

	    private void pushLeft(TNode node) {
	        while (node != null) {
	            stack.push(node);
	            node = node.left;
	        }
	    }

	    public int next() {
	        TNode node = stack.pop();

	        // process right subtree
	        if (node.right != null) {
	            pushLeft(node.right);
	        }

	        return node.data;
	    }

	    public boolean hasNext() {
	        return !stack.isEmpty();
	    }
	    TNode prev=null;
	    public void flatten(TNode root) { // linked List
	    	if(root==null) {
	    		return;
	    	}
	    	flatten(root.right);
	    	flatten(root.left);
	    	
	    	root.right=prev;
	    	root.left=null;
	    	
	    	prev=root;
	    	
	    }
	    public void flattenItrative(TNode root) {
	        if (root == null) return;

	        Stack<TNode> stack = new Stack<>();
	        stack.push(root);

	        while (!stack.isEmpty()) {
	            TNode curr = stack.pop();

	            if (curr.right != null) stack.push(curr.right);
	            if (curr.left != null) stack.push(curr.left);

	            if (!stack.isEmpty()) {
	                curr.right = stack.peek();
	            }

	            curr.left = null;
	        }
	    }
	    
	    public void flattenMorris(TNode root) {
	        TNode curr = root;

	        while (curr != null) {

	            if (curr.left != null) {

	                TNode pred = curr.left;

	                while (pred.right != null) {
	                    pred = pred.right;
	                }

	                // connect
	                pred.right = curr.right;
	                curr.right = curr.left;
	                curr.left = null;
	            }

	            curr = curr.right;
	        }
	    }

}
