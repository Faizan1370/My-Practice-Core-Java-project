package com.faizan.leetcode.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeetCodeRevision3 {
	
	public static int[] sortedSquares(int[] nums) {
		int[] result = new int[nums.length];
		int left=0;
		int right=nums.length-1;
		
		for(int i=nums.length-1;i>=0;i--) {
			int elemment1 = nums[left]*nums[left];
			int element2 = nums[right]*nums[right];
			if(elemment1>element2) {
				result[i]=elemment1;
				left++;
			}else {
				result[i]=element2;
				right--;
			}
		}
		return result;
		
	}
	
	static PriorityQueue<Integer> integers = new PriorityQueue<Integer>(new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			return o2-o1;
		}
	});
	public static int lastWeight(int[] stones) {
		for(int i=0;i<stones.length;i++) {
			integers.add(stones[i]);
		}
		while(integers.size()>1) {
			int stone1=integers.poll();
			int stone2=integers.poll();
			if(stone1!=stone2) {
				integers.add(stone1-stone2);
			}
		}
		if(integers.isEmpty()) {
			return 0;
		}
		return integers.poll();
	}
	
	public static String removeAdjcent(String s) {
		Stack<Character> stack= new Stack<Character>();
		
		for(int i=0;i<s.length();i++) {
			char ch=s.charAt(i);
			if(stack.isEmpty()) {
				stack.push(ch);
			}else if(ch== stack.peek()) {
				stack.pop();
			}else {
				stack.push(ch);
			}
		}
		StringBuilder builder = new StringBuilder();
		if(!stack.isEmpty()) {
			for(char c:stack) {
				builder.append(c);
			}
		}
		return builder.toString();
	}
	
	public static String removeAdjacentDuplicates(String s) {
	    StringBuilder sb = new StringBuilder();

	    for (char c : s.toCharArray()) {
	        int len = sb.length();
	        if (len > 0 && sb.charAt(len - 1) == c) {
	            sb.deleteCharAt(len - 1); // Remove duplicate
	        } else {
	            sb.append(c);
	        }
	    }

	    return sb.toString();
	}
	
	public static int balancedStringSplit(String s) {
		if(s==null || s.length()==0) {
			return 0;
		}
		int count=0;
		int left=0;
		int right=0;
		
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='L') {
				left++;
			}else {
				right++;
			}
			if(left==right) {
				count++;
			}
		}
		return count;
	}
	public static int findNumbers(int[] nums) {
		if(nums.length==0) {
			return 0;
		}
		int count=0;
		for(int i=0;i<nums.length;i++) {
			int numsCount=0;
			while(nums[i]>0) {
				nums[i]=nums[i]/10;
				numsCount++;
			}
			if(numsCount%2==0) {
				count++;
			}
		}
		return count;
	}
	
	public static int findNumbers1(int[] nums) {
	    int count = 0;
	    for (int num : nums) {
	        if (String.valueOf(num).length() % 2 == 0) {
	            count++;
	        }
	    }
	    return count;
	}
	
	public static int countNegative(int[][] grid) {
		int totalRows=grid.length;
		int totalCols=grid[0].length;
		int row=0;
		int col=totalCols-1;
		int count=0;
		while(row<totalRows && col>=0) {
			if(grid[row][col]<0) {
				col--;
				count +=totalRows-row;
			}else {
				row++;
				
			}
		}
		return count;
	}
	
	public static boolean[] kidsWithCandies(int[] candies,int extraCanties) {
		boolean[] result= new boolean[candies.length];
		//Integer integer = Arrays.stream(candies).mapToObj(num->(Integer)num).max(Comparator.comparingInt(num->num.intValue())).get();
		int max = Arrays.stream(candies).max().getAsInt();
		/*
		 * int max=Integer.MIN_VALUE; for(int i=0;i<candies.length;i++) {
		 * if(candies[i]>max) { max=candies[i]; } }
		 */
		for(int i=0;i<candies.length;i++) {
			if(candies[i]+extraCanties>=max) {
				result[i]=true;
			}else {
				result[i]=false;
			}
		}
		return result;
	}
	
	public static int maximumProduct(int[] nums) {
		int product=0;
		PriorityQueue<Integer> integers = new PriorityQueue<Integer>(( o1,  o2)-> o2-o1);
		for(int i=0;i<nums.length;i++) {
			integers.add(nums[i]);
		}
		if(integers.size()>1) {
			int first =integers.poll();
			int second=integers.poll();
			product=(first-1)*(second-1);
		}
		return product;
	}
	
	public static int maximumProduct1(int[] nums) {
	  int first =0,second=0;
	  
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
	
	public static int maximumProduct2(int[] nums) {
		Arrays.sort(nums);
		return (nums[nums.length-1]-1)*(nums[nums.length-2]-1);
	}
	
	public static int[] runningSum(int[] nums) {
		int[] runnigSum=new int[nums.length];
		int sum=0;
		for(int i=0;i<nums.length;i++) {
			sum=sum+nums[i];
			runnigSum[i]=sum;
		}
		return runnigSum;
	}
	
	public static int[] runningSum1(int[] nums) {
	   for(int i=1;i<nums.length;i++) {
		   nums[i] +=nums[i-1];
	   }
	return nums;
	}
	
	public static double avgSalary(int[] nums) {
		double avg=0.0;
		int max = Arrays.stream(nums).max().getAsInt();
		int min= Arrays.stream(nums).min().getAsInt();
		
		int sum=0;
		for(int i=0;i<nums.length;i++) {
			sum +=nums[i];
		}
		avg = (sum-min-max)/(nums.length-2);
		return avg;
		
	}
	
	public static double avgSalary1(int[] salary) {
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		int sum=0;
		for(int i=0;i<salary.length;i++) {
			min=Math.min(min, salary[i]);
			max=Math.max(max, salary[i]);
			sum +=salary[i];
		}
		
		double avg= (sum-min-max)/(salary.length-2);
		return avg;
	}
	
	public static String reformatDate(String date) {
		StringBuilder builder = new StringBuilder();
		String[] dates=date.split(" ");
		String year=dates[dates.length-1];
		String month=dates[1];
		String day=dates[0];
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("OCT", 10);
		String digits="";
		for(int i=0;i<day.length()-1;i++) {
			if(Character.isDigit(day.charAt(i))) {
				digits +=day.charAt(i);
			}
		}
		if(digits.length()==1) {
			digits ="0"+digits;
		}
		
		return builder.append(year).append("-").append(map.get(month)).append("-").append(digits).toString();
	}
	
	public static String shufflingString(String s,int[] nums) {
		HashMap<Integer, Character> map = new HashMap<Integer, Character>();
		StringBuilder builder = new StringBuilder();
		if(s.length()!=nums.length) {
			return "";
		}
		for(int i=0;i<nums.length;i++) {
			map.put(nums[i], s.charAt(i));
		}
		for(int i=0;i<nums.length;i++) {
			builder.append(map.get(i));
		}
		return builder.toString();
	}
	public static String shufflingString1(String s, int[] nums) {
	   char[] result= new char[s.length()];
	   for(int i=0;i<nums.length;i++) {
		   result[nums[i]]=s.charAt(i);
	   }
	   
	   return new String(result);
	}
	
	public static boolean consecutiveOdd(int[] arr) {
		int count=0;
		for(int i=0;i<arr.length;i++) {
			if(arr[i]%2!=0) {
				count++;
				if(count==3) {
					return true;
				}
			}else {
				count=0;
			}
		}
		return false;
		
	}
	
	public static int diagonalSum(int[][] mat) {
		int len=mat.length;
		int sum=0;
		for(int i=0;i<len;i++) {
			sum +=mat[i][i];
			sum+=mat[len-1-i][i];
		}
		if(len%2!=0) {
			sum -=mat[len/2][len/2];
		}
		return sum;
	}
	
	public static double trimMean(int[] arr) {
		if (arr == null || arr.length == 0) return 0.0;
		Arrays.sort(arr);
		int len=arr.length;
		int limit=(int) (len*0.05);
		int sum=0;
		for(int i=limit;i<len-limit;i++) {
			sum +=arr[i];
		}
		double avg= sum/(len-(2*limit));
		
		return avg;
	}
	
	public static int[] sortFrequesncy(int[] nums) {
		 Map<Integer, Long> freqMap = Arrays.stream(nums)
			        .boxed()
			        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

			    return Arrays.stream(nums)
			        .boxed()
			        .sorted((a, b) -> {
			            int freqCompare = Long.compare(freqMap.get(a), freqMap.get(b));
			            if (freqCompare == 0) {
			            	return Integer.compare(b, a);
			            }
			            return freqCompare;
			        })
			        .mapToInt(i -> i)
			        .toArray();
         }
	
	public static int[] sortFrequency1(int[] nums) {
	    // Step 1: Build frequency map
	    Map<Integer, Integer> freqMap = new HashMap<>();
	    for (int num : nums) {
	        freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
	    }

	    // Step 2: Put all elements into a list (including duplicates)
	    List<Integer> numList = new ArrayList<>();
	    for (int num : nums) {
	        numList.add(num);
	    }

	    // Step 3: Sort using Collections.sort with custom comparator
	    Collections.sort(numList, new Comparator<Integer>() {
	        @Override
	        public int compare(Integer a, Integer b) {
	            int freqA = freqMap.get(a);
	            int freqB = freqMap.get(b);
	            if (freqA != freqB) {
	                return freqA - freqB; // frequency ascending
	            } else {
	                return b - a; // value descending
	            }
	        }
	    });

	    // Step 4: Convert back to int[]
	    int[] result = new int[nums.length];
	    for (int i = 0; i < nums.length; i++) {
	        result[i] = numList.get(i);
	    }

	    return result;
	}

  
		
	

	
	public static void main(String[] args) {
		int[] nums = {1, 1, 2, 2, 2, 3};
		System.out.println(Arrays.toString(sortFrequency1(nums)));
	   // int[] arr= {6,2,7,5,1,2,0,3,10,2,5,0,5,5,0,8,7,6,8,0};
		//System.out.println(trimMean(arr));
		//int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
		//System.out.println(diagonalSum(mat));
		//int[] arr= {1,5,8,9,2};
		//System.out.println(consecutiveOdd(arr));
		//String s="codeleet";
		//int[] nums= {4,5,6,7,0,2,1,3};
		//System.out.println(shufflingString1(s, nums));
		//String date="8th OCT 2025";
		//System.out.println(reformatDate(date));
		//int[] salary= {4000,3000,1000,2000};
		//System.out.println(avgSalary1(salary));
		//int[] nums= {1,2,3,4};
		//System.out.println(Arrays.toString(runningSum1(nums)));
		//int[] nums= {3,4,5,2};
		//System.out.println(maximumProduct2(nums));
		//int[] candies= {2,3,5,1,3};
		//System.out.println(Arrays.toString(kidsWithCandies(candies, 3)));
		//int[][] grid= {{4,3,2,-1},{3,2,1,-1},{1,1,-1,-2},{-1,-1,-2,-3}};
		//System.out.println(countNegative(grid));
		//int[] nums= {12,345,2,6,7896};
		//System.out.println(findNumbers(nums));
		//String s="RLRRLLRLRL";
		//System.out.println(balancedStringSplit(s));
		// String s="abbaca";
		 //System.out.println(removeAdjacentDuplicates(s));
		//int[] stones= {2,7,4,1,8,1};
		//System.out.println(lastWeight(stones));
		//int[] nums= {-4,-1,0,3,10};
		//System.out.println(Arrays.toString(sortedSquares(nums)));
	}
	
}