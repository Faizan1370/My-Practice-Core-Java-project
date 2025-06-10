package com.faizan.array.prac;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Ex3 {
	
	public static void kthLargestElement() {
		int[] array = {4,6,2,8,90,78};
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
	
	public static void insertValue() {
		int[] array = {4,6,2,8,90,78};
		int position=3;
		int value=10;
		
		for(int i=array.length-1;i>position-1;i--) {
			array[i]=array[i-1];
		}
		array[position-1]=value;
		System.out.println(Arrays.toString(array));
	}
	
	public static void deleteValue() {
		int[] array = {4,6,2,8,90,78};
		int delete=8;
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
	
	public static void removeDuplicate() {
		int[] array = {4,6,2,8,90,78,4,2};
		int[] resultArray = new int[array.length];
		
		int k=0;
		for(int i=0;i<array.length;i++) {
			boolean isDuplicate=false;
			for(int j=0;j<k;j++) {
				if(array[i]== resultArray[j]) {
					isDuplicate=true;
					break;
				}
			}
			if(!isDuplicate) {
				resultArray[k]=array[i];
				k++;
			}
		}
		System.out.println(Arrays.toString(resultArray));
	}
	
	
	public static void removeDuplicate1() {
		int[] array = {4,6,2,8,90,78,4,2};
		int[] resultArray = new int[array.length];
		boolean[] seen= new boolean[127];
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
		int[] array = {4,6,2,8,90,78,4,2};
		int[] resultArray = new int[array.length];
		int[] seen= new int[256];
		int j=0;
		for(int i=0;i<array.length;i++) {
			if(seen[array[i]]==0) {
				resultArray[j]=array[i];
				seen[array[i]]=1;
				j++;
			}
		}
		System.out.println(Arrays.toString(resultArray));
 		
	}
	
	public static void indices() {
		int[] array = {4,6,2,8,90,78};
		int target=8;
		Map<Integer,Integer> map = new HashMap<Integer, Integer>();
		
		for(int i=0;i<array.length;i++) {
			int component=target-array[i];
			if(map.containsKey(array[i])) {
				System.out.println("Indices are :"+map.get(i)+ " "+i);
			}else {
				map.put(component, i);
			}
		}
	}
	public static void fibo() {
		int num=7;
		int a=0;
		int b=1;
		
		for(int i =0;i<num;i++) {
			System.out.print(b +" ");
			int c=a+b;
			a=b;
			b=c;
		}
	}
	
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
	
	 public static void findMaxSumSubArray() {
		 int[] array = {3,5,-4,9,-10,7};
		 
		 int currentMax=0;
		 int sumMax=0;
		 
		 for(int i=0;i<array.length;i++) {
			 currentMax = currentMax+array[i];
			 if(currentMax>sumMax) {
				 sumMax=currentMax;
			 }
			 
			 if(currentMax<0) {
				 currentMax=0;
			 }
		 }
		 
		 System.out.println(sumMax);
		 
	 }
	 
	 public static void findMaxSumSubArray1() {
		    int[] array = {3, 5, -4, 9, -10, 7};
		    
		    int currentMax=array[0];
		    int sumMax=array[0];
		    
		    for(int i=1;i<array.length;i++) {
		    	currentMax=Math.max(array[i], currentMax+array[i]);
		    	sumMax=Math.max(sumMax, currentMax);
		    }
		    
		    System.out.println(sumMax);
	 }
	 
	 public static void palin() {
		 int num =121;
		 int temp=num;
		 int sum=0;
		 while(num>0) {
			int r =num%10;
			sum=sum*10+r;
			num=num/10;
		 }
		 if(temp==sum) {
			 System.out.println("Palin");
		 }
	 }
	 
	 public static void amons() {
		 int num=153;
		 int temp=num;
		 int sum=0;
		 
		 while(num>0) {
			 int r =num%10;
			 sum =sum+r*r*r;
			 num=num/10;
		 }
		 if(temp==sum) {
			 System.out.println("amon");
		 }
	 }
	
	public static void main(String[] args) {
		//kthLargestElement();
		//insertValue();
		//deleteValue();
		//removeDuplicate2();
		//indices();
		//fibo();
		//rearangePosNegValue();
		//findMaxSumSubArray1();
		//palin();
		amons();
	}

}
