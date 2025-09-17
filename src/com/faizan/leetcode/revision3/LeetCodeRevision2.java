package com.faizan.leetcode.revision3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class LeetCodeRevision2 {

	public static String captilize(String s) {
		String[] words = s.split(" ");
		StringBuilder builder = new StringBuilder();
		for (String word : words) {
			if (word.length() == 2) {
				builder.append(allLower(word)).append(" ");
			} else {
				builder.append(Character.toUpperCase(word.charAt(0))).append(allLower(word.substring(1))).append(" ");
			}

		}
		return builder.toString().trim();
	}

	private static String allLower(String word) {
		return word.toLowerCase();
	}

	public static int evenDigitSum(int num) {
		int count = 0, sum = 0;
		for (int i = 1; i <= num; i++) {
			sum += i;
			if (sum % 2 == 0) {
				count++;
			}
		}
		return count;
	}

	public static String[] sortPeople(String[] names, int[] height) {

		Integer[] indices = new Integer[height.length];
		String[] result = new String[names.length];
		for (int i = 0; i < height.length; i++) {
			indices[i] = i;
		}
		Arrays.sort(indices, (a, b) -> height[b] - height[a]);
		for (int i = 0; i < indices.length; i++) {
			result[i] = names[indices[i]];
		}
		return result;
	}

	public static String[] sortPeople1(String[] names, int[] height) {
		String[] result = new String[names.length];
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		for (int i = 0; i < height.length; i++) {
			map.put(height[i], names[i]);
		}
		Arrays.sort(height);
		int j = 0;
		for (int i = height.length - 1; i >= 0; i--) {
			result[j] = map.get(height[i]);
			j++;
		}
		return result;
	}

	public static int distinctAvgSum(int[] nums) {
		Arrays.sort(nums);
		HashSet<Integer> set = new HashSet<Integer>();
		int start = 0, end = nums.length - 1;
		while (start < end) {
			int avg = nums[start] + nums[end];
			set.add(avg);
			start++;
			end--;
		}
		return set.size();
	}

	public static boolean circularSentence(String str) {
		String[] words = str.split(" ");
		if (words.length == 1 && str.charAt(0) == str.charAt(str.length() - 1)) {
			return true;
		}
		if (words.length == 1 && str.charAt(0) != str.charAt(str.length() - 1)) {
			return false;
		}
		for (int i = 0; i < str.length() - 1; i++) {
			if (!Character.isLetter(str.charAt(i))
					&& Character.isLetter(str.charAt(i + 1)) != Character.isLetter(str.charAt(i - 1))
					|| (str.charAt(0) != str.charAt(str.length() - 1))) {
				return false;
			}
		}
		return true;

	}

	public static int maxValueStr(String[] strs) {
		int max = 0;
		for (String word : strs) {
			if (checkDigits(word)) {
				if (max < Integer.parseInt(word)) {
					max = Integer.parseInt(word);
				}
			} else {
				max = Math.max(max, word.length());
			}
		}
		return max;
	}

	private static boolean checkDigits(String word) {
		int count = 0;
		for (int i = 0; i < word.length(); i++) {
			if (Character.isDigit(i)) {
				count++;
			}
		}
		return count == word.length();
	}
	
	public static int countSimiliarPair(String[] words) {
		int count=0;
		for(int i=0;i<words.length-1;i++) {
			for(int j=i+1;j<words.length;j++) {
				if(checkEquilent(words[i], words[j])) {
					count++;
				}
			}
		}
		return count;
	}
	
	private static boolean checkEquilent(String word1 ,String word2) {
		HashSet<Character> set = new HashSet<Character>();
		HashSet<Character> set1 = new HashSet<Character>();
		for(int i=0;i<word1.length();i++) {
			set.add(word1.charAt(i));
		}
		
		for(int i=0;i<word2.length();i++) {
			set1.add(word2.charAt(i));
		}
		
		
		return set.size()==set1.size();
	}
	
	public static int posNegMax(int[] nums) {
		int posCount=0,negCount=0;
		for(int num:nums) {
			if(num<0) {
				negCount++;
			}else {
				posCount++;
			}
		}
		return Math.max(posCount, negCount);
	}
	
	public static int minCommonVal(int[] nums1,int[] nums2) {
		HashSet<Integer> set = new HashSet<Integer>();
		for(int num:nums1) {
			set.add(num);
		}
		for(int num:nums2) {
			if(set.contains(num)) {
				return num;
			}
		}
		return -1;
	} 
	
	public static int alterNateDigitSum(int num) {
		
		String str=num+"";
		int sum=Character.getNumericValue( str.charAt(0));
		for(int i=1;i<str.length();i++) {
			if(i%2!=0) {
				sum -=Character.getNumericValue(str.charAt(i));
			}else {
				sum +=Character.getNumericValue(str.charAt(i));
			}
		}
		return sum;
		
	}
	
	public static int concatArray(int[] nums) {
		int sum=0;
		int start=0,end=nums.length-1;
		while(start<end) {
			sum +=Integer.parseInt((String.valueOf(nums[start])+String.valueOf(nums[end])));
			start++;
			end--;
		}
		 // If odd length → add middle element
	    if (start == end) {
	        sum += nums[start];
	    }
		return sum;
	}
	
	public static int vowelStringCount(String[] words) {
		int count=0;
		for(String word:words) {
			if((word.charAt(0)=='a' ||word.charAt(0)=='e' || word.charAt(0)=='i' || word.charAt(0)=='o' || word.charAt(0)=='u')
				&& (word.charAt(word.length()-1)=='a' ||word.charAt(word.length()-1)=='e' || word.charAt(word.length()-1)=='i' || word.charAt(word.length()-1)=='o' || word.charAt(word.length()-1)=='u')) {
			 count++;	
			}
		}
		return count;
	}
	public static int delayedArrivalTime(int arrTime,int delTime) {
		int result=arrTime+delTime;
		if(result >=24) {
			result -=24;
		}
		return result;
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
		if(countScore(player1)>countScore(player2)) {
			return 1;
		}else if(countScore(player1)<countScore(player2)){
			return -1;
		}else {
			return 0;
		}
		
	}
	
	private static int countScore(int[] player) {
		int sum=0;
		for(int i=0;i<player.length;i++) {
			if(i==1) {
				if(player[i-1]==10) {
					sum += 2*player[i];
				}else {
					sum += player[i];
				}
			}else if(i>1) {
				if(player[i-1]==10 || player[i-2]==10) {
					sum += 2*player[i];
				}else {
					sum += player[i];
				}
			}
		}
		return sum;
	}
	public static int[] distinctDiff(int[] nums) {
		int[] result = new int[nums.length];
		for(int i=0;i<nums.length;i++) {
			result[i]=nums[i]-(nums.length-(i+1));
		}
		return result;
	}
	
	public static int totalDisTraveled(int mainTank,int additionalTank) {
		int totalDisTravled=0;
		while(mainTank>=5 && additionalTank>=1) {
			mainTank=(mainTank-5)+1;
			totalDisTravled=totalDisTravled+50;
			additionalTank--;
		}
		totalDisTravled =totalDisTravled+ mainTank*10;
		
		return totalDisTravled;
	}
	
	public static boolean isGood(int[] nums) {
		Arrays.sort(nums);
		int max =nums[nums.length-1];
		if(nums.length!=max+1) {
			return false;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0;i<nums.length;i++) {
			if(map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i])+1);
			}else {
				map.put(nums[i], 1);
			}
		}
		
		ArrayList<Integer> arrayList = new ArrayList<Integer>(map.values());
		int count=0;
		for(int num:arrayList) {
			if(num>=2){
				count++;
			}
		}
		if(map.get(max)==2 && count==1) {
			return true;
		}
		
		return false;
			
	}
	
	public static List<String> stringSeprator(List<String> list,String separtor){
		List<String> list1 = new ArrayList<String>();
		for(int i=0;i<list.size();i++) {
			String[] split = list.get(i).split("["+separtor+"]");
			for(String word:split) {
				list1.add(word);
			}
		}
		return list1;
	}
	
	public static List<String> stringSeprator1(String[] words,String separtor){
		List<String> list1 = new ArrayList<String>();
		for(int i=0;i<words.length;i++) {
			String[] split = words[i].split("["+separtor+"]");
			for(String word:split) {
				list1.add(word);
			}
		}
		return list1;
	}
	
	public static int purchaseAmount(int purchaseAmout) {
		
		int re=purchaseAmout%10;
		int balance=10-re;
		if(re>5) {
			purchaseAmout +=balance;
		}else {
			purchaseAmout -=re;
		}
		return purchaseAmout;
	}
	public static int purchaseAmount1(int purchaseAmount) {
	    return Math.round(purchaseAmount / 10.0f) * 10;
	}
	
	public static int maxPairSum(int[] nums) {
		int sum=0;
		for(int i=0;i<nums.length;i++) {
			  for(int j=i+1;j<nums.length;j++) {
				  if(checkMaxDigit(nums[i])==checkMaxDigit(nums[j])) {
					  sum= Math.max(sum, (nums[i]+nums[j]));
				  }
			  }
		}
		return sum;
	}
	public static int checkMaxDigit(int num) {
		int max=0;
		while(num!=0) {
			if(num%10>max) {
				max=num%10;
			}
			num=num/10;
		}
		return max;
	}
	
	public static boolean acronym(String[] words,String s) {
		StringBuilder builder = new StringBuilder();
		if(words.length !=s.length()) {
			return false;
		}
		for(String word:words) {
			builder.append(word.charAt(0));
		}
		return builder.toString().equals(s);
	}
	
	public static int minimumOperation(int[] nums,int k) {
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i=1;i<=k;i++) {
			set.add(i);
		}
		int count=0;
		for(int num:nums) {
			if(set.contains(num)) {
				set.remove(num);
				if(set.isEmpty()) {
					break;
				}
			}
			count++;
		}
		return count;
	}
	public static int sumDiff(int n,int m) {
		int divSum=0,nonDivSum=0;
		for(int i=1;i<=n;i++) {
			if(i%m==0) {
				divSum=divSum+i;
			}else {
				nonDivSum +=i;
			}
		}
		return Math.abs(divSum-nonDivSum); 
	}
	
	public static int largestGain(int[] gain) {
		int currentGain=0,maxGain=0;
		for(int i=0;i<gain.length;i++) {
			currentGain=currentGain+gain[i];
			maxGain=Math.max(currentGain, maxGain);
		}
		return maxGain;
	}
	
	public static int[] indexValDiff(int[] nums,int valDiff, int indexDiff) {
		for(int i=0;i<nums.length;i++) {
			for(int j=i+1;j<nums.length;j++) {
				if(Math.abs(i-j) >=indexDiff && Math.abs(nums[i]-nums[j])==valDiff) {
					return new int[] {i,j};
				}
			}
		}
		return new int[] {-1,-1};
	}
	
	public static boolean wordPattern(String s,String pattern) {
		String[] words = s.split(" ");
		if(words.length!=pattern.length()){
			return false;
		}
		HashMap<Character, String> char_map = new HashMap<Character, String>();
		HashMap<String, Character> word_map = new HashMap<String, Character>();
		
		for(int i=0;i<words.length;i++) {
			char ch= pattern.charAt(i);
			String word=words[i];
			if(!char_map.containsKey(ch)) {
				if(word_map.containsKey(word)) {
					return false;
				}else {
					char_map.put(ch, word);
					word_map.put(word, ch);
				}
			}else {
				if(!char_map.get(ch).equals(word)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static int[] leftRightSumDiff(int[] nums) {
		int sum=0;
		int[] leftSumArr= new int[nums.length];
		int[] rightSumArr=new int[nums.length];
		leftSumArr[0]=sum;
		for(int i=0;i<nums.length-1;i++) {
			sum +=nums[i];
			leftSumArr[i+1]=sum;
		}
		sum=0;
		rightSumArr[nums.length-1]=0;
		for(int i=nums.length-1;i>0;i--) {
			sum +=nums[i];
			rightSumArr[i-1]=sum;
		}
		int[] ans=new int[nums.length];
		for(int i=0;i<nums.length;i++) {
			ans[i]=Math.abs(leftSumArr[i]-rightSumArr[i]);
		}
		
		
		return ans;
		
	}
	
	public static int[] leftRightSumDiffArr(int[] nums) {
		int totalSum=0;
		for(int num:nums) {
			totalSum +=num;
		}
		int leftSum=0;
		int[] ans= new int[nums.length];
		for(int i=0;i<nums.length;i++) {
			int rightSum=totalSum-leftSum-nums[i];
			ans[i]=Math.abs(leftSum-rightSum);
			leftSum +=nums[i];
		}
		return ans;
	}
	
	public static List<ArrayList<Integer>> arrayDiff(int[] nums1,int[] nums2){
		return Arrays.asList(arrayDiffUtil(nums1, nums2),arrayDiffUtil(nums2, nums1));
	}
	
	private static ArrayList<Integer> arrayDiffUtil(int[] nums1,int[] nums2){
		HashSet<Integer> set = new HashSet<Integer>();
		HashSet<Integer> set1 = new HashSet<Integer>();
		
		for(int num:nums2) {
			set1.add(num);
		}
		for(int num:nums1) {
			if(!set1.contains(num)) {
				set.add(num);
			}
		}
		
		return new ArrayList<Integer>(set);
	}
	
	public static int empWithTarget(int[] nums,int target) {
		int count=0;
		for(int num:nums) {
			if(num>=target) {
				count++;
			}
		}
	return	count;
	}
	
	public static boolean contiguesOnes(String s) {
		int currentOneCount=0,currentZeroCount=0,maxZero=0,maxOne=0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='1') {
				currentOneCount++;
				currentZeroCount=0;
				maxOne=Math.max(maxOne, currentOneCount);
			}else {
				currentOneCount=0;
				currentZeroCount++;
				maxZero=Math.max(maxZero, currentZeroCount);
			}
		}
		return maxOne>maxZero;
	}
	
	public static int consecutioveChars(String s) {
		int currentCount=1,max=1;
		for(int i=1;i<s.length();i++) {
			if(s.charAt(i-1)==s.charAt(i)) {
				currentCount++;
			}else {
				currentCount=1;
			}
			max=Math.max(max, currentCount);
		}
		return max;
	}
	
	public static int maxBallons(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('b', 0);
        map.put('a', 0);
        map.put('l', 0);
        map.put('o', 0);
        map.put('n', 0);
        
        for(int i=0;i<s.length();i++) {
        	if(map.containsKey(s.charAt(i))) {
        		map.put(s.charAt(i), map.get(s.charAt(i))+1);
        	}
        }
        
        int min=map.get('b');
        min=Math.min(min, map.get('a'));
        min=Math.min(min, map.get('a')/2);
        min=Math.min(min, map.get('o')/2);
        min=Math.min(min, map.get('n'));
        return min;
	}

	public static void main(String[] args) {
		String s="afaebollon";
		System.out.println(maxBallons(s));
	}

}
