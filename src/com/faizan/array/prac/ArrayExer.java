package com.faizan.array.prac;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;


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
		 System.out.println(integers.peek());
	 }
	 
	 public static void unmodifiableArray() {
		 int[] a= {5,7,9,3,8};
		 List<int[]> unmodifiableList = Collections.unmodifiableList(Arrays.asList(a));
	 }
			
	public static void main(String[] args) {
		//rearangePosNegValue();
		//rightRotateByOneIndex(3);
		//checkPlaindrome();
		//reverseArray();
		//checkAmostronge();
		//showIndecesOfNum();
		kthLargestElement();
		
	}

}
