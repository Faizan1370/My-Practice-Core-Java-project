package com.faizan.array.prac;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Ex8 {
	
	 public static void rotateArrayByK(int[] array, int k) {
		 int n=array.length;
		 if (array == null || n == 0 || k <= 0 || k >= n) {
	            System.out.println("Invalid input.");
	            return;
	        }
		 
		 k=k%n;
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
	 public static void findMasSumSubArray() {
		 int[] array = {3,5,-4,9,-10,7};
		 int currentMax=array[0];
		 int sumMax=array[0];
		 
		 for(int i=1;i<array.length;i++) {
			 currentMax=Math.max(array[i], array[i]+currentMax);
			 sumMax=Math.max(sumMax, currentMax);
		 }
		 System.out.println(sumMax);
		 
	 }
	 // Kaden's algo if sum vaues pos and negative will work fine but if alkl values nega wil not work
	 public static void findMasSumSubArray1() {
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
	 
	 public static int[] indices(int[] array,int target) {
		 HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		 for(int i=0;i<array.length;i++) {
			 int compliment=target-array[i];
			 if(map.containsKey(compliment)) {
				 return new int[] {map.get(compliment),i};
			 }else {
				 map.put(array[i], i);
			 }
		 }
		return null;
	 }
	 
	 public static void kthLargestElement() {
		 int[] array= {3,5,7,9,2};
		 int k=2;
		 PriorityQueue<Integer> integers = new PriorityQueue<Integer>();
		 
		 for(int i=0;i<array.length;i++) {
			 integers.add(array[i]);
			 if(integers.size()>k) {
				 integers.remove();
			 }
		 }
		 System.out.println(integers.peek());
	 }
	 
	 public static void kthLargestElement1() {
		 int[] array= {3,5,7,9,2};
		 int k=2;
		 Integer integer = Arrays.stream(array).mapToObj(num->(Integer)num).sorted(Comparator.reverseOrder()).skip(k-1).findFirst().get();
		 System.out.println(integer);
		 
	 }
	     
	 public static void insertElement() {
		 int[] array= {3,5,7,2,9};
		 int element=10;
		 int position=3;
		 
		 for(int i=array.length-1;i>position-1;i--) {
			 array[i]=array[i-1];
		 }
		 array[position-1]=element;
		 System.out.println(Arrays.toString(array));
	 }
	 
	 public static void deleteEmlement() {
		 int[] array= {3,6,8,1,9};
		 int delete=6;
		 for(int i=0;i<array.length;i++) {
			 if(array[i]==delete) {
				 for(int j=i;j<array.length-1;j++) {
					 array[j]=array[j+1];
				 }
			 }
		 }
		 System.out.println(Arrays.toString(array));
	 }
	 
	 public static void main(String[] args) {
		 deleteEmlement();
		// int[] array= {1,4,2,3,7,8,9,5};
		 //int target=5;
		 //System.out.println(Arrays.toString(indices(array, target))); ;
		// findMasSumSubArray1();
		//int[] array= {4,6,8,1,5,8};
		//int k=3;
		//rotateArrayByK(array, k);
	}

}
