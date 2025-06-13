package com.faizan.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LeetCode4 {
	
	public static List<Boolean> kidsWithCandies(int[] candies,int extraCandies){
		int max= Integer.MIN_VALUE;
		List<Boolean> result= new ArrayList<Boolean>();
		for(int i=0;i<candies.length;i++) {
			if(candies[i]>max) {
				max=candies[i];
			}
		}
		for(int i=0;i<candies.length;i++) {
			if(candies[i]+extraCandies>=max) {
				result.add(true);
			}else {
				result.add(false);
			}
		}
		return result;
	}
	
	public static int maxProduct(int[] nums) {
		Arrays.sort(nums);
		int firstVlaue=nums[nums.length-1];
		int secondVlaue=nums[nums.length-2];
		int product=(firstVlaue-1)*(secondVlaue-1);
		return product;
	}
	
	public static int[] runningSum(int[] nums) {
		int sum=0;
		for(int i=1;i<nums.length;i++) {
			sum=nums[i]+nums[i-1];
			nums[i]=sum;
		}
		return nums;
	}
	
	public static double avgSalary(int[] salary) {
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		int sum=0;
		for(int i=0;i<salary.length;i++) {
			min=Math.min(min, salary[i]);
			max=Math.max(max, salary[i]);
			sum +=salary[i];
		}
		double newSum= sum-min-max;
		double newLength=salary.length-2;
		
		double avg= newSum/newLength;
		
		return avg;
	}
	
	public static String dateFormator(String date) {
		String[] words = date.split(" ");
		String year=words[words.length-1];
		String month =words[1];
		String day=words[0];
		
		String result=year +"-";
		Map<String,String> map = new HashMap<String, String>();
		map.put("OCT", "10");// need to add for every month
		
		month=map.get(month);
		result +=month +"-";
		String digits="";
		for(int i=0;i<day.length();i++) {
			if(Character.isDigit(day.charAt(i))) {
				digits +=day.charAt(i);
			}
		}
		if(digits.length()==1) {
			day ="0"+digits;
		}else {
			day=digits;
		}
		result +=day;
		return result;
	}
	
	public static String shufllingString(String s,int[] indices) {
		if(s.isBlank() || s.length()==0) {
			return "";
		}
		if(s.length()!=indices.length) {
			return"";
		}
		
		char[] chars = s.toCharArray();
		for(int i=0;i<indices.length;i++) {
			int pos=indices[i];
			chars[pos]=s.charAt(i);
		}
		String result="";
		for(int i=0;i<chars.length;i++) {
			result +=chars[i];
		}
		return result;
	}
	
	public static boolean threeConsecutiveOdd(int[] arr) {
		int count=0;
		for(int i=0;i<arr.length;i++) {
			if(arr[i]%2!=0) {
				count++;
			}else {
				count =0;
			}
			
			if(count ==3) {
				return true;
			}
		}
		return false;
	}
	
	public static int daigonalSum(int[][] mat) {
		int len=mat.length;
		int sum=0;
		for(int i=0;i<len;i++) {
			sum+=mat[i][i];
			sum+=mat[len-1-i][i];
		}
		if(len%2!=0) {
			sum -=mat[len/2][len/2];
		}
		return sum;
	}
	
		// find meand or avg
	
	public static double trimMean(int[] arr) {
		int length=arr.length;
		Arrays.sort(arr);
		double limit=length *0.05;
		int start= (int) limit;
		int end =(int) (length-limit);
		double sum=0;
		double newLength=0;
		for(int i=start;i<end;i++) {
			sum=sum+arr[i];
			newLength++;
		}
		double avg=sum/newLength;
		return avg;
	}
	
	public static int[] frequencySort(int[] nums) {
	    // Step 1: Build frequency map
	    Map<Integer, Long> freqMap = Arrays.stream(nums)
	        .mapToObj(num -> (Integer) num)
	        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

	    // Step 2: Sort original values based on frequency asc, value desc
	    List<Integer> sortedList = Arrays.stream(nums)
	        .mapToObj(num -> (Integer) num)
	        .sorted((a, b) -> {
	            int freqCompare = Long.compare(freqMap.get(a), freqMap.get(b));
	            if (freqCompare == 0) {
	                return Integer.compare(b, a); // higher number first
	            }
	            return freqCompare;
	        })
	        .collect(Collectors.toList());

	    // Step 3: Convert to int[]
	    return sortedList.stream().mapToInt(i -> i).toArray();
	}

	
	public static void main(String[] args) {
		int[] nums= {1,1,2,2,2,3};
		System.out.println(Arrays.toString(frequencySort(nums)));
		//int[] arr= {6,2,7,5,1,2,0,3,10,2,5,0,5,5,0,8,7,6,8,0};
		//System.out.println(trimMean(arr));
		//int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
		//System.out.println(daigonalSum(mat));
		//int[] arr= {3,6,7,12,13};
		//System.out.println(threeConsecutiveOdd(arr));
		//String s="codeleet";
		//int[] indices= {4,5,6,7,0,2,1,3};
		//System.out.println(shufllingString(s, indices));
		//String date="6th OCT 1933";
		//System.out.println(dateFormator(date));
		//int[] salary= {4000,3000,2000,1000};
		//System.out.println(avgSalary(salary));
		//int[] nums= {1,2,3,4};
		//System.out.println(Arrays.toString(runningSum(nums)));
		//int[] nums= {3,4,5,2};
		//System.out.println(maxProduct(nums));
		//int[] candies= {2,3,5,1,3};
		//int extraCandies=3;
		//System.out.println(kidsWithCandies(candies, extraCandies));
	}

}
