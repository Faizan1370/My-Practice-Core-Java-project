package com.faizan.leetcode.revision4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.faizan.design.pattrens.factory.prac5.Poco;

public class LeetCodeRevision2 {
	
	public static int smallestIndex(int[] nums) {
		for(int i=0;i<nums.length;i++) {
			if(i %10 ==nums[i]) {
				return i;
			}
		}
		return -1;
	}
	public static boolean equivalentString(String word1,String word2) {
		if(word1.length()!=word2.length()) {
			return false;
		}
		int[] freq1 = new int[100];
		int[] freq2= new int[100];
		for(int i=0;i<word1.length();i++) {
			freq1[word1.charAt(i)-'a']++;
		}
		for(int i=0;i<word2.length();i++) {
			freq2[word2.charAt(i)-'a']++;
		}
		for(int i=0;i<freq1.length;i++) {
			if((freq1[i]-freq2[i])>3) {
				return false;
			}
		}
		return true;
	}
	public static int countWords(String[] word1,String[] word2) {
		Map<String, Long> map = Arrays.stream(word1).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		for(String word:word2) {
			if(map.containsKey(word)) {
				map.put(word, map.get(word)-1);
			}
		}
		int count=0;
		for(String word:word2) {
			if(map.containsKey(word) && map.get(word)==0) {
				count++;
			}
		}
		return count;
	}
	public static int countWords1(String[] word1,String[] word2) {
		Map<String, Long> map = Arrays.stream(word1).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		Map<String, Long> map1 = Arrays.stream(word2).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		int count=0;
		for(Map.Entry<String, Long> enty:map.entrySet()) {
			if(map.containsKey(enty.getKey()) && map1.containsKey(enty.getKey()) && map.get(enty.getKey())==1L && map1.get(enty.getKey())==1L) {
				count++;
			}
		}
		return count;
	}
	
