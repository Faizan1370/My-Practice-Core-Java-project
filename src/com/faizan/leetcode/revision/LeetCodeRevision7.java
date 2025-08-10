package com.faizan.leetcode.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class LeetCodeRevision7 {
	
	public static int countVowelString(String[] words,int left,int right) {
		int count=0;
		for(int i=left;i<=right;i++) {
			if(checkVowelString(words[i])) {
				count++;
			}
		}
		return count;
	}
	
	public static int delayedArrivalTime(int arrivalTime,int delayedTime) {
		int delayedArrivalTime=arrivalTime+delayedTime;
		if(delayedArrivalTime>=24) {
			delayedArrivalTime=delayedArrivalTime % 24;
		}
		return delayedArrivalTime;
		
	}
	
	private static boolean checkVowelString(String word) {
		if((word.charAt(0)=='a' || word.charAt(0)=='e' || word.charAt(0)=='i' || word.charAt(0)=='o' || word.charAt(0)=='u')
				&& (word.charAt(word.length()-1)=='a' || word.charAt(word.length()-1)=='e' || word.charAt(word.length()-1)=='i' || word.charAt(word.length()-1)=='o' || word.charAt(word.length()-1)=='u')){
			return true;
		}
		return false;
	}
	public static int sumOfMultiples(int n) {
		int sum=0;
		for(int i=1;i<=n;i++) {
			if(i%3==0 || i%5==0 || i%7==0) {
				sum +=i;
			}
		}
		return sum;
	}
	public static int isWinner(int[] player1,int[] player2) {
		int p1_score=countScore(player1);
		int p2_score=countScore(player2);
		if(p1_score>p2_score) {
			return 1;
		}else if(p2_score>p1_score) {
			return 2;
		}
		
		return 0;
	}
	private static int countScore(int[] player) {
		int score=0;
		for(int i=0;i<player.length;i++) {
			if(i==1) {
				if(player[i-1]==10) {
					score +=(2*player[i]);
				}else {
					score +=player[i];
				}
			}else if(i>1) {
				if(player[i-1]==10 || player[i-2]==10) {
					score +=(2*player[i]);
				}else {
					score +=player[i];
				}
			}
		}
		return score;
	}
	
	public static int[] distictArrayDiffence(int[] nums) {
		int[] diff=new int[nums.length];
		
		for(int i=0;i<nums.length;i++) {
			HashSet<Integer> set = new HashSet<Integer>();
			for(int j=0;j<=i;j++) {
				set.add(nums[j]);
			}
			
			HashSet<Integer> set1 = new HashSet<Integer>();
			for(int j=i+1;j<nums.length;j++) {
				set1.add(nums[j]);
			}
			diff[i]=set.size()-set1.size();
		}
		return diff;
	}
	public static int totalDistanceTraveled(int mainTank,int additionalTank) {
		int distanceTraveled=0;
		while(mainTank>=5 && additionalTank>0) {
			distanceTraveled +=50;
			mainTank =(mainTank-5)+1;
			additionalTank--;
		}
		distanceTraveled +=mainTank*10;
		return distanceTraveled;
	}
	
	public static boolean isGood(int[] nums) {
		Arrays.sort(nums);
		int max=nums[nums.length-1];
		if(nums.length!=(max+1)) {
			return false;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0;i<nums.length;i++) {
			if(map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i])+1);
			}else {
				map.put(nums[i],1);
			}
		}
		int count=0;
		ArrayList<Integer> list = new ArrayList<Integer>(map.values());
		for(int num:list) {
			if(num>=2) {
				count++;
			}
		}
		if(map.get(max)==2 && count==1) {
			return true;
		}
		return false;
		
	}
	
	public static boolean isGood1(int[] nums) {
	    Arrays.sort(nums);
	    int max = nums[nums.length - 1];

	    // Length must be max + 1
	    if (nums.length != max + 1) {
	        return false;
	    }

	    HashMap<Integer, Integer> map = new HashMap<>();
	    for (int num : nums) {
	        map.put(num, map.getOrDefault(num, 0) + 1);
	    }

	    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
	        int key = entry.getKey();
	        int freq = entry.getValue();

	        if (key == max) {
	            if (freq != 2) return false; // max must occur twice
	        } else {
	            if (freq != 1) return false; // others must occur once
	        }
	    }

	    return true;
	}
	
	public static List<String> splitWordsbySeprator(List<String> words,String seprator){
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<words.size();i++) {
			String[] wordArray = words.get(i).split("["+seprator+"]");
			for(String str:wordArray) {
				list.add(str);
			}
		}
		return list;
	}
	
	public static int roundedAmount(int purchaseAmount) {
		int rem=purchaseAmount%10;
		int balance=10-rem;
		if(rem<5) {
			purchaseAmount =purchaseAmount-rem;
		}else {
			purchaseAmount= purchaseAmount+balance;
		}
		return 100-purchaseAmount;
	}

	
	public static void main(String[] args) {
		int purchaseAmunt=16;
		System.out.println(roundedAmount(purchaseAmunt));
		//List<String> list= Arrays.asList("one.two.three","four.five","six");
		//String seprator=".";
		//System.out.println(splitWordsbySeprator(list, seprator));
		//int[] nums= {1,3,3,2};
		//System.out.println(isGood1(nums));
		//int maintank=5;
		//int additionalTank=10;
		//System.out.println(totalDistanceTraveled(maintank, additionalTank));
		//int[] nums= {1,2,3,4,5};
		//System.out.println(Arrays.toString(distictArrayDiffence(nums)));
		//int[] player1= {4,10,7,9};
		//int[] player2= {6,5,2,3};
		//System.out.println(isWinner(player1, player2));
		//int arrivalTime=21,delayedTime=5;
		//System.out.println(delayedArrivalTime(arrivalTime, delayedTime));
		//int n=7;
		//System.out.println(sumOfMultiples(n));
		//int left=0,right=2;
		//String[] words= {"are","any","a"};
		//System.out.println(countVowelString(words,left,right));
	}

}
