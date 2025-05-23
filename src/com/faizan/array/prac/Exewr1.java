package com.faizan.array.prac;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Exewr1 {
	public static void moveALLzeroLeft() {
		int[] arrar = {4,8,0,3,0,12,0};
		
		int j=0;
		for(int i=0;i<arrar.length;i++) {
			if(arrar[i]==0) {
				if(i!=j) {
					int temp=arrar[i];
					arrar[i]=arrar[j];
					arrar[j]=temp;
				}
				
				j++;
			}
		}
		System.out.println(Arrays.toString(arrar));
		
	}
	
	public static void moveALLzeroRight() {
		int[] arrar = {4,8,0,3,0,12,0};
		
		int j=arrar.length-1;
		for(int i=arrar.length-1;i>=0;i--) {
			if(arrar[i]==0) {
				if(i!=j) {
					int temp=arrar[i];
					arrar[i]=arrar[j];
					arrar[j]=temp;
				}
				
				j--;
			}
		}
		System.out.println(Arrays.toString(arrar));
		
	}
	
	public static void movePosNegVal() {
		int[] arrar = {4,8,-21,3,-90,12,-3};
		int j=0;
		for(int i=0;i<arrar.length;i++) {
			if(arrar[i]<0) {
				if(i!=j) {
					int temp=arrar[i];
					arrar[i]=arrar[j];
					arrar[j] =temp;
				}
				j++;
			}
		}
		
		System.out.println(Arrays.toString(arrar));
		
	}
	
	public static void rightRotateByOneIndex(int k) {
		int[] array= {4,6,1,3,6,8,2};
		
		int lastElement=array[array.length-1];
		for(int i=array.length-1;i>0;i--) {
			array[i]=array[i-1];
		}
		array[0]=lastElement;
		System.out.println(Arrays.toString(array));
		
	}
	
	public static void showIndecesOfNum() {
		 int[] a= {5,7,3,6,9,2};
		  int target=10;
		  Map<Integer,Integer> map = new HashMap<>();
		  for(int i=0;i<a.length;i++) {
			  int component =target-a[i];
			  if(map.containsKey(a[i])) {
				  System.out.println(map.get(a[i]) + " "+i);
			  }else {
				  map.put(component, i);
			  }
		  }
		 
	}
	
	 public static void kthLargestElement() {
		 int[] a= {1,6,7,9};
		 int k=2;
		 
		 PriorityQueue<Integer> intgers= new PriorityQueue<Integer>();
		 for(int i=0;i<a.length;i++) {
			 intgers.offer(a[i]);
			 if(intgers.size()>k) {
				 intgers.poll();
			 }
		 }
		 System.out.println(intgers.peek());
		 
	 }
	 
	 public static void polin() {
		 int num=222;
		 int temp=num;
		 int sum=0;
		 while(num>0) {
			 int r=num%10;
			 sum =sum*10+r;
			 num=num/10;
		 }
		 if(temp==sum) {
			 System.out.println("yes");
		 }else {
			 System.out.println("No");
		 }
	 }
	 
	 public static void amonst() {
		 int num=155;
		 int temp=num;
		 int sum=0;
		 while(num>0) {
			 int r=num%10;
			 sum =sum+r*r*r;
			 num=num/10;
		 }
		 if(temp==sum) {
			 System.out.println("yes");
		 }else {
			 System.out.println("No");
		 }
	 }
	 
	 public static void missingNum() {
		 int[] array = {1,2,4,5,6};
		 int n= array.length+1;
		 int sum=n*(n+1)/2;
		 for(int num:array) {
			 sum=sum-num;
		 }
		 
		 System.out.println("Missing Number :"+sum);
	}
	 public static void pattern1() {
		 for(int i=0;i<5;i++) {
			 for(int j=0;j<=i;j++) {
				 System.out.print("*");
			 }
			 System.out.println();
		 }
	 }
	 
	 public static void pattern2() {
		 for(int i=0;i<5;i++) {
			 for(int j=0;j<5-i;j++) {
				 System.out.print(" ");
			 }
			 for(int k=0;k<=i;k++) {
				 System.out.print("*");
			 }
			 System.out.println();
		 }
		 }
	 
	 public static void pyramidShape() {
		 for(int i=0;i<5;i++) {
			 for(int j=0;j<5-i;j++) {
				 System.out.print(" ");
			 }
			 for(int k=0;k<2*i+1;k++) {
				 System.out.print("*");
			 }
			 System.out.println();
		 }
	 }
	 
	 public static void reversePyramidShape() {
		 for(int i=0;i<5;i++) {
			 
			 for(int k=0;k<i;k++) {
				 System.out.print(" ");
			 }
			 for(int j=0;j<5- 2*i;j++) {
				 System.out.print("*");
			 }
			
			 System.out.println();
		 }
	 }
	 
	 public static void vshape() {
		 for(int i=0;i<5;i++) {
			 for(int k=0;k<i;k++) {
				 System.out.print(" ");
			 }
			
			System.out.print("*");
			
			 
			 for(int l=0;l< 2 * (5 - i - 1);l++) {
				// System.out.print(l);
				 System.out.print(" ");
			 }
			 
			if(i!=5-1) {
		    System.out.print("*");
			}
			 
			 System.out.println();
			 
		 }
	 }
	
	public static void main(String[] args) {
		//moveALLzeroLeft();
		//moveALLzeroRight();
		//movePosNegVal();
		//rightRotateByOneIndex(0);
		//showIndecesOfNum();
		//kthLargestElement();
		//polin();
		//amonst();
		//missingNum();
		//pattern2();
		//pyramidShape();
		//reversePyramidShape();
		vshape();
	}

}
