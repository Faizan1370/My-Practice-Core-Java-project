package com.faizan.array.prac;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Ex14 {
	public static void rotateByK(int[] nums, int k) {
		int n= nums.length;
		if(n<=k || k<=0 || n==0) {
			System.out.println("invalid input");
		}
		k=k%n;
		reverse(nums, 0, n-1);
		reverse(nums, 0, k-1);
		reverse(nums, k, n-1);
		System.out.println(Arrays.toString(nums));
		
	}
	
	private static void reverse(int[] nums,int start,int end) {
		while(start<end) {
			int temp=nums[start];
			nums[start]=nums[end];
			nums[end]=temp;
			start++;
			end--;
		}
	}
	public static void findMaxSumSubArray() {
		int[] array = { 3, -2, -3, 4, 7 };
		int currentSum=0,maxSum=0;
		
		for(int i=0;i<array.length;i++) {
			currentSum=currentSum+array[i];
			if(currentSum>maxSum) {
				maxSum=currentSum;
			}
			if(currentSum<0) {
				currentSum=0;
			}
		}
		System.out.println(maxSum);
	}
	public static void findMaxSumSubArray1() {
		int[] array = { 3, -2, -3, 4, 7 };
		int currentMax=0,maxSum=0;
		
		for(int i=0;i<array.length;i++) {
			currentMax = Math.max(array[i], currentMax+array[i]);
			maxSum=Math.max(maxSum, currentMax);
		}
		System.out.println(maxSum);
	}
	public static int[] indices(int[] array, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(int i=0;i<array.length;i++) {
			int compliment=target-array[i];
			if(map.containsKey(compliment)) {
				return new int[] {map.get(compliment),i};
			}else {
				map.put(array[i], i);
			}
		}
		return new int[] {-1,-1};
	}
	public static int[] twoSumSortedArray(int[] nums, int target) {
		int start=0,end=nums.length-1;
		
		while(start<end) {
			if(nums[start]+nums[end]==target) {
				return new int[] {start,end};	
			}
			else if((nums[start]+nums[end]>target)){
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
		PriorityQueue<Integer> queue= new PriorityQueue<Integer>((o1,o2)-> o2-o1);
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
		int[] result = new int[array.length];
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
		int[] result = new int[array.length];
		boolean[] seen= new boolean[256];
		int j=0;
		for(int i=0;i<array.length;i++) {
			if(!seen[array[i]]) {
				result[j]=array[i];
				seen[array[i]]=true;
				j++;
			}
		}
		System.out.println(Arrays.toString(result));
	}
	
	public static void removeDeuplicate2() {
		int[] array = { 7, 8, 3, 7, 4 };
		int[] result = new int[array.length];
		int[] seen= new int[256];
		int j=0;
		for(int i=0;i<array.length;i++) {
			if(seen[array[i]]==0) {
				result[j]=array[i];
				seen[array[i]]=1;
				j++;
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
					array[j]= array[j+1];
				}
				break;
			}
		}
		System.out.println(Arrays.toString(array));
		
	}
	 public static void selectionSort() {
   	  int[] array = { 3, 5, 7, 2, 9 };
   	  for(int i=0;i<array.length;i++) {
   		  int minIndex=i;
   		  for(int j=i+1;j<array.length;j++) {
   			  if(array[j]<array[minIndex]) {
   				  minIndex=j;
   			  }
   		  }
   		  int temp=array[i];
   		  array[i]=array[minIndex];
   		  array[minIndex]=temp;
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