	public static String firstPlaindramic(String[] words) {
		for(String word:words) {
			if(checkPlain(word)) {
				return word;
			}
		}
		return "";
	}
	private static boolean checkPlain(String word) {
		int start=0,end=word.length()-1;
		while(start<end) {
			if(word.charAt(start)!=word.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
	
	public static String captilizeTitle(String title) {
		String[] words = title.split("\\s+");
		StringBuilder builder = new StringBuilder();
		for(String word:words) {
			if(word.length()<=2) {
				builder.append(allLower(word)).append(" ");
			}else {
				builder.append(firstCap(word)).append(" ");
			}
		}
		return builder.toString().trim();
	}
	public static String allLower(String word) {
		return word.toLowerCase();
	}
	public static String firstCap(String word) {
		StringBuilder builder = new StringBuilder();
		builder.append(Character.toUpperCase(word.charAt(0)));
		builder.append(allLower(word.substring(1)));
		return builder.toString();
		
	}
	
	public static int evenDigitsSum(int num) {
		int sum=0;
		int count=0;
		for(int i=1;i<=num;i++) {
			sum +=i;
			if((sum & 1)==0) {
				count++;
			}
		}
		return count;
	}
	
	public static String[] sortPeople(int[] height,String[] names) {
		if(height.length!=names.length) {
			return new String[] {"",""};
		}
		Integer[] indices= new Integer[height.length];
		for(int i=0;i<height.length;i++) {
			indices[i]=i;
		}
		Arrays.sort(indices,(a,b)->height[b]-height[a]);
		String[] result = new String[names.length];
		for(int i=0;i<indices.length;i++) {
			result[i]=names[indices[i]];
		}
		return result;
	}
	
	public static int distinctAvg(int[] nums) {
		Arrays.sort(nums);
		int start=0,end=nums.length-1;
		HashSet<Integer> set = new HashSet<Integer>();
		while(start<end) {
			set.add(nums[start]+nums[end]);
			start++;
			end--;
		}
		return set.size();
	}
	public static double[] tempConverter(double celcius) {
	 return new double[] {celcius+273.15,celcius*1.80 +32.00}; 
	}
	
	public static boolean circularSentence(String sentence) {
		if(sentence.charAt(0)!=sentence.charAt(sentence.length()-1)) {
			return false;
		}
		for(int i=0;i<sentence.length();i++) {
			if(Character.isWhitespace(sentence.charAt(i))) {
				// Avoid out-of-bounds: ensure there is a previous and next character
	            if (i == 0 || i == sentence.length() - 1) {
	                return false; // space at start or end means invalid
	            }
				if(sentence.charAt(i+1)!=sentence.charAt(i-1)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static int maxValue(String[] strs) {
		int max=0;
		for(String word:strs) {
			if(chDigits(word)) {
				if(max<Integer.parseInt(word)) {
					max=Integer.parseInt(word);
				}else {
					max=Math.max(max, word.length());
				}
			}
		}
		return max;
	}
	private static boolean chDigits(String word) {
		int count=0;
		for(int i=0;i<word.length();i++) {
			if(Character.isDigit(word.charAt(i))) {
				count++;
			}
		}
		return count==word.length();
	}
	
	public static int countSimiliarPair(String[] words) {
		int count=0;
		for(int i=0;i<words.length;i++) {
			for(int j=i+1;j<words.length;j++) {
				if(checkEquivalent(words[i],words[j])) {
					count++;
				}
			}
		}
		return count;
	}
	private static boolean checkEquivalent(String string, String string2) {
		HashSet<Character> set = new HashSet<Character>();
		HashSet<Character> set1 = new HashSet<Character>();
		for(int i=0;i<string.length();i++) {
			set.add(string.charAt(i));
		}
		for(int i=0;i<string2.length();i++) {
			set1.add(string2.charAt(i));
		}
		
		return set.equals(set1);
	}
	
	public static int maxPosNeg(int[] nums) {
		int negCount=0,posCount=0;
		for(int num:nums) {
			if(num<0) {
				negCount++;
			}else {
				posCount++;
			}
		}
		return Math.max(negCount, posCount);
	}
	
	public static int smallestCommon(int[] nums1,int[] nums2) {
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
	public static int alternateDigitSum(int num) {
		String numStr=num+"";
		int sum=Character.getNumericValue(numStr.charAt(0));
		for(int i=1;i<numStr.length();i++) {
			if(i%2==0) {
				sum +=Character.getNumericValue(numStr.charAt(i));
			}else {
				sum -=Character.getNumericValue(numStr.charAt(i));
			}
		}
		return sum;
				
	}
	
	public static int arrayConcat(int[] nums) {
		int sum=0;
		int start=0;
		int end=nums.length-1;
		while(start<=end) {
			String result = String.valueOf(nums[start]) + String.valueOf(nums[end]);
			sum +=Integer.parseInt(result);
			start++;
			end--;
		}
		return sum;
		}
	
	
	public static int countVowel(String[] words) {
		int count=0;
		for(int i=0;i<words.length;i++) {
			char first=words[i].charAt(0);
			char last =words[i].charAt(words[i].length()-1);
			if((first=='a' || first=='e' || first=='i' || first=='o' || first=='u') 
					&& (last=='a' || last=='e' || last=='i' || last=='o' || last=='u' )) {
				count++;
			}
		}
		return count;
	}
	
	public static int delayedArraivalTime(int arrTime,int delTime) {
		int delArrTime=0;
		if((arrTime+delTime)>=24) {
			delArrTime=(arrTime+delTime)-24;
		}else {
			delArrTime=arrTime+delTime;
		}
		return delArrTime;
	}
	public static int delayedArrivalTime(int arrTime, int delTime) {
	    return (arrTime + delTime) % 24;
	}
	
	public static int sumOfMultiples(int n) {
		int sum=0;
		for(int i=1;i<=n;i++) {
			if( i%3==0 || i% 5==0 || i % 7==0) {
				sum =sum+i;
			}
		}
		return sum;
	}
	
	public static int isWinner(int[] player1,int[] player2) {
		if(playerScore(player1)>playerScore(player2)) {
			return 1;
		}else if(playerScore(player1)<playerScore(player2)) {
			return -1;
		}else {
			return 0;
		}
	}
	
	private static int playerScore(int[] player) {
		int playerSum=0;
		for(int i=0;i<player.length;i++) {
			 if (i == 0) {
	                // first throw, no previous element
	                playerSum += player[i];
	            }
			 else if(i==1) {
				if(player[i-1]==10) {
					playerSum +=2*player[i];
				}else {
					playerSum +=player[i];
				}
			}else if(i>1) {
				if(player[i-1]==10 || player[i-2]==10) {
					playerSum +=2*player[i];
				}else {
					playerSum +=player[i];
				}
			}
		}
		return playerSum;
	}

	   public static int[] distinctDiffrenceArray(int[] nums) {
		  
		   
		   int[] diff = new int[nums.length];
		   for(int i=0;i<nums.length;i++) {
			   HashSet<Integer> prefix= new HashSet<Integer>();
			   for(int j=0;j<=i;j++) {
				   prefix.add(nums[j]);
			   }
			   HashSet<Integer> suffix= new HashSet<Integer>();
			   for(int j=i+1;j<nums.length;j++) {
				   suffix.add(nums[j]);
			   }
			   diff[i]=prefix.size()-suffix.size();
		   }
		  
		return diff;
		   
		   
	   }
	   
	   public static int totatDistanceTravled(int mainTank,int addTank) {
		   int totalDistance=0;
		   while(mainTank>=5 &&  addTank>0) {
			   mainTank =(mainTank -5)+1;
			   totalDistance +=50;
			   addTank--;
		   }
		   totalDistance += mainTank*10;
		   
		   return totalDistance;
		   
	   }
	   public static boolean isGood(int[] nums) {
		   Arrays.sort(nums);
		   int max=nums[nums.length-1];
		   if(nums.length!=(max+1)){
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
		   ArrayList<Integer> list = new ArrayList<Integer>(map.values());
		   int count=0;
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
	   
	   public static List<String> splitWordsbySeprator(List<String> words,String seprator){
		   List<String> list = new ArrayList<String>();
		  for(int i=0;i<words.size();i++) {
			  String[] wordArr = words.get(i).split("["+seprator+"]");
			  for(String word:wordArr) {
				  list.add(word);
			  }
		  }
		return list;
	   }
	   
	   public static int purchseAmount(int purchaseAmount) {
		   int rem=purchaseAmount %10;
		   int balance=10-rem;
		   if (purchaseAmount < 0) return 100;
		    if (purchaseAmount > 100) return 0;
		   if(rem<5) {
			   purchaseAmount = purchaseAmount-rem; 
		   }else {
			   purchaseAmount =purchaseAmount+balance;
		   }
		return 100-purchaseAmount;
	   }
	   
	   public static int maxSum(int[] nums) {
		   int maxSum=-1;
		   for(int i=0;i<nums.length;i++) {
			   for(int j=i+1;j<nums.length;j++) {
				   if(findMax(nums[i])==findMax(nums[j])) {
					   maxSum=Math.max(maxSum, (nums[i]+nums[j]));
				   }
			   }
		   }
		return maxSum;
	   }

	private static int findMax(int num) {
		int max=0;
		while(num !=0) {
			if((num %10)>max) {
				max=num%10;
			}
			num =num/10;
		}
		return max;
	}
	public static boolean acronym(String[] words,String s) {
		StringBuilder builder = new StringBuilder();
		for(String word:words) {
			builder.append(word.charAt(0));
		}
		
		return builder.toString().equals(s);
	}
	
	public static int minOprations(int[] nums,int k) {
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i=1;i<=k;i++) {
			set.add(i);
		}
		int count=0;
		for(int i=0;i<nums.length;i++) {
			if(set.contains(nums[i])) {
				set.remove(nums[i]);
				if(set.isEmpty()) {
					break;
				}
			}
			count++;
		}
		return count;
	}
	
	public static int divNonDiv(int n,int m) {
		int num1=0,num2=0;
		for(int i=1;i<=n;i++) {
			if(i % m !=0) {
				num1 +=i;
			}else {
				num2 +=i;
			}
		}
		return num1-num2;
	}
	public static int highestAltitude(int[] gain) {
		int currentGain=0,maxGain=0;
		for(int i=0;i<gain.length;i++) {
			currentGain=currentGain+gain[i];
			maxGain = Math.max(maxGain, currentGain);
		}
		return maxGain;
	}
	
	public static int[] valueDiff(int[] nums,int indexDiff,int valueDiff) {
		for(int i=0;i<nums.length;i++) {
			for(int j=i+1;j<nums.length;j++) {
				if(Math.abs(i-j)>=indexDiff && Math.abs(nums[i]-nums[j])>=valueDiff) {
					return new int[] {i,j};
				}
			}
		}
		return new int[] {-1,-1};
	}
	
	public static boolean wordPattern(String pattern,String s) {
		String[] words = s.split(" ");
		if(pattern.length()!=words.length) {
			return false;
		}
		HashMap<Character, String> char_map= new  HashMap<Character, String>();
		HashMap<String, Character> word_map = new HashMap<String, Character>();
		
		for(int i=0;i<words.length;i++) {
			char ch=pattern.charAt(i);
			String word=words[i];
			if(!char_map.containsKey(ch)) {
				if(word_map.containsKey(word)) {
					return false;
				}else {
					char_map.put(ch, word);
					word_map.putIfAbsent(word, ch);
				}
			}else {
				if(!char_map.get(ch).equals(word)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean wordPattern1(String pattern, String s) {
	    String[] words = s.split(" ");
	    if (pattern.length() != words.length) return false;

	    Map<Character, String> charToWord = new HashMap<>();
	    Map<String, Character> wordToChar = new HashMap<>();

	    for (int i = 0; i < words.length; i++) {
	        char ch = pattern.charAt(i);
	        String word = words[i];

	        if (charToWord.containsKey(ch)) {
	            if (!charToWord.get(ch).equals(word)) return false;
	        } else {
	            if (wordToChar.containsKey(word)) return false;
	            charToWord.put(ch, word);
	            wordToChar.put(word, ch);
	        }
	    }
	    return true;
	}
	
	public static int[] leftSumRightSumDiff(int[] nums) {
		int sum=0;
		int[] leftSum= new int[nums.length];
		int[] rightSum=new int[nums.length];
		
		for(int i=0;i<nums.length;i++) {
			leftSum[i]=sum;
			sum +=nums[i];
		}
		sum=0;
		for(int i=nums.length-1;i>=0;i--) {
			rightSum[i]=sum;
			sum +=nums[i];
		}
		System.out.println(Arrays.toString(leftSum));
		System.out.println(Arrays.toString(rightSum));
		int[] ans=new int[nums.length];
		for(int i=0;i<nums.length;i++) {
			ans[i]=Math.abs(leftSum[i]-rightSum[i]);
		}
		return ans;
		
	}
	public static int[] leftSumRightSumDiff1(int[] nums) {
		int sum=0;
		for(int num:nums) {
			sum +=num;
		}
		int leftSum=0;
		int[] ans=new int[nums.length];
		for(int i=0;i<nums.length;i++) {
			int rightSum=sum-leftSum-nums[i];
			ans[i]=Math.abs(leftSum-rightSum);
			leftSum=leftSum+nums[i];
		}
		return ans;
	}
	public static List<ArrayList<Integer>> twoArrayDiff(int[] nums1,int[] nums2) {
		
		return Arrays.asList(findDiff(nums1,nums2),findDiff(nums2,nums1));
			
	}

	private static ArrayList<Integer> findDiff(int[] nums1, int[] nums2) {
		HashSet<Integer> set = new HashSet<Integer>();
		for(int num:nums2) {
			set.add(num);
		}
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for(int num:nums1) {
			if(!set.contains(num)) {
				ans.add(num);
			}
		}
		return ans;
	}
	
	public static int employeesMetTheTarget(int[] hours,int target) {
		int count=0;
		for(int hr:hours) {
			if(hr>=target) {
				count++;
			}
		}
		return count;
	}
	public static boolean contigous1S0S(String s) {
		int currentOne=0,currentZero=0;
		int maxOne=0,maxZero=0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='1') {
				currentOne++;
				currentZero=0;
			}else {
				currentOne=0;
				currentZero++;
			}
			maxOne=Math.max(maxOne, currentOne);
			maxZero = Math.max(maxZero, currentZero);
		}
		return maxOne>maxZero;
	}
	
	public static int contigousChar(String s) {
		int current=1,max=1;
		for(int i=1;i<s.length();i++) {
			if(s.charAt(i-1)==s.charAt(i)) {
				current++;
			}else {
				current=1;
			}
			max=Math.max(current, max);
		}
		return max;
	}
	
	public static int maxBallons(String text) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('b', 0);
        map.put('a', 0);
        map.put('l', 0);
        map.put('o', 0);
        map.put('n', 0);
        for(int i=0;i<text.length();i++) {
        	if(map.containsKey(text.charAt(i))) {
        		map.put(text.charAt(i), map.get(text.charAt(i))+1);
        	}
        }
        int min=map.get('b');
        min=Math.min(min, map.get('a'));
        min=Math.min(min, map.get('l')/2);
        min=Math.min(min, map.get('o')/2);
        min=Math.min(min, map.get('n'));
        return min;
	}
	public static void main(String[] args) {
		String s="alaebolkon";
		System.out.println(maxBallons(s));
	}
}
