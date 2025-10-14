package com.faizan.leetcode.revision3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCodeRevision3 {
	
	public static int findChampion(int[][] grid) {
		
		for(int i=0;i<grid.length;i++) {
			int count=0;
			for(int j=0;j<grid[0].length;j++) {
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
	
	public static boolean checkStringEq(String[] word1,String[] word2) {
		StringBuilder builder = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		for(String word:word1) {
			builder.append(word);
		}
		for(String word:word2) {
			builder2.append(word);
		}
		return builder.toString().equals(builder2.toString());
		
	}
	
	public static int elementAppear(int[] arr) {
		int limit = arr.length / 4;
		for(int i=0;i<arr.length-limit;i++) {
			if(arr[i]==arr[i+limit]) {
				return arr[i];
			}
		}
		return -1;
	}
	public static int[] findCommonElements(int[] nums1,int[] nums2) {
		int[] count1=new int[101];
		int[] count2=new int[101];
		int[] ans=new int[2];
		
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
	public static int specailPosition(int[][] mat) {
		int row =mat.length;
		int col=mat[0].length;
		int count=0;
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				
				if(mat[i][j]==0) {
					continue;
				}
				boolean flag=false;
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
	
	public static boolean isSequence(String s,String t) {
		int index=0;
		for(int i=0;i<t.length();i++) {
			if(s.charAt(index)==t.charAt(i)) {
				index++;
			}
			if(s.length()==index) {
				return true;
			}
		}
		return false;
	}
	public static boolean isSubsequence1(String s, String t) {
	    int i = 0, j = 0; 
	    while (i < s.length() && j < t.length()) {
	        if (s.charAt(i) == t.charAt(j)) {
	            i++;
	        }
	        j++;
	    }
	    return i == s.length();
	}
	public static boolean canPlaceFlower(int[] flowerBer, int n) {
		int count=0;
		for(int i=0;i<flowerBer.length;i++) {
			if(flowerBer[i]==0) {
				if((i==0 || flowerBer[i-1]==0) && (i==flowerBer.length-1 ||  flowerBer[i+1]==0)) {
					count++;
					if(count==n) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static int removeElement(int[] nums,int k) {
		int len=nums.length;
		int i=0;
		while(i<len) {
			if(nums[i]==k) {
				nums[i]=nums[len-1];
				len--;
			}else {
				i++;
			}
			
		}
		return len;
	}
	
	public static String greatestCommonD(String str1,String str2) {
		int len1=str1.length(),len2=str2.length();
		String result = str1.substring(0,gcd(len1,len2));
		return result;
	}
	private static int gcd(int len1, int len2) {
		if(len2==0) {
			return len1;
		}
		
		return gcd(len2,len1%len2);
	}
	public static String destCity(String[][] paths) {
		HashSet<String> set1= new HashSet<String>();
		for(int i=0;i<paths.length;i++) {
			set1.add(paths[i][0]);
		}
		for(int i=0;i<paths.length;i++) {
			if(!set1.contains(paths[i][1])) {
				return paths[i][1];
			}
			
		}
		return "";
	}
	
	public static int pivotIndex(int[] nums) {
	    int start = 0, end = nums.length - 1;
	    int leftSum = 0, rightSum = 0;

	    while (start <= end) {
	        if (leftSum == rightSum && start == end) {
	            return start; // pivot found
	        }

	        if (leftSum <= rightSum) {
	            leftSum += nums[start];
	            start++;
	        } else {
	            rightSum += nums[end];
	            end--;
	        }
	    }
	    return -1; // no pivot
	}

	public static int pivotIndex1(int[] nums) {
		int sum=0,leftSum=0;
		for(int i=0;i<nums.length;i++) {
			sum +=nums[i];
		}
		for(int i=0;i<nums.length;i++) {
			if(leftSum==sum-leftSum-nums[i]) {
				return i;
			}
			leftSum +=nums[i];
		}
		return -1;
	}
	public static boolean uniqueOccurrences(int[] arr) {
	    Map<Integer, Long> freq = Arrays.stream(arr).boxed()
	            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

	    return freq.size() == new HashSet<>(freq.values()).size();
	}
	public static String reverseVolwel(String s) {
		char[] words= s.toCharArray();
		String vowels="AEIOUaeiou";
		int start =0,end =words.length-1;
		while(start<end) {
			while(start<end && vowels.indexOf(words[start])==-1) {
				start++;
			}
			while(start<end && vowels.indexOf(words[end])==-1) {
				end--;
			}
			char temp= words[start];
			words[start]=words[end];
			words[end]=temp;
			start++;
			end--;
		}
		return new String(words);
	}
	
	public static int findValueOp(String[] operations) {
		int result=0;
		for(String op:operations) {
			if(op.equals("X++") || op.equals("++X")) {
				result +=1;
			}else if(op.equals("--X") || op.equals("X--")) {
				result -=1;
		}
	}
		return result;
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
	public static int[] findMissingAndRepeatedValue(int[][] grid) {
		int n=grid.length;
		int a = 0,b = 0;
		HashSet<Integer> set = new HashSet<Integer>();
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
	
	public static int maxNumberOfWords(String[] sentence) {
		int maxCount=0;
		for(String sen:sentence) {
			String[] words = sen.split(" ");
			maxCount=Math.max(maxCount, words.length);
		}
		return maxCount;
	}
	
	public static String removeTrailingZero(String s) {
		int start =0,end=s.length()-1;
		while(start<end) {
			if(s.charAt(end)=='0') {
				end--;
			}else {
				break;
			}
		}
		return s.substring(start, end+1);
	}
	public static int percentage(String s,char letter) {
		 if (s == null || s.length() == 0) return 0; // avoid divide by zero
		int count=0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)==letter) {
				count++;
			}
		}
		return (count*100)/s.length();
	}
	
   public static int percentage1(String s,char letter) {
		 if (s == null || s.length() == 0) return 0; // avoid divide by zero
		 int[] freq = new int[26];
		 for(char ch:s.toCharArray()) {
			 freq[ch-'a']++;
		 }
		 int count = freq[letter-'a'];
		 return (count*100)/s.length();
   }
   
   public static int countWordPrefix(String[] words,String prefix) {
	   int count=0;
	   for(String word:words) {
		 if(word.startsWith(prefix)) {
			 count++;
		 }
	   }
	return count;
   }
   public static int buyChoclates(int[] prices,int money) {
	   Arrays.sort(prices);
	 int result=  money -(prices[0]+prices[1]);
	 if(result>=0) {
		 return result;
	 }else {
		 return 0;
	 }
	    
   }
   
   public static int buyChoclates1(int[] prices,int money) {
	  int min=Integer.MAX_VALUE,secondMin=Integer.MAX_VALUE;
	  for(int price:prices) {
		  if(price<min) {
			  secondMin=min;
			  min=price;
		  }else if(price<secondMin) {
			  secondMin=price;
		  }
	  }
	 int result=  money -(min+secondMin);
	 if(result>=0) {
		 return result;
	 }else {
		 return 0;
	 }
	    
   }
   
   public static int maxScoreAfterSplit1(String s) {
	   int totalOnes=0;
	   for(int i=0;i<s.length();i++) {
		   if(s.charAt(i)=='1') {
			   totalOnes++;
		   }
	   }
	   int maxScore=0;
	   int leftZero=0,rightOne=totalOnes;
	   for(int i=0;i<s.length()-1;i++) {
		   if(s.charAt(i)=='0') {
			   leftZero++;
		   }else {
			   rightOne--;
		   }
		   maxScore = Math.max(maxScore, leftZero+rightOne);
	   }
	   return maxScore;
   }
   
   public static double maxAvgSubarray(int[] nums,int k) {
	   int maxSum=0;
	   for(int i=0;i<=nums.length-k;i++) {
		   int sum=0;
		   for(int j=i;j<k+i;j++) {
			  sum +=nums[j]; 
		   }
		   maxSum=Math.max(maxSum, sum);
	   }
	return (double)maxSum/k;
   }
   
   public static boolean pathCross(String path) {
	   int x=0;
	   int y=0;
	   HashSet<String> set = new HashSet<String>();
	   set.add(x+" "+y);
	   for(int i=0;i<path.length();i++) {
		   char ch= path.charAt(i);
		   if(ch=='N') {
			   y++;
		   }
		   if(ch=='S') {
			   y--;
		   }
		   
		   if(ch=='E') {
			   x++;
		   }
		   if(ch=='w') {
			   x--;
		   }
		   if(set.contains(x+" "+y)) {
			   return true;
		   }
		   set.add(x+" "+y);
		   
	   }
	return false;
   }
	public static int minOprationInAltenate(String s) {
		int startWith1=0; //010
		int startWith0=0;//101
		
		for(int i=0;i<s.length();i++) {
			if(i%2==0) {
				if(s.charAt(i)=='0') {
					startWith1++;
				}else {
					startWith0++;
				}
			}else {
				if(s.charAt(i)=='1') {
					startWith1++;
				}else {
					startWith0++;
				}
			}
		}
		return Math.min(startWith1, startWith0);
	}
	public static boolean isMonolatic(int[] nums) {
		boolean isIncreasing=true;
		boolean isDecreasing=true;
		
		for(int i=1;i<nums.length;i++) {
			if(nums[i-1]>nums[i]) {
				isIncreasing=false;
			}else if(nums[i-1]<nums[i]) {
				isDecreasing=false;
			}
			
		}
		if(isDecreasing || isIncreasing) {
			return true;
		}
		return isDecreasing;
	}
	
	public static int[] minNumberOfGame(int[] nums) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		int[] result = new int[nums.length];
		for(int num:nums) {
			queue.add(num);
		}
		int i=0;
		while(!queue.isEmpty()) {
		 int alice=queue.poll();
		 int bob= queue.poll();
		 result[i++]=bob;
		 result[i++]=alice;
		}
		return result;
	}
	
	public static int incRemovableSubArray(int[] nums) {
		int count=0;
		for(int i=0;i<nums.length;i++) {
			for(int j=i;j<nums.length;j++) {
				if(isIncreasingSubArray(nums, i, j)) {
					count++;
				}
			}
		}
		return count;
	}


	private static boolean isIncreasingSubArray(int[] nums, int start, int end) {
		int prev=0;
		for(int i=0;i<nums.length;i++) {
			if(i<=end && i>=start) {
				continue;
			}
			if(nums[i]<=prev) {
				return false;
			}
			prev=nums[i];
		}
		return true;
	}
	 public static List<String> stringMatching(String[] words){
		 List<String> list = new ArrayList<String>();
		 for(int i=0;i<words.length;i++) {
			 for(int j=0;j<words.length;j++) {
				 String word1=words[i];
				 String word2=words[j];
				 if(word1.length()>=word2.length()) {
					 continue;
				 }
				 if(isSubstring(words[i],words[j])) {
					 list.add(words[i]);
				 }
			 }
		 }
		return list;
	 }

	private static boolean isSubstring(String word1, String word2) {
		if(word2.contains(word1)) {
			return true;
		}
		return false;
	}
	
	public static boolean makeEqual(String[] words) {
		int[] freq = new int[26];
		if(words.length==1) {
			return true;
		}
		for(String word:words) {
			for(int i=0;i<word.length();i++) {
				freq[word.charAt(i)-'a']++;
			}
		
		}
		for(int i:freq) {
			if(i%(words.length)!=0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String[] words = {"abc","aabc","bc"};
		System.out.println(makeEqual(words));
	}

	

}
