package com.faizan.array.prac;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Ex16 {
	public static int[] rotateByK(int[] nums,int k) {
		int n= nums.length;
		if(n==0 || k<=0 || n<k) {
			System.out.println("Invalid input");
		}
		k=k%n;
		revesre(nums, 0, n-1);
		revesre(nums, 0, k-1);
		revesre(nums, k,n-1);
		
		return nums;
	}
	
	private static void revesre(int[] nums,int start,int end) {
		while(start<end) {
			int tmp=nums[start];
			nums[start]=nums[end];
			nums[end]=tmp;
			start++;
			end--;
		}
	}
	public static void findMaxSumSubArray() {
		int[] array = { 3, -2, -3, 4, 7 };
		int currntMax=0,maxSum=0;
		
		for(int i=0;i<array.length;i++) {
			currntMax=currntMax+array[i];
			if(currntMax>maxSum) {
				maxSum=currntMax;
			}
			if(currntMax<0) {
				currntMax=0;
			}
		}
		System.out.println(maxSum);
		
	}
	public static void findMaxSumSubArray1() {
		int[] array = { 3, -2, -3, 4, 7 };
		int currntMax=0,maxSum=0;
		for(int i=0;i<array.length;i++) {
			currntMax=Math.max(array[i], currntMax+array[i]);
			maxSum=Math.max(maxSum, currntMax);
		}
		System.out.println(maxSum);
		
	}
	public static int[] indices(int[] array, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(int i=0;i<array.length;i++) {
			int compliment=target-array[i];
			if(map.containsKey(compliment)) {
				return new int[] {map.get(compliment),i};
			}
			map.put(array[i], i);
		}
		
		return new int[] {-1,-1};
		
	}
	public static int[] twoSumSortedArray(int[] nums, int target) {
		int start=0,end=nums.length-1;
		while(start<end) {
			if(target==(nums[start]+nums[end])) {
				return new int[] {start,end};
			}else if(target<(nums[start]+nums[end])) {
				end--;
			}else {
				start++;
			}
		}
		return new int[] {-1,-1};
		
	}
	public static int kthLargest(int[] nums, int k) {
		PriorityQueue<Integer> queue= new PriorityQueue<Integer>();
		for(int i=0;i<nums.length;i++) {
			queue.add(nums[i]);
			if(queue.size()>k) {
				queue.poll();
			}
		}
		
		return queue.peek();
		
	}
	
	public static int kthSmallest(int[] nums, int k) {
		PriorityQueue<Integer> queue= new PriorityQueue<Integer>((o1,o2)->o2-o1);
		for(int i=0;i<nums.length;i++) {
			queue.add(nums[i]);
			if(queue.size()>k) {
				queue.poll();
			}
		}
		
		return queue.peek();
		
	}
	public static void removeDeuplicate() {
		int[] array = { 7, 8, 3, 7, 4 };
		int[] result= new int[array.length];
		int k=0;
		for(int i=0;i<array.length;i++) {
			boolean isDuplicate=false;
			for(int j=0;j<k;j++) {
				if(array[i]==result[j]) {
					isDuplicate=true;
					break;
				}
			}
			if(!isDuplicate) {
				result[k]=array[i];
				k++;
			}
		}
		System.out.println(Arrays.toString(result));
		
	}
	public static void removeDeuplicate1() {
		int[] array = { 7, 8, 3, 7, 4 };
		int[] result= new int[array.length];
		boolean[] seen=new boolean[256];
		
		int k=0;
		for(int i=0;i<array.length;i++) {
			if(!seen[array[i]]) {
				result[k]=array[i];
				seen[array[i]]=true;
				k++;
			}
		}
		System.out.println(Arrays.toString(result));
		
	}
	
	public static void removeDeuplicate2() {
		int[] array = { 7, 8, 3, 7, 4 };
		int[] result= new int[array.length];
		int[] seen=new int[256];
		
		int k=0;
		for(int i=0;i<array.length;i++) {
			if(seen[array[i]]==0) {
				result[k]=array[i];
				seen[array[i]]=1;
				k++;
			}
		}
		System.out.println(Arrays.toString(result));
		
	}
	public static void insertElement() {
		int[] array = { 3, 5, 7, 2, 9 };
		int element = 10;
		int position = 3;
		
		for(int i=array.length-1;i>position-1;i--) {
			array[i]=array[i-1];
		}
		array[position-1]=element;
		System.out.println(Arrays.toString(array));
		
	}
	public static void deleteElement() {
		int[] array = { 3, 5, 7, 2, 9 };
		int delete = 2;
		
		for(int i=0;i<array.length;i++) {
			if(array[i]==delete) {
				for(int j=i;j<array.length-1;j++) {
					 array[j+1]=array[j];
				}
				break;
			}
		}
		System.out.println(Arrays.toString(array));
		
	}
	
	public static void movePosNeg() {
		int[] array = { -3, 5, -7, 2, 9 };
		int j=array.length-1;
		for(int i=0;i<=j;i++) {
			if(array[i]<0) {
					int tmp=array[i];
					array[i]=array[j];
					array[j]=tmp;
				j--;
			}
		
		}
		System.out.println(Arrays.toString(array));
	}
	 public static void selectionSort() {
	   	  int[] array = { 10, 5, 7, 2, 9 };
	   	  for(int i=0;i<array.length;i++) {
	   		  int minIndex=i;
	   		  for(int j=i+1;j<array.length;j++) {
	   			  if(array[j]<array[minIndex]) {
	   				  minIndex=j;
	   			  }
	   		  }
	   		  int tmp=array[i];
	   		  array[i]=array[minIndex];
	   		  array[minIndex]=tmp;
	   	  }
	   	  
	   	System.out.println(Arrays.toString(array));
	   	  
	 }
	 
	 public static void insertionSort() {
	   	  int[] array = { 3, 5, 7, 2, 9 };
	   	  
	   	  for(int i=1;i<array.length;i++) {
	   		  int key=array[i];
	   		  int j=i-1;
	   		  while(j>=0 && array[j]>key) {
	   			  array[j+1]=array[j];
	   			  j--;
	   		  }
	   		  array[j+1]=key;
	   	  }
	   	System.out.println(Arrays.toString(array));
	   	  
	 }
		
	
	public static void main(String[] args) {
		insertionSort();		
	}

}
