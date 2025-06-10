package com.faizan.array.prac;

import java.util.Arrays;

public class Sort1 {
	public static void bubbleSort() {
		int[] array = {3,5,7,2,9,10};
		
		for(int i =0;i<array.length-1;i++) {
			for(int j=i+1;j<array.length;j++) {
				if(array[i]>array[j]) {
					int temp=array[i];
					array[i]=array[j];
					array[j]=temp;
				}
			}
		}
		
		System.out.println(Arrays.toString(array));
	}
	
	public static void selectionSort() {
		int[] array = {3,5,7,2,9,10};
		
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
		//bubbleSort();
		//selectionSort();
		insertionSort();
	}

}
