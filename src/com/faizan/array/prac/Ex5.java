package com.faizan.array.prac;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Ex5 {
	
	
	public static void rearrangePosNeg() {
		int[] array = {2,-1,6,-9,-19,14,-17};
		int j=0;
		for(int i=0;i<array.length;i++) {
			if(array[i]>0) {
				array[j]=array[i];
				j++;
			}
		}
		for(int k=j;k<array.length;k++) {
			array[k]=-1;
		}
		System.out.println(Arrays.toString(array));
	}
	
	public static void rearrangePosNeg1() {
		int[] array = {2,-1,6,-9,-19,14,-17};
		int j=0;
		for(int i=0;i<array.length;i++) {
			if(array[i]>0) {
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
	
	public static void kthLargestValue() {
		int[] array= {2,5,8,2,9,3};
		int k=2;
		PriorityQueue<Integer> integers = new PriorityQueue<Integer>();
		for(int i=0;i<array.length;i++) {
			integers.offer(array[i]);
			if(integers.size()>k) {
				integers.poll();
			}
			
		}
		System.out.println(integers.peek());
	}
	

	public static void kthLargestValue1() {
		int[] array= {2,5,8,2,9,3};
		int k=2;
		
		Integer integer = Arrays.stream(array).mapToObj(num->(Integer)num)
		.sorted(Comparator.reverseOrder()).skip(k-1)
		.findFirst().get();
		System.out.println(integer);
		
	}
	
	public static void rotateArrayaByK(int[] array,int k) {
		int n=array.length;
		  if (array == null || n == 0 || k <= 0 || k >= n) {
	            System.out.println("Invalid input.");
	            return;
	        }
		  k=k%n;
		  //System.out.println(k);
		  reverse(array, 0, n-1);
		  reverse(array, 0, k-1);
		  reverse(array, k, n-1);
		  
		  System.out.println(Arrays.toString(array));
	}
	
	private static void reverse(int[] array,int start,int end) {
		while(start<end) {
			int temp=array[start];
			array[start]=array[end];
			array[end]=temp;
			
			start++;
			end--;
		}
		
	}
	
	public static void checkPalidrome(int num) {
		String str=num+"";
		boolean isPalin=false;
		int start=0;
		int end=str.length()-1;
		while(start<end) {
			if(str.charAt(start)!=str.charAt(end)) {
				isPalin=false;
				break;
			}else {
				isPalin =true;
			}
			start++;
			end--;
		}
		if(isPalin) {
			System.out.println("yes Palin");
		}else {
			System.out.println("not Palin");
		}
	}
	public static void checkPalidrome1(int num) {
		int temp=num;
		int sum=0;
		
		while(num>0) {
			int r=num%10;
			sum=sum*10+r;
			num=num/10;
		}
		if(sum==temp) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}
	}
	public static void findMaxSumSubArray() {
	    int[] array = {3, 5, -4, 9, -10, 7};
	    int currentMax=0;
	    int sumMax=0;
	    
	    for(int i=0;i<array.length;i++) {
	    	currentMax=Math.max(array[i], currentMax+array[i]);
	    	sumMax=Math.max(currentMax, sumMax);
	    }
	    System.out.println(sumMax);
	}
	
	 public static void showIndecesOfNum() {
		 int[] a= {5,7,3,6,9,2};
		 int target =8;
		 HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		 for(int i=0;i<a.length;i++) {
			 int compliment=target-a[i];
			 if(map.containsKey(a[i])) {
				 System.out.println(map.get(a[i]) + " "+i);
			 }else {
				 map.put(compliment, i);
			 }
		 }
		 
	 }
	 
	 public static void insertElement() {
		 int[] array = {4,6,1,98,6};
		 int element=3;
		 int position=3;
		 for(int i=array.length-1;i>position-1;i--) {
			 array[i]=array[i-1];
		 }
		 array[position-1]=element;
		 System.out.println(Arrays.toString(array));
	 }
	 
	 public static void deleteElement() {
		 int[] array = {4,6,1,98,6};
		 int delete=98;
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
	
	public static void main(String[] args) {
		deleteElement();
	}

}
