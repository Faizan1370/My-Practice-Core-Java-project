package com.faizan.array.prac;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Ex9 {
	 public static void rotateArrayByK(int[] array, int k) {
		 int n=array.length;
		 if(array==null || k>=n || k<=0 || n==0) {
			 System.out.println("invlaid input");
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
			 array[start] =array[end];
			 array[end]=temp;
			 start++;
			 end--;
			 }
	 }
	 
	 public static void findMasSumSubArray() {
		 int[] array= {3,-2,-3,4,7};
		 int currentmax=0;
		 int msxSum=0;
		 for(int i=0;i<array.length;i++) {
			 currentmax=Math.max(array[i], currentmax+array[i]);
			 msxSum=Math.max(msxSum, currentmax);
		 }
		 System.out.println(msxSum);
	 }
	// Kaden's algo if sum vaues pos and negative will work fine but if alkl values nega wil not work
		 public static void findMasSumSubArray1() {
			 int[] array= {3,-2,-3,4,7};
			 int cureentMax=0;
			 int maxSum =0;
			 
			 for(int i=0;i<array.length;i++) {
				 cureentMax=cureentMax+array[i];
				 if(cureentMax>maxSum) {
					 maxSum=cureentMax;
				 }
				 if(cureentMax<0) {
					 cureentMax=0;
				 }
			 }
			 System.out.println(maxSum);
			 
		 }
		 
		 public static int[] indices(int[] array,int target) {
			 HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			 for(int i=0;i<array.length;i++) {
				 int compliment = target-array[i];
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
			 PriorityQueue<Integer> integers = new PriorityQueue<Integer>();
			 
			 for(int i=0;i<array.length;i++) {
				 integers.add(array[i]);
				 if(integers.size()>k) {
					 integers.remove();
				 }
			 }
			 System.out.println(integers.peek());
			 
		 }
		 
		 public static void kthMinElement() {
			 int[] array= {3,5,7,9,2};
			 
			 PriorityQueue<Integer>  integers= new PriorityQueue<Integer>(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					
					return o2-o1;
				}
			});
			 int k =2;
			 for(int i=0;i<array.length;i++) {
				 integers.add(array[i]);
				 if(integers.size()>k) {
					 integers.remove();
				 }
			 }
			 System.out.println(integers.peek());
			 
		 }
		 
		 public static void insertElement() {
			 int[] array= {3,5,7,2,9};
			 int element=10;
			 int pos=3;
			 for(int i=array.length-1;i>pos-1;i--) {
				 array[i]=array[i-1];
			 }
			 array[pos-1]=element;
			 
			 System.out.println(Arrays.toString(array));
			 
		 }
		 
		 public static void deleteElement() {
			 int[] array= {4,6,8,2,5,90};
			 int delete=4;
			 for(int i=0;i<array.length;i++) {
				 if(array[i]==delete) {
					for(int j=i;j<array.length-1;j++) {
						 array[j]=array[j+1];
					}
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
			 String str= num+"";
			 int start=0;
			 int end= str.length()-1;
			  if(str.charAt(start)!=str.charAt(end)) {
				  return false;
			  }
			return true;
		 }
		 
		 public static boolean checkAmonstrong(int num) {
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
		 
		 public static void selectionSort() {
				int[] array = {3,5,7,2,9,10};
				for(int i=0;i<array.length-1;i++) {
					int minIndex=i;
					for(int j=i+1;j<array.length;j++) {
						if(array[j]<array[minIndex]) {
							minIndex=j;
						}
						int temp=array[i];
						array[i]=array[minIndex];
						array[minIndex]=temp;
					}
				}
				
				System.out.println(Arrays.toString(array));
				
		 }
		 public static void insertionSort() {
			 int[] array = {3,5,7,2,9,10};
			 
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
		// selectionSort();
		 //int num=155;
		// System.out.println(checkAmonstrong(num));
		 //deleteElement();
		 //insertElement();
		 //kthMinElement();
		 //kthLargestElement();
		 //int[] array= {3,8,5,-1,65,4,3};
		 //int target=4;
		 //System.out.println(Arrays.toString(indices(array, target)));
		 findMasSumSubArray();
		 int[] array= {5,8,1,6,82,6,87};
		// int k=3;
		//rotateArrayByK(array, k);
	}

}
