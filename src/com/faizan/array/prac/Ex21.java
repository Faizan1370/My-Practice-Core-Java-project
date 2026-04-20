package com.faizan.array.prac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Ex21 {
	
	public static int maxLenTwoDistinct(int[] arr) {

	    Map<Integer, Integer> map = new HashMap<>();
	    int left = 0;
	    int maxLen = 0;

	    for (int right = 0; right < arr.length; right++) {

	        map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);

	        // If more than 2 distinct, shrink window
	        while (map.size() > 2) {
	            map.put(arr[left], map.get(arr[left]) - 1);

	            if (map.get(arr[left]) == 0) {
	                map.remove(arr[left]);
	            }
	            left++;
	        }

	        maxLen = Math.max(maxLen, right - left + 1);
	    }

	    return maxLen;
	}

	
	public static int[] rotateByK(int[] nums, int k) {
		int n=nums.length;
		
		//if(n<=k || n==0 || k<=0) {
			//System.out.println("invalid");
		//}
		if (n == 0) return nums;

	    k = k % n;
	    if (k == 0) return nums;
		reverse(nums,0,n-1);
		reverse(nums, 0, k-1);
		reverse(nums, k, n-1);
		return nums;
	}

	private static void reverse(int[] nums, int start, int end) {
	while(start<end) {
		int temp=nums[start];
		nums[start]=nums[end];
		nums[end]=temp;
		start++;
		end--;
	}
		
	}
	public static void findMaxSumSubArray() {
		int currentSum=0,maxSum=0;
		int[] array = { 3, -2, -3, 4, 7 };
		for(int i=0;i<array.length;i++) {
			currentSum = currentSum+array[i];
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
	    int currentSum = array[0];
	    int maxSum = array[0];

	    for (int i = 1; i < array.length; i++) {
	        currentSum = Math.max(array[i], currentSum + array[i]);
	        maxSum = Math.max(maxSum, currentSum);
	    }
		
	}
	public static ArrayList<int[]> indices(int[] array, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		ArrayList<int[]> list = new ArrayList<int[]>();
		for(int i=0;i<array.length;i++) {
			int compliment=target-array[i];
			  if (map.containsKey(compliment)) {
		            list.add(new int[]{map.get(compliment), i});
		        }
				map.put(array[i],i);
			
		}
		return list;
	}
	public static int[] twoSumSortedArray(int[] nums, int target) {
		int start=0,end=nums.length-1;
		while(start<end) {
			if(nums[start]+nums[end]==target) {
				return new int[] {start,end};
			}else if(nums[start]+nums[end]>target) {
				end--;
			}else {
				start++;
			}
		}
		return new int[] {-1,-1};
	}
	public static int kthLargest(int[] nums, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for(int i=0;i<nums.length;i++) {
			queue.add(nums[i]);
			if(queue.size()>k) {
				queue.poll();
			}
		}
		return queue.peek();
	}
	public static int kthSmallest(int[] nums, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>((a,b)->b-a);
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
			boolean isDup=false;
			for(int j=0;j<k;j++) {
				if(array[i]==result[j]) {
					isDup =true;
					break;
				}
			}
			if(!isDup) {
				result[k]=array[i];
				k++;
			}
		}
		System.out.println(Arrays.toString(result));
	}
	public static void removeDeuplicate1() {
		int[] array = { 7, 8, 3, 7, 4 };
		int[] result = new int[array.length];
		boolean[] seen = new boolean[101];
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
		int[] result = new int[array.length];
		int[] seen = new int[256];
		int k=0;
		for(int i=0;i<array.length;i++) {
			if(seen[array[i]] ==0) {
				result[k]=array[i];
				seen[array[i]]=1;
				k++;
			}
		}
		System.out.println(Arrays.toString(result));
		
	}
	public static void insertElement() {
		int[] array = { 3, 5, 7, 2, 9 };
		int element=6;
		int position =3;
		for(int i=array.length-1;i>position-1;i--) {
			array[i]=array[i-1];
		}
		array[position-1]=element;
		System.out.println(Arrays.toString(array));
		
	}
	public static void insertElement1() {
	    int[] array = {3, 5, 7, 2, 9};
	    int element = 6;
	    int position = 3;

	    int[] result = new int[array.length + 1];

	    for (int i = 0, j = 0; i < result.length; i++) {
	        if (i == position - 1) {
	            result[i] = element;
	        } else {
	            result[i] = array[j++];
	        }
	    }

	    System.out.println(Arrays.toString(result));
	}
	public static void deleteElement() {
		int[] array = { 3, 5, 7, 2, 9 };
		int delete =5;
		for(int i=0;i<array.length;i++) {
			if(array[i]==delete) {
				for(int j=i;j<array.length-1;j++) {
					array[j]=array[j+1];
				}
				break;
			}
		}
		System.out.println(Arrays.toString(array));
		
	}
	public static void movePosNeg() {
		int[] array = { -3, 5, -7, 2, 9 };
		int j=0;
		for(int i=0;i<array.length;i++) {
			if(array[i]<0) {
				if(i!=j) {
					int temp=array[i];
					array[i]=array[j];
					array[j]=temp;
				}
				j++;
			}
		}
		System.out.println(Arrays.toString(array));
		
	}
	public static void selectionSort() {
		int[] array = { 10, 5, 7, 2, 9 };
		
		for(int i=0;i<array.length;i++) {
			int minIndex=i;
			for(int j=i+1;j<array.length;j++) {
				if(array[minIndex]>array[j]) {
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
