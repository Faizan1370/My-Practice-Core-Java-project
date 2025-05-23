package com.faizan.array.prac;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Ex2 {
	
	public static void insertValue() {
		int[] array = {32,4,6,12,89};
		int value=10;
		int position=3;
		
		for(int i=array.length-1;i>position-1;i--) {
			array[i]= array[i-1];
		}
		array[position-1]=value;
		
		System.out.println(Arrays.toString(array));
	}
	
	public static void deleteValue() {
		int[] array = {32,4,6,12,89};
		int value=6;
		for(int i=0;i<array.length;i++) {
			if(array[i]==value) {
				for(int j=i;j<array.length-1;j++) {
					array[j]=array[j+1];
				}
				break;
			}
		}
		System.out.println(Arrays.toString(array));
	}
	
	public static void removeDuplicate() {
		int[] array= {3,6,4,8,9,3,4};
		int[] resultArrray = new int[array.length];
		int k=0;
		
		for(int i=0;i<array.length;i++) {
			boolean isDuplicate=false;
			 for(int j=0;j<k;j++) {
				 if(array[i]==resultArrray[j]) {
					 isDuplicate=true;
				 }
			 }
			 if(!isDuplicate) {
				 resultArrray[k]=array[i];
				 k++;
			 }
		}
		//System.out.println(Arrays.toString(resultArrray));
		 System.out.println(Arrays.toString(Arrays.copyOf(resultArrray, k)));
				
		}
	
	public static void removeDuplicate1() {
		int[] array= {3,6,4,8,9,3,4};
		 boolean[] seen = new boolean[128]; // or larger if needed
		int[] resultArray= new int[array.length];
		int j=0;
		for(int i=0;i<array.length;i++) {
			if(!seen[array[i]]) {
				resultArray[j]=array[i];
				seen[array[i]]=true;
				j++;
			}
		}
		
		
		System.out.println(Arrays.toString(resultArray));
		
	}
	
	public static void removeDuplicate2() {
	    int[] array = {3, 6, 4, 8, 9, 3, 4};
	    int[] seen = new int[256]; // assuming elements are less than 256
	    int[] resultArray = new int[array.length];
	    int j = 0;

	    for (int i = 0; i < array.length; i++) {
	        if (seen[array[i]] == 0) {
	            resultArray[j] = array[i];
	            seen[array[i]] = 1; // mark as seen
	            j++;
	        }
	    }

	    System.out.println(Arrays.toString(resultArray));
	}
	
	public static void findIndicesForSum() {
		int[] array = {3,4,2,6,8,3};
		int target=6;
		Map<Integer,Integer> map = new HashMap<Integer, Integer>();
		for(int i=0;i<array.length;i++) {
			int component = target-array[i];
			if(map.containsKey(array[i])) {
				System.out.println(map.get(array[i]) +" "+i);
			}else {
				map.put(component, i);
			}
		}
	}
	
	public static void KthLargestValue() {
		int[] array = {3,6,2,8,4};
		int k=2;
		PriorityQueue<Integer> integers = new PriorityQueue<Integer>(); // it follows the binary tree concept mean min on top then right left
		//PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // greater on top then right left

		for(int i=0;i<array.length;i++) {
			System.out.println("**"+array[i]);
			integers.offer(array[i]);
			if(integers.size()>k) {
				System.out.println(array[i]);
				integers.poll();
			}
		}
		
		System.out.println(integers.peek());
		
	}
	
	public static void arrangePosNegtiveValue() {
		int[] array = {-1,4,8,-23,6,66,-25};
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

	
	public static void main(String[] args) {
		//insertValue();
		//deleteValue();
		//removeDuplicate();
		//removeDuplicate1();
		//removeDuplicate2();
		//findIndicesForSum();
		//KthLargestValue();
		arrangePosNegtiveValue();
	}
}
