package com.faizan.leetcode.revision3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
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


	public static void main(String[] args) {
	  String[] operations= {"--X","X++","++X"};
	  System.out.println(findValueOp(operations));
	}

	

}
