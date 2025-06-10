package com.faizan.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class LeetCodeProb {
	
	public static int[] twoSum() {
		int[] array = {4,2,7,3,9,1,5};
		int target=6;
		HashMap<Integer, Integer> map= new HashMap<Integer, Integer>();
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
	
	public static void twoSum1() {
		int[] array = {4,2,7,3,9,1,5};
		int target=6;
		HashMap<Integer, Integer> map= new HashMap<Integer, Integer>();
		for(int i=0;i<array.length;i++) {
			int compliment = target-array[i];
			if(map.containsKey(array[i])) {
				System.out.println("indices :"+map.get(array[i]) +" "+i);
			}else {
				map.put(compliment, i);
			}
		}
		
	}
	
	public static void checkPalindrome() {
		int num=123;
		boolean isPalindrome=false;
		int sum=0;
		int temp=num;
		if(num<0) {
			isPalindrome=false;
		}
		while(num>0) {
			int r=num%10;
			sum=sum*10+r;
			num=num/10;
		}
		if(sum==temp)
		{
			isPalindrome=true;
			}else {
				isPalindrome=false;
			}
		
		if(isPalindrome) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}
				
		}
	
	
	public static void checkPalindrome1() {
		int num=121;
		boolean isPalidrome=false;
		if(num<0) {
			isPalidrome=false;
		}
		String str= num+"";
		int left=0;
		int right=str.length()-1;
		while(left<right) {
			if(str.charAt(left)!=str.charAt(right)) {
				isPalidrome=false;
				break;
			}else {
				isPalidrome=true;
			}
			left++;
			right--;
		}
		if(isPalidrome) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}
	}
	
	public static void romanToIntger() {
		String str="III";
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X',10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		int result=0;
		char[] charArray = str.toCharArray();
		int i,j;
		for(i=0,j=1;j<charArray.length;i++,j++) {
			if(map.get(charArray[i])>=map.get(charArray[j])) {
				result+=map.get(charArray[i]);
			}else {
				result -=map.get(charArray[i]);
			}
			
		}
		result +=map.get(charArray[i]);
		System.out.println(result);
	}
	
	public static void romanToIntger1() {
		String str="VI";
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X',10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		int result=0;
		char[] charArray = str.toCharArray();
		int j=0;
		for(int i=1;i<charArray.length;i++) {
			if(map.get(charArray[j])>=map.get(charArray[i])) {
				result+=map.get(charArray[j]);
			}else {
				result -=map.get(charArray[j]);
			}
			j++;
			
		}
		result +=map.get(charArray[j]);
		System.out.println(result);
	}
	
	public static void longestPrefix() {
		String[] array= {"faizan","faiz","farhan"};
		String prefix=array[0];
		for(int i=0;i<array.length;i++) {
			while(!array[i].startsWith(prefix)) {
				prefix=prefix.substring(0,prefix.length()-1);
			}
			if(prefix.isBlank()) {
				System.out.println("no prefix found");
				return;
			}
		}
		System.out.println(prefix);
	}
	
	public static void validParenteses() {
		String str="(){}[]";
		Stack<Character> stack = new Stack<Character>();
		for(int i=0;i<str.length();i++) {
			char ch = str.charAt(i);
			if(ch=='(' || ch=='{' || ch== '[') {
				stack.push(ch);
			}else if(!stack.isEmpty() && ch==')' && stack.peek() =='('){
				stack.pop();
			}else if(!stack.isEmpty() && ch=='}' && stack.peek() =='{'){
				stack.pop();
			}else if(!stack.isEmpty() && ch==']' && stack.peek() =='['){
				stack.pop();
			}
			
		}
		if(!stack.isEmpty()) {
			System.out.println("no valid parent");
		}else {
			System.out.println("yes Valid Parent");
		}
	}
	
	//Binary Serach
	public static int binarySearch() {
		int[] arrray = {1,3,6,8,9};
		int target=8;
		
		int left=0;
		int right=arrray.length-1;
		int mid=0;
		
		while(left<=right) {
			mid=left+(right-left)/2;
			if(target==arrray[mid]) {
				return mid;
			}
			if(target<arrray[mid]) {
				right=mid-1;
			}else {
				left=mid+1;
			}
		}
		return -1;
		
	}
	
	public static int searchInseryPosition() {
		int[] arrray = {1,3,6,8,9};
		int mid=0,target =2,right=arrray.length-1,left=0;
		
	     while(left<=right) {
	    	 mid = left+(right-left)/2;
	    	 if(target==arrray[mid]) {
	    		 return mid;
	    	 }else if(target<arrray[mid]) {
	    		 right=mid-1;
	    	 }else {
	    		 left=mid+1;
	    	 }
	    	 
	     }
		return left;
	}
	
	public static void lengthOfLastWord() {
		String str="Hello World";
		String[] split = str.split(" ");
		System.out.println(split[split.length-1].length());
	}
	
	public static void removeDuplicatesFromSortLinkedList(ListNode head) {
	     ListNode current=head;
	     while(current!=null && current.next!=null) {
	    	 if(current.data==current.next.data) {
	    		 current.next=current.next.next;
	    	 }else {
	    		 current=current.next;
	    	 }
	     }
	    
	     ListNode temp=head;
	     while(temp!=null) {
	    	 System.out.print(temp.data +" ");
	    	 temp=temp.next;
	     }
	}
	public static void mergeSortedArray(int[] array1,int m,int[] array2,int n) {
		int p1=m-1;
		int p2=n-1;
		int p3 = array1.length-1;
		while(p3>=0) {
			int element1, element2;
			element1=(p1>=0)?array1[p1]:Integer.MIN_VALUE;
			element2 =(p2>=0)?array2[p2]:Integer.MIN_VALUE;
			if(element1>element2) {
				array1[p3]=element1;
				p3--;
				p1--;
				
			}else {
				array1[p3]=element2;
				p3--;
				p2--;
			}
		}
		System.out.println(Arrays.toString(array1));
	}
	
	public static void maxProfit() {
		int[] preices= {7,1,5,3,6,4};
		int min =Integer.MAX_VALUE;
		int maxProfit=0;
		for(int i=0;i<preices.length;i++) {
			if(preices[i]<min) {
				min=preices[i];
			}
			 int currentProfit = preices[i]-min;
			 if(currentProfit>maxProfit) {
				 maxProfit=currentProfit;
			 }
		}
		System.out.println(maxProfit);
	}
	public static void main(String[] args) {
		/*
		 * ListNode head = new ListNode(1); head.next = new ListNode(1); head.next.next
		 * = new ListNode(2); head.next.next.next = new ListNode(3);
		 * head.next.next.next.next = new ListNode(3);
		 * 
		 * removeDuplicatesFromSortLinkedList(head);
		 */
		int[] array = {1,3,5,0,0,0};
		int m=3;
		int n=3;
		int[] array2= {2,4,6};
		mergeSortedArray(array, m, array2, n);
		maxProfit();
		
	}

}
