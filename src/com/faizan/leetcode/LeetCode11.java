 package com.faizan.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCode11 {
	
	public static int findChampion(int[][] grid) {
		int row =grid.length;
		int col=grid[0].length;
		
		
		for(int i=0;i<row;i++) {
			int count=0;
			for(int j=0;j<col;j++) {
				if(grid[i][j]==1) {
					count++;
				}
			}
			if(count==grid.length-1) {
				return i;
			}
		}
		return -1;
	}
	
	public static boolean arrayStringEqual(String[] words1,String[] words2) {
		StringBuilder builder = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		for(String str:words1) {
			builder.append(str);
		}
		for(String str:words2) {
			builder2.append(str);
		}
		
		return builder.toString().equals(builder2.toString());
	}
	
	//This will work if array is sorted Otherwise use count frequncey
	public static int elementAppering25Per(int[] nums) {
	  int limit = nums.length/4; //25%
	  for(int i=0;i<nums.length-limit;i++) {
		  if(nums[i]== nums[i+limit]) {
			  return nums[i];
		  }
	  }
	return -1;
	}
	public static int elementAppering25Per1(int[] nums) {
		  int target = nums.length/4; //25%
		  Integer integer = Arrays.stream(nums).mapToObj(num->(Integer)num)
		  .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		  .entrySet()
		  .stream()
		  .filter(entry->entry.getValue()>target)
		  .map(entry->entry.getKey())
		  .findFirst()
		  .get();
		  return integer;
	}
	
	public static int[] findIntersectionValues(int[] nums1,int[] nums2) {
		int[] ans = new int[2];
		int[] count1=new int[101];
		int[] count2 = new int[101];
		
		for(int i=0;i<nums1.length;i++) {
			count1[nums1[i]]++;
		}
		
		for(int i=0;i<nums2.length;i++) {
			count2[nums2[i]]++;
		}
		int result=0,result2=0;
		for(int i=1;i<=100;i++) {
			if(count2[i]>=1) {
				result +=count1[i];
			}
			if(count1[i]>=1) {
				result2 +=count2[i];
			}
		}
		ans[0]=result;
		ans[1]=result2;
		
		return ans;
	}
	
	public static int numSpecial(int[][] mat) {
		int row =mat.length;
		int col= mat[0].length;
		int count=0;
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(mat[i][j]==0) {
					continue;
				}
				boolean flag =false;
				for(int r=0;r<row;r++) {
					if(r!=i && mat[r][j]==1) {
						flag=true;
						break;
					}
				}
				
				for(int c=0;c<col;c++) {
					if(c!=j && mat[i][c]==1) {
						flag=true;
						break;
					}
				}
				if(!flag) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static boolean isSunsequence(String s,String t) {
		if(s==null || s.equals("") ) {
			return false;
		}
		int index =0;
		for(int i=0;i<t.length();i++) {
			if(s.charAt(index)== t.charAt(i)) {
				index++;
			}
			if(index==s.length()) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean canPlaceFlower(int[] flowerBer, int n) {
		if(n==0) {
			return false;
		}
		int count=0;
		for(int i=0;i<flowerBer.length;i++) {
			if(flowerBer[i]==0) {
				if((i==0 || flowerBer[i-1]==0) && (i==flowerBer.length-1 || flowerBer[i+1]==0)) {
					count++;
					if(count==n) {
						return true;
					}
					i++;
				}
			}
		}
		return false;
	}
	
	public static int removeElement(int[] nums,int val) {
		int len=nums.length;
		int i=0;
		while(i<len) {
			if(nums[i]==val) {
				nums[i]=nums[len-1];
				len--;
			}else {
				i++;
			}
		}
		return len;
	}
	
	public static int removeElement1(int[] nums, int val) {
	    int k = 0;  // slow pointer (next position to place a valid element)

	    for (int i = 0; i < nums.length; i++) {
	        if (nums[i] != val) {
	            nums[k] = nums[i];  // copy valid element forward
	            k++;
	        }
	    }
	    return k;  // new length
	}
     
	
	public static String gcdOfStrings(String str1,String str2) {
		int len1=str1.length(),len2=str2.length();
		if(!(str1+str2).equals(str2+str1)) {
			return "";
		}
		String result= str1.substring(0,gcd(len1, len2));
		return result;
	}
	
	private static int gcd(int num1,int num2) {
		if(num2==0) {
			return num1;
		}
		/*
		 * while (num2 != 0) { int temp = num2; num2 = num1 % num2; num1 = temp; }
		 * return num1;
		 */
		return gcd(num2, num1%num2);
	}
	
	public static String destCity(List<List<String>> paths) {
		HashSet<String> set = new HashSet<String>();
		for(int i=0;i<paths.size();i++) {
			set.add(paths.get(i).get(0));
		}
		for(int i=0;i<paths.size();i++) {
			String dest=paths.get(i).get(1);
			if(!set.contains(dest)) {
				return dest;
			}
		}
		return "";
	}
	
	public static int pivotIndex(int[] nums) {
		int sum=0,leftSum=0;
		for(int num:nums) {
			sum +=num;
		}
		for(int i=0;i<nums.length;i++) {
			//rightSum =sum-leftSum-nums[i];
			if(leftSum==sum-leftSum-nums[i]) {
				return i;
			}
			leftSum +=nums[i];
			
		}
		return -1;
	}
	
	public static boolean uniqueOcuurecce(int[] nums) {
		Map<Integer, Long> map = Arrays.stream(nums).mapToObj(num->(Integer)num)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		
		HashSet<Long> set = new HashSet<Long>();
		for(Map.Entry<Integer, Long> entry: map.entrySet()) {
			if(set.contains(entry.getValue())) {
				return false;
			}else {
				set.add(entry.getValue());
			}
		}
		return true;
		
	}
	
	public static boolean uniqueOcuurecce1(int[] nums) {
		Map<Integer, Long> map = Arrays.stream(nums).mapToObj(num->(Integer)num)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		HashSet<Long> set = new HashSet<Long>(map.values());
		
		return map.size()==set.size();
		
	}
	
	public static String reverseVolwel(String s) {
		char[] words= s.toCharArray();
		int start=0,end=words.length-1;
		String vowels="AEIOUaeiou";
		while(start<end) {
		while(start<end && vowels.indexOf(words[start])==-1){
				 start++;
		}
		while(start<end && vowels.indexOf(words[end])==-1){
				 end--;
		}
		char temp=words[start];
		words[start]=words[end];
		words[end]=temp;
		start++;
		end--;
		}
		return new String(words);
	}
	
	public static String defanfIPaddr(String ipAddress) {
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<ipAddress.length();i++) {
			if(ipAddress.charAt(i)=='.') {
				builder.append("[.]");
			}else {
				builder.append(ipAddress.charAt(i));
			}
		}
		return builder.toString();
	}
	
	public static int finalValueOfVar(String[] oprations) {
		int x=0;
		for(String op:oprations) {
			if(op.equals("++X") || op.equals("X++")) {
				x +=1;
			}else if(op.equals("--X") || op.equals("X--")) {
				x -=1;
			}
		}
		return x;
	}
	
	public static List<Integer> wordContainigChars(String[] words,char x) {
		List<Integer> list = new ArrayList<Integer>();
		String temp=""+x;
		for(int i=0;i<words.length;i++) {
			if(words[i].contains(temp)) {
				list.add(i);
			}
		}
		return list;
	}
	
	public static List<Integer> wordContainigChars1(String[] words,char x) { // this one better approach
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<words.length;i++) {
			for(char ch:words[i].toCharArray()) {
				if(ch==x) {
					list.add(i);
					break;
				}
			}
		}
		return list;
		
	}
	
	public static List<Integer> containigChar(String[] words,char x){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<words.length;i++) {
			if(words[i].indexOf(x)!=-1) {
				list.add(i);
			}
			
		}
		return list;
	}

	
	public static int maxProductDiff(int[] nums) {
		Arrays.sort(nums);
		return (nums[nums.length-1]*nums[nums.length-2])-(nums[0]*nums[1]);
	}
	
	public static int maxProduct(int[] nums) {
		int largest =Integer.MIN_VALUE,seondLargest=Integer.MIN_VALUE;
		int smallest=Integer.MAX_VALUE,secondSmallest=Integer.MAX_VALUE;
		
		for(int num:nums) {
			if(num>largest) {
				seondLargest=largest;
				largest=num;
			}else if(num>seondLargest) {
				seondLargest=num;
			}
			
			if(num<smallest) {
				secondSmallest=smallest;
				smallest=num;
			}else if(num<secondSmallest) {
				secondSmallest=num;
			}
		}
		return (largest*seondLargest) - (smallest*secondSmallest);
	}
	
	public static int[] findMissingAndRepeatedValue(int[][] grid) {
		HashSet<Integer> set = new HashSet<Integer>();
		int n=grid.length;
		int a=0,b=0;
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[0].length;j++) {
				if(set.contains(grid[i][j])) {
					a=grid[i][j];
				}
				set.add(grid[i][j]);
			}
		}
		for(int i=1;i<=n*n;i++) {
			if(!set.contains(i)) {
				b=i;
				break;
			}
		}
		
		return new int[] {a,b};
	}
	
	public static int maximumWordFound(String[] senteces) {
		int maxCount=0;
		for(String word:senteces) {
			String[] sens = word.split(" ");
			maxCount=Math.max(maxCount, sens.length);
		}
		return maxCount;
	}
	
	public static int maximumWordFound1(String[] sentences) {
	    int maxCount = 0;
	    for (String s : sentences) {
	        int words = 1; // at least one word if string is not empty
	        for (char c : s.toCharArray()) {
	            if (c == ' ') words++;
	        }
	        maxCount = Math.max(maxCount, words);
	    }
	    return maxCount;
	}
	
	public static String removeTrailingZero(String num) {
		int start =0,end=num.length()-1;
		while(start<end) {
			if(num.charAt(end)=='0') {
				end--;
			}else {
				break;
			}
		}
		return num.substring(start,end+1);
	}
	
	public static String removeTrailingZero1(String num) {
	    int end = num.length() - 1;

	    while (end >= 0 && num.charAt(end) == '0') {
	        end--;
	    }

	    // if all characters were '0', return "0"
	    if (end < 0) return "0";

	    return num.substring(0, end + 1);
	}
	
	public static int percentageLeeterInString(String str,char c) {
		 if (str == null || str.length() == 0) return 0; // avoid divide by zero
		int count=0;
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)==c) {
				count++;
			}
		}
		
		return (count*100)/str.length();
	}
	
	public static int percentageLeeterInString1(String str,char c) {
		 if (str == null || str.length() == 0) return 0; // avoid divide by zero
		 int[] freq = new int[26];
		 for (char ch : str.toCharArray()) {
		     freq[ch - 'a']++;
		 }
		 int countC = freq[c - 'a'];
		 int percentage = (countC * 100) / str.length();
		 
		 return percentage;

	}
	
	public static int countWordsWithPrefix(String[] words,String prefix) {
		int count=0;
		for(String word:words) {
			if(word.length()>=prefix.length() && word.startsWith(prefix)) {
				count++;
			}
		}
		return count;
	}
	
	public static int buyChoclate(int[] prices,int money) {
		int min=Integer.MAX_VALUE,secondMin=Integer.MAX_VALUE;
		for(int i=0;i<prices.length;i++) {
			if(prices[i]<min) {
				secondMin=min;
				min=prices[i];
			}else if(prices[i]<secondMin) {
				secondMin=prices[i];
			}
		}
		 int totalCost = min + secondMin;
		if(totalCost<=money) {
			return money -totalCost;
		}else {
			return money;
		}
	}
	
	public static int maxScoreAfterSplit(String s) {
		int maxCount=0;
		 for (int i = 1; i < s.length(); i++) {
		        String left = s.substring(0, i);   // first i chars
		        String right = s.substring(i);     // remaining chars

		        int countZeroOne = countZeroOne(left, right);
		        maxCount = Math.max(maxCount, countZeroOne);
		    }
		return maxCount;
	}
	
	public static int countZeroOne(String left,String right) {
		int zeroCount=0,oneCount=0;
		for(int i=0;i<left.length();i++) {
			if(left.charAt(i)=='0') {
				zeroCount++;
			}
		}
		for(int i=0;i<right.length();i++) {
			if(right.charAt(i)=='1') {
				oneCount++;
			}
		}
		return zeroCount+oneCount;
	}
	
	public static int maxScoreAfterSplit1(String s) {
	    int totalOnes = 0;
	    for(int i=0;i<s.length();i++) {
	    	if(s.charAt(i)=='1') {
	    		totalOnes++;
	    	}
	    }
	    int maxScore=0;
	    int leftZeros=0,rightOnes=totalOnes;
	    
	    for(int i=0;i<s.length()-1;i++) {
	    	if(s.charAt(i)=='0') {
	    		leftZeros++;
	    	}else {
	    		rightOnes--;
	    	}
	    	
	    	maxScore=Math.max(maxScore, leftZeros+rightOnes);
	    }
	    return maxScore;
	}
	
	public static double findMaxAvergae(int[] nums,int k) {
		double maxAverage=Double.NEGATIVE_INFINITY;
		for(int i=0;i<=nums.length-k;i++) {
			int sum=0;
			for(int j=i;j<i+k;j++) {
				sum +=nums[j];
			}
			maxAverage = Math.max(maxAverage, (double) sum / k);
		}
		return maxAverage;
	}
	
	public static boolean isPathCrossing(String path) {
		HashSet<String> set = new HashSet<String>();
		int x=0;
		int y=0;
		set.add(x+","+y);
		for(char ch:path.toCharArray()) {
			if(ch=='N') {
				y++;
			}
			if(ch=='S') {
				y--;
			}
			if(ch=='E') {
				x++;
			}
			if(ch=='W') {
				x--;
			}
			if(set.contains(x+","+y)) {
				return true;
			}
			set.add(x+","+y);
		}
		return false;
		
	}

	
	public static void main(String[] args) {
		String path ="NESE";
		System.out.println(isPathCrossing(path));
	}

}
