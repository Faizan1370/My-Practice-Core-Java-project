package com.faizan.array.prac;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Ex10 {
	
	 public static void rotateArrayByK(int[] array, int k) {
		 int n=array.length;
		 if(array==null || k>=n || k<=0 || n==0) {
			 System.out.println("invlaid input");
		 }
		 k=k%n;
		 reverse(array, 0, n-1);
		 reverse(array, 0,k-1);
		 reverse(array, k, n-1);
		 System.out.println(Arrays.toString(array));
		 
	 }
	 
	 private static void reverse(int[] array ,int start,int end) {
		 while(start<end) {
			 int temp=array[start];
			 array[start]= array[end];
			 array[end]=temp;
			 start++;
			 end--;
		 }
		
	 }
	 public static void findMasSumSubArray() {
		 int[] array= {3,-2,-3,4,7};
		 int currentSum=0,maxSum=0;
		 
		 for(int i=0;i<array.length;i++) {
			 currentSum=Math.max(array[i], currentSum+array[i]);
			 maxSum=Math.max(maxSum, currentSum);
		 }
		 System.out.println(maxSum);
	 }
	 
	 public static void findMasSumSubArray1() {
		 int[] array= {3,-2,-3,4,7};
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
		 int k =2;
		 PriorityQueue<Integer> quue= new PriorityQueue<Integer>();
		 
		 for(int i=0;i<array.length;i++) {
			 quue.add(array[i]);
			 if(quue.size()>k) {
				 quue.remove();
			 }
		 }
		 System.out.println(quue.peek());
		 
	 }
	 
	 public static void kthMinElement() {
		 int[] array= {3,5,7,9,2};
		 int k =2;
		 PriorityQueue<Integer> quue= new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				
				return o2-o1;
			}
		});
		 
		 for(int i=0;i<array.length;i++) {
			 quue.add(array[i]);
			 if(quue.size()>k) {
				 quue.remove();
			 }
		 }
		 System.out.println(quue.peek());
		 
	 }
	 
	 public static void removeDuplicate() {
		 int[] array= {2,3,5,6,4,6};
		 int[] result = new int[array.length];
		 int[] seen= new int[256];
		 int j=0;
		 for(int i=0;i<array.length;i++) {
			 if(seen[array[i]]==0) {
				 seen[array[i]]=1;
				 result[j]=array[j];
				 j++;
			 }
		 }
		 System.out.println(Arrays.toString(result));
	 }
	 
	 public static void removeDuplicate1() {
		 int[] array= {2,3,5,6,4,6};
		 int[] result = new int[array.length];
		 boolean[] seen= new boolean[256];
		 int j=0;
		 for(int i=0;i<array.length;i++) {
			 if(!seen[array[i]]) {
				 seen[array[i]]=true;
				 result[j]=array[j];
				 j++;
			 }
		 }
		 System.out.println(Arrays.toString(result));
	 }
	 
	 public static void removeDuplicate3() {
		 int[] array= {2,3,5,6,4,6};
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
	 
	 public static void selectionSort() {
		 int[] array= {3,5,1,9,2,4};
		 
		 for(int i=0;i<array.length-1;i++) {
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
		 int[] array= {3,5,1,9,2,4};
		 
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
