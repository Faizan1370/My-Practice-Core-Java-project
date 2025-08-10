package com.faizan.leetcode.revision1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCodeRevision3 {
	
	public static boolean mountainArray(int[] nums) {
		int i=0;
		int j=nums.length-1;
		
		while(i<nums.length && nums[i]<nums[i+1]) {
			i++;
		}
		
		while(j>=0 && nums[j]<nums[j-1]) {
			j--;
		}
		return i==j;
	}
	
	public static int[] squareArray(int[] nums) {
		int left=0;
		int right=nums.length-1;
		int[] ans=new int[nums.length];
		
		for(int i=nums.length-1;i>=0;i--) {
			int element1=nums[left]*nums[left];
			int element2=nums[right]*nums[right];
			if(element1>element2) {
				ans[i]=element1;
				left++;
			}else {
				ans[i]=element2;
				right--;
			}
		}
		return ans;
	}
	
	public static int[] squareArray1(int[] nums) {
		
		
		for(int i=0;i<nums.length;i++) {
			nums[i]=nums[i]*nums[i];
		}
		Arrays.sort(nums);
		return nums;
	}
	
	public static int lastStoneWeight(int[] stones) {
		PriorityQueue<Integer> integers = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				
				return o2-o1;
			}
		});
		
		for(int i=0;i<stones.length;i++) {
			integers.add(stones[i]);
		}
		while(integers.size()>1) {
			int stone1=integers.poll();
			int stone2=integers.poll();
			if(stone1>stone2) {
				integers.add(stone1-stone2);
			}
		}
		return integers.peek();
	}
	public static int lastStoneWeight1(int[] stones) {
		Arrays.sort(stones);
		int i=stones.length-1;
		while(i>=1) {
			if(stones[i]>stones[i-1]) {
				stones[i-1]=stones[i]-stones[i-1];
				stones[i]=0;
				i--;
				Arrays.sort(stones);
			}else {
				stones[i]=0;
				stones[i-1]=0;
				i =i-2;
				Arrays.sort(stones);
			}
			
		}
		for (int stone : stones) {
	        if (stone > 0) {
	            return stone;
	        }
	    }
		return 0;
		
	}
	
	public static String removeAjacentDuplicate(String s) {
		Stack<Character> stack = new Stack<Character>();
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<s.length();i++) {
			if(stack.isEmpty()) {
				stack.push(s.charAt(i));
			}else if(stack.peek()==s.charAt(i)) {
				stack.pop();
			}else {
				stack.push(s.charAt(i));
			}
		}
		for(char c:stack) {
			builder.append(c);
		}
		return builder.toString();
	}
	
	public static int balancedString(String s) {
		int l_count=0;
		int r_count=0;
		int count=0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='L') {
				l_count++;
			}else {
				r_count++;
			}
			if(l_count==r_count) {
				count++;
			}
		}
		return count;
	}
	
	public static int evenDigitIntegre(int[] nums) {
		int count=0;
		for(int i=0;i<nums.length;i++) {
			if(String.valueOf(nums[i]).length()%2==0) {
				count++;
			}
		}
		return count;
	}
	
	public static boolean[] kidsWithCandies(int[] candies,int exttraCandies) {
		int max=Integer.MIN_VALUE;
		boolean[] result=new boolean[candies.length];
		for(int i=0;i<candies.length;i++) {
			if(candies[i]>max) {
				max=candies[i];
			}
		}
		for(int i=0;i<candies.length;i++) {
			if((exttraCandies+candies[i])>=max){
				result[i]=true;
			}else {
				result[i]=false;
			}
		}
		return result;
	}
	
	public static int maxProduct(int[] nums) {
		int first=0,second=0;
		for(int i=0;i<nums.length;i++) {
			if(nums[i]>first) {
				second=first;
				first=nums[i];
			}else if(nums[i]>second) {
				second=nums[i];
			}
		}
		
		return (first-1)*(second-1);
	}
	public static int maxProduct1(int[] nums) {
		Arrays.sort(nums);
		return (nums[nums.length-1]-1)*(nums[nums.length-2]-1);
	}
	
	public static int[] runnningSum(int[] nums) {
		int sum=0;
		for(int i=0;i<nums.length;i++) {
			sum +=nums[i];
			nums[i]=sum;
		}
		return nums;
	}
	
	public static double avgSalary(int[] salary) {
		Arrays.sort(salary);
		int sum=0;
		for(int i=0;i<salary.length;i++) {
			sum +=salary[i];
		}
		double avg= (sum-salary[0]-salary[salary.length-1])/(salary.length-2);
		return avg;
	}
	
	public static String formatDate(String s) {
		String[] words = s.split(" ");
		String year=words[words.length-1];
		String month=words[words.length-2];
		String day=words[0];
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Jan", "01");
		String digits="";
		for(int i=0;i<day.length();i++) {
			if(Character.isDigit(day.charAt(i))) {
				digits+=day.charAt(i);
				
			}
		}
		StringBuilder builder = new StringBuilder();
		if(digits.length()==1) {
			digits="0"+digits;
		}
		
		return builder.append(year).append("-").append(map.get(month)).append("-").append(digits).toString();
		
	}
	
	public static String shufflingString(int[] array,String s) {
		if(array.length!=s.length()){
			return "";
		}
		HashMap<Integer, Character> map = new HashMap<Integer, Character>();
		for(int i=0;i<array.length;i++) {
			map.put(array[i], s.charAt(i));
		}
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<array.length;i++) {
			builder.append(map.get(i));
		}
		
		return builder.toString();
	}
	public static boolean consecutiveOdd(int[] nums) {
		for(int i=1;i<nums.length;i++) {
			if(nums[i]%2!=0 && nums[i-1]%2!=0) {
				return true;
			}
		}
		return false;
	}
	
	public static double meanOfArray(int[] nums) {
		Arrays.sort(nums);
	    int limit=(nums.length *5)/100;
	    System.out.println(nums.length);
	    System.out.println(limit);
	    int sum=0;
	    for(int i=limit;i<nums.length-limit;i++) {
	    	sum +=nums[i];
	    }
	    
	    return sum/(nums.length- (2*limit));
	}
	
	public static int[] sortFrequecy(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0;i<nums.length;i++) {
			if(map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i])+1);
			}else {
				map.put(nums[i], 1);
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int num:nums) {
			list.add(num);
		}
		Collections.sort(list , new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				int freq1=map.get(o1);
				int freq2=map.get(o2);
				if(freq1!=freq2) {
					return freq1-freq2;
				}else {
					return o2-o1;
				}
			}
		});
		int[] result = new int[nums.length];
		for(int i=0;i<nums.length;i++) {
			result[i]=list.get(i);
		}
		return result;
	}
	
	public static String goalParser(String s) {
		StringBuilder builder = new StringBuilder();
	     int i=0;
	     while(i<s.length()) {
	    	 char ch =s.charAt(i);
	    	 if(ch=='G') {
	    		 builder.append("G");
	    		 i++;
	    	 }else if(ch=='(' && s.charAt(i+1)==')'){
	    		 builder.append("o");
	    		 i=i+2;
	    	 }else {
	    		 builder.append("al");
	    		 i=i+4;
	    	 }
	     }
	     return builder.toString();
	}
	
	public static int sumOfDistinct(int[] nums) {
	     Integer reduce = Arrays.stream(nums).mapToObj(num->(Integer)num)
	     .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
	     .entrySet()
	     .stream()
	     .filter(entry->entry.getValue()==1L)
	     .map(entry->entry.getKey())
	     .reduce(0,(a,b)->a+b);
	     return reduce;
	}
	
	public static String mergeString(String s1,String s2) {
		if(s1.length()>=s2.length()) {
			 
			return merger(s1, s2);
		}else{
			 return merger(s2, s1);
		}
	}
	
	public static String merger(String s1,String s2) {
		StringBuilder builder = new StringBuilder();
		int i=0;
		for(i=0;i<s2.length();i++) {
			builder.append(s1.charAt(i)).append(s2.charAt(i));
		}
		builder.append(s1.substring(i));
		return builder.toString();
	}
	public static void main(String[] args) {
		String s1="abc",s2="pqrs";
		System.out.println(mergeString(s1, s2));
		//int[] nums= {1,3,2,2};
		//System.out.println(sumOfDistinct(nums));
		// String s="G()(al)";
		 //System.out.println(goalParser(s));
		//int[] nums= {2,3,1,3,2};
		//System.out.println(Arrays.toString(sortFrequecy(nums)));
		// int[] arr= {6,2,7,5,1,2,0,3,10,2,5,0,5,5,0,8,7,6,8,0};
		//System.out.println(meanOfArray(arr));
		//int[] array= {2,6,4,1,3};
		//System.out.println(consecutiveOdd(array));
		//String s="codeleet";
		//int[] array= {4,5,6,7,0,2,1,3};
		//System.out.println(shufflingString(array, s));
	//	String s="20th Jan 2052";
	//	System.out.println(formatDate(s));
		//int[] sal= {4000,3000,1000,2000};
		//System.out.println(avgSalary(sal));
		//int[] nums= {1,2,3,4};
		//System.out.println(Arrays.toString(runnningSum(nums)));
		//int[] nums= {3,4,5,2};
		//stem.out.println(maxProduct1(nums));
		//int[] candies= {2,3,5,1,3};
		//int extraCandies=3;
		//System.out.println(Arrays.toString(kidsWithCandies(candies, extraCandies)));
		//int[] nums= {12,345,2,6,7896};
		//System.out.println(evenDigitIntegre(nums));
		//String s="RLRRLLRLRL";
		//System.out.println(balancedString(s));
		//String s="abbaaca";
		//System.out.println(removeAjacentDuplicate(s));
		//int[] stones= {2,7,4,1,8,1};
		//System.out.println(lastStoneWeight1(stones));
		//int[] ans= {-4,-1,0,3,10};
		//System.out.println(Arrays.toString(squareArray1(ans)));
		//int[] nums= {0,2,3,4,5,2,1,0};
		//System.out.println(mountainArray(nums));
	}

}
