package com.faizan.leetcode.revision1;

import java.util.Arrays;
import java.util.HashSet;

public class LeetCodeRevision6 {
	
	public static int alternativeDigitSum(int num) {
		String str=num+"";
		int sum=Character.getNumericValue(str.charAt(0));
		for(int i=1;i<str.length();i++) {
			if(i%2!=0) {
				sum -=Character.getNumericValue(str.charAt(i));
			}else {
				sum +=Character.getNumericValue(str.charAt(i));
			}
			
		}
		return sum;
	}
	
	public static int concatenationValue(int[] nums) {
		int sum=0;
		int left=0;
		int right=nums.length-1;
		while(left<right) {
			sum += Integer.parseInt(String.valueOf(nums[left]) +String.valueOf(nums[right]));
			left++;
			right--;
		}
		if(nums.length %2!=0) {
			sum += nums[left];
		}
		
      return sum;		
	}
	
	public static int vowelWord(String[] words) {
		int count=0;
		for(String word:words) {
			if((word.charAt(0)=='a' ||word.charAt(0)=='e' || word.charAt(0)=='i' || word.charAt(0)=='o' || word.charAt(0)=='u')
					 && (word.charAt(word.length()-1)=='a' ||word.charAt(word.length()-1)=='e' || word.charAt(word.length()-1)=='i' || word.charAt(word.length()-1)=='o' || word.charAt(word.length()-1)=='u')) {
				count++;
			}
		}
		return count;
	}
	
	public static int delayedTimeArrival(int arrivalTime,int delayedTime) {
		int result=arrivalTime+delayedTime;
		if(result >=24) {
			result -=24;
		}
		return result;
	}
	
	public static int isWinner(int[] player1,int[] player2) {
		int player1Score=countScore(player1);
		int player2Score=countScore(player2);
		
		if(player1Score>player2Score) {
			return 1;
		}else if(player2Score>player1Score) {
			return 2;
		}
		return 0;
	}
	
	private static int countScore(int[] player) {
		int score=0;
		for(int i=0;i<player.length;i++) {
			if(i==1) {
				if(player[i-1]==10) {
					score +=2*player[i];
				}else {
					score=player[i];
				}
			}else if(i>1) {
				if(player[i-1] ==10 || player[i-2]==10) {
					score +=2*player[i];
				}else {
					score=player[i];
				}
			}
		}
		return score;
	}
	
	public static int[] distinctArrayDiff(int[] nums) {
		int[] diff= new int[nums.length];
		for(int i=0;i<nums.length;i++) {
			HashSet<Integer> set= new HashSet<Integer>();
			for(int j=0;j<=i;j++) {
				set.add(nums[j]);
			}
			HashSet<Integer> set1= new HashSet<Integer>();
			for(int k=i+1;k<nums.length;k++){
				set1.add(nums[k]);
				
				}
			diff[i]= (set.size()-set1.size());
			}
		return diff;
	}
	
	public static int totaDistanceTraveled(int mainTank,int additionalTank) {
		int total =0;
		while(mainTank>=5 && additionalTank>0) {
			total +=50;
			mainTank =(mainTank-5)+1;
			additionalTank--;
		}
		total +=mainTank*10;
		return total;
	}
	public static void main(String[] args) {
		//int mainTank=5,additionalTank=10;
		//System.out.println(totaDistanceTraveled(mainTank, additionalTank));
		//int[] nums= {1,2,3,4,5};
		//System.out.println(Arrays.toString(distinctArrayDiff(nums)));
		//nt[] player1= {4,10,7,9};
		//int[] player2= {6,5,2,3};
		//System.out.println(isWinner(player1, player2));
		//int arrivalTime=20,delayedTime=5;
		//System.out.println(delayedTimeArrival(arrivalTime, delayedTime));
		//String[] words= {"are","any","a"};
		//System.out.println(vowelWord(words));
		//int[] nums= {7,52,2,4,1};
		//System.out.println(concatenationValue(nums));
		//int num=521;
		//System.out.println(alternativeDigitSum(num));
	}

}
