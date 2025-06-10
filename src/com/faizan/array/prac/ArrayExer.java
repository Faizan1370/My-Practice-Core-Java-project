package com.faizan.array.prac;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;


public class ArrayExer {
	
	public static void rearangePosNegValue() {
		int[] a= {-4,6,-1,3,-6,8,-2};
		int j=0;
		for(int i=0;i<a.length;i++) {
			if(a[i]<0) {
				if(i!=j) {
				int temp=a[i];
				a[i]=a[j];
				a[j]=temp;
				}
				j++;
			}
			
		}
		System.out.println(Arrays.toString(a));
	}
	
	 public static void findKthLargest1() {
	    	int[] array= {3,5,7,9,2};
	    	int k=2;
	    	 Integer integer = Arrays.stream(array)
	    	 .mapToObj(num->(Integer)num)
	    	 .sorted(Comparator.comparingInt(num->((Integer) num).intValue()).reversed())
	    	 .skip(k-1)
	    	 .findFirst()
	    	 .get();
	    	 System.out.println(integer);
	    }

	
	 public static void rotateArrayByK(int[] array, int k) {
	        int n = array.length;
	        if (array == null || n == 0 || k <= 0 || k >= n) {
	            System.out.println("Invalid input.");
	            return;
	        }

	        // Normalize k in case it's > n
	        k = k % n;

	        // Step 1: Reverse whole array
	        reverse(array, 0, n - 1);
	        // Step 2: Reverse first k elements
	        reverse(array, 0, k - 1);
	        // Step 3: Reverse remaining n-k elements
	        reverse(array, k, n - 1);
	        System.out.println(Arrays.toString(array));
	    }

	    private static void reverse(int[] array, int start, int end) {
	        while (start < end) {
	            int temp = array[start];
	            array[start++] = array[end];
	            array[end--] = temp;
	        }
	    }
	
	public static void rightRotateByOneIndex(int k) {
		int[] array= {4,6,1,3,6,8,2};
		int lastElement = array[array.length-1];
		for(int i=array.length-1;i>0;i--) {
			array[i]=array[i-1];
		}
		array[0]= lastElement;
		System.out.println(Arrays.toString(array));
	}
	
	public static boolean checkPlaindrome() {
		int num=111;
		boolean isPalindrome=false;
		int temp=num;
		int sum=0;
		while(num>0) {
			int r = num%10;
			sum =sum*10+r;
			num=num/10;
		}
		if(temp==sum) {
			isPalindrome=true;
		}
		System.out.println("Palindrome :"+isPalindrome);
		return isPalindrome;
	}
	 public static void reverseArray() {
		 int[] a= {6,8,2,9,3,5};
		 int start=0;
		 int end = a.length-1;
		 while(end>start) {
			 int temp=a[start];
			 a[start] = a[end];
			 a[end]= temp;
			 end--;
			 start++;
		 }
		 System.out.println(Arrays.toString(a));
				 
	 }
	 public static boolean checkAmostronge() {
			int num=151;
			boolean isAmontronge=false;
			int sum=0;
			int temp=num;
			while(num>0) {
				int r= num%10;
				sum =sum+r*r*r;
				num=num/10;
			}
			if(temp==sum) {
				isAmontronge=true;
			}
			System.out.println("Amons :"+isAmontronge);
			return isAmontronge;
					
			
	 }
	 public static void showIndecesOfNum() {
		 int[] a= {5,7,3,6,9,2};
		 int target =8;
		 HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		 for(int i=0;i<a.length;i++) {
			 int component = target-a[i];
			 if(map.containsKey(a[i])) {
				 System.out.println("Pair found at indices: " + map.get(a[i]) + " and " + i);
			 }else {
				 map.put(component, i);
			 }
		 }
		 
	 }
	 
	 public static void kthLargestElement() {
		 int[] a= {1,6,7,9};
		 int k=2;
		 PriorityQueue<Integer> integers = new PriorityQueue<Integer>();
		 for(int i=0;i<a.length;i++) {
			 integers.offer(a[i]);
			 if(integers.size()>k) {
				 System.out.println(a[i]);
				 integers.poll();
			 }
		 }
		 System.out.println(integers);
		 System.out.println(integers.peek());  //use to get zero index value
	 }
	 
	 public static void unmodifiableArray() {
		 int[] a= {5,7,9,3,8};
		 List<int[]> unmodifiableList = Collections.unmodifiableList(Arrays.asList(a));
	 }
	 
	 public static void insertElement() {
		 int[] array= {5,6,1,8,0,3};
		 int element =10;
		 int position=3;
		 for(int i =array.length-1;i>position-1;i--) {
			 array[i]=array[i-1];
		 }
		 array[position-1] = element;
		 System.out.println(Arrays.toString(array));
	 }
	 
	 public static void deleteElement() {
		 int[] array= {5,6,1,8,0,3};
		 int element =8;
		 for(int i=0;i<array.length;i++) {
			 if(array[i]==element) {
				for(int j=i;j<array.length-1;j++) {
					array[j]=array[j+1];
				}
				break;
			 }
			// break;
		 }
		 System.out.println(Arrays.toString(array));
	 }
	 
	 
	 // Kaden's algo if sum vaues pos and negative will work fine but if alkl values nega wil not work
	 public static void findMasSumSubArray() {
		 int[] array = {3,5,-4,9,-10,7};
		 
		 int currentMax=0;
		 int sumMax=0;
		 for(int i=0;i<array.length;i++) {
			 currentMax=currentMax+array[i];
			 if(currentMax>sumMax) {
				 sumMax=currentMax;
			 }
			 if(currentMax<0) {
				 currentMax=0;
			 }
		 }
		 System.out.println("sum Max :"+sumMax);
	 }
	 
	 
	 //This is final code which will work for all pos or nega values
	 public static void findMaxSumSubArray() {
		    int[] array = {3, 5, -4, 9, -10, 7};

		    int currentMax = array[0];
		    int sumMax = array[0];

		    for (int i = 1; i < array.length; i++) {
		        currentMax = Math.max(array[i], currentMax + array[i]);
		        sumMax = Math.max(sumMax, currentMax);
		    }

		    System.out.println("sum Max: " + sumMax);
		}
	 
	 public static int[] nextGreaterElements() {
		 	int[] nums= {1,5,3,9,7};
		    Stack<Integer> stack = new Stack<>();
		    int[] result = new int[nums.length];

		    for (int i = nums.length - 1; i >= 0; i--) {
		        while (!stack.isEmpty() && stack.peek() <= nums[i]) {
		            stack.pop();
		        }
		        result[i] = stack.isEmpty() ? -1 : stack.peek();
		        stack.push(nums[i]);
		    }
		    System.out.println(Arrays.toString(result));
		    return result;
		}


			
	public static void main(String[] args) {
		//rearangePosNegValue();
		//rightRotateByOneIndex(3);
		//checkPlaindrome();
		//reverseArray();
		//checkAmostronge();
		//showIndecesOfNum();
		//kthLargestElement();
		//insertElement();
		//deleteElement();
		//findMaxSumSubArray();
		nextGreaterElements();
		
	}

}
