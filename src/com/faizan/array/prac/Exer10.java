package com.faizan.array.prac;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Exer10 {
	 public static void rotateArrayByK(int[] array, int k) {
		 int n= array.length;
       if(n<=k || array==null || k<=0) {
    	   System.out.println("invalid input");
       }
       k=k%n;
       reverse(array, 0, n-1);
       reverse(array, 0, k-1);
       reverse(array, k, n-1);
       System.out.println(Arrays.toString(array));
		 
	 }
	 public static void reverse(int[] array,int start,int end) {
		 while(start<end) {
			 int temp=array[start];
			 array[start]=array[end];
			 array[end]=temp;
			 start++;
			 end--;
		 }
	 }
	 
	 public static void findMasSumSubArray() {
		 int[] array= {3,-2,-3,4,7};
		 int currentSum=0;
		 int maxSum=0;
		 
		 for(int i=0;i<array.length;i++) {
			 currentSum=Math.max(array[i], currentSum+array[i]);
			 maxSum=Math.max(maxSum, currentSum);
		 }
		 System.out.println(maxSum);
		 
	 }
	 public static void findMasSumSubArray2() {
		 int[] array= {3,-2,-3,4,7};
		 int currentSum=0;
		 int maxSum=0;
		 
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
	 public static int[] indices(int[] array,int target) {
		 HashMap<Integer, Integer> hashMap= new HashMap<Integer, Integer>();
		 for(int i=0;i<array.length;i++) {
			 int comliment=target-array[i];
			 if(hashMap.containsKey(comliment)) {
				 return new int[] {hashMap.get(comliment),i};
			 }else {
				 hashMap.put(array[i], i);
			 }
		 }
		return null;
	 }
	 public static void kthLargestElement() {
		 int[] array= {3,5,7,9,2};
		 int k =2;
		 
		 PriorityQueue<Integer> integers = new PriorityQueue<Integer>();
		 for(int i=0;i<array.length;i++) {
			 integers.add(array[i]);
			 if(integers.size()>k) {
				 integers.poll();
			 }
		 }
		 
		 System.out.println(integers.peek());
		 
	 }
	 
	 public static void kthMinElement() {
		 int[] array= {3,5,7,9,2};
		 int k =2;
		 
		 PriorityQueue<Integer> integers = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}
			 
		});
		 for(int i=0;i<array.length;i++) {
			 integers.add(array[i]);
			 if(integers.size()>k) {
				 integers.poll();
			 }
		 }
		 
		 System.out.println(integers.peek());
		 
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
	 
	 public static void deleteElement() {
		 int[] array= {3,5,7,2,9};
		 int delete=2;
		 
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
	 public static boolean palin(int num) {
		 int temp=num;
		 int sum=0;
		 
		 while(num>0) {
			 int r=num%10;
			 sum=sum*10+r;
			 num=num/10;
		 }
		 if(temp==sum) {
			 return true;
		 }
		return false;
	 }
	 public static boolean palin1(int num) {
		 String str=num+"";
		 int start=0;
		 int end=str.length()-1;
		 while(start<end) {
			 if(str.charAt(end) !=str.charAt(start)) {
				 return false;
			 }
			 start++;
			 end--;
		 }
		return true;
	 }
	 public static boolean amos(int num) {
		 int temp=num;
		 int sum=0;
		 
		 while(num>0) {
			 int r=num%10;
			 sum=sum+r*r*r;
			 num=num/10;
		 }
		 if(temp==sum) {
			 return true;
		 }
		return false;
	 }
	 
	 public static void removeDeuplicate() {
		 int[] array= {3,5,7,1,3,4,7};
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
		 int[] array= {3,5,7,1,3,4,7};
		 int[] result= new int[array.length];
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
		 int[] array= {3,5,7,1,3,4,7};
		 int[] result= new int[array.length];
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
	 
	 public static void selectionSort() {
		int[] array= {5,7,1,3,4};
		 //int[] array = {3,5,7,2,9,10};
		 
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
		 int[] array = {5,7,1,3,4};
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
