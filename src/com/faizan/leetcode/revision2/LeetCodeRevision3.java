package com.faizan.leetcode.revision2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class LeetCodeRevision3 {
	
	public static boolean circulerSentence(String sentence) {
		String[] words = sentence.split(" ");
		if(words.length==1) {
			return sentence.charAt(0)==sentence.charAt(sentence.length()-1);
		}
		for(int i=1;i<sentence.length();i++) {
			if(!Character.isLetter(sentence.charAt(i))) {
				if(sentence.charAt(i-1)!=sentence.charAt(i+1)) {
					return false;
				}
			}
		}
		return sentence.charAt(0)== sentence.charAt(sentence.length()-1);
		
	}
	
	
	public static int maxValue(String[] strs) {
		int value=0,maxValue=0;
		for(String word : strs) {
			if(checkWord(word)) {
				value=Integer.parseInt(word);
			}else {
				value=word.length();
			}
			maxValue=Math.max(maxValue,value);
		}
		return maxValue;
		
	}
	
	public static boolean checkWord(String word) {
		int count=0;
		for(int i=0;i<word.length();i++) {
			if(Character.isDigit(word.charAt(i))) {
				count++;
			}
		}
		return count==word.length();
	}
	
	public static int countPairs(String[] words) {
		int count=0;
		for(int i=1;i<words.length;i++) {
			if(checkPair(words[i],words[i-1])) {
				count++;
			}
		}
		return count;
	}
	private static boolean checkPair(String word1,String word2) {
		HashSet<Character> set=new HashSet<Character>();
		HashSet<Character> set1=new HashSet<Character>();
		for(int i=0;i<word1.length();i++){
			set.add(word1.charAt(i));
		}
		
		for(int i=0;i<word2.length();i++){
			set1.add(word2.charAt(i));
		}
		
		return set.equals(set1);
	}
	
	public static int maxCount(int[] nums) {
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
	public static int minimumCommonValue(int[] nums1,int[] nums2) {
		HashSet<Integer> hashSet = new HashSet<Integer>();
		for(int num:nums1) {
			hashSet.add(num);
		}
		for(int num:nums2) {
			if(hashSet.contains(num)) {
				return num;
			}
		}
		return 0;
	}
	
	public static int alternateDigitSum(int num) {
		String str=num+"";
		int sum=0;
		int j=1;
		for(int i=0;i<str.length();i++) {
			if(j%2!=0) {
				sum=sum+Character.getNumericValue(str.charAt(i));
			}else {
				sum=sum-Character.getNumericValue(str.charAt(i));
			}
			j++;
		}
		return sum;
	}
	
	public static int alternateDigitSum1(int num) {
		String str=num+"";
		int sum=Character.getNumericValue(str.charAt(0));;
		for(int i=1;i<str.length();i++) {
			if(i%2!=0) {
				sum=sum-Character.getNumericValue(str.charAt(i));
			}else {
				sum=sum+Character.getNumericValue(str.charAt(i));
			}
			
		}
		return sum;
	}
	
	public static int concateValue(int[] nums) {
		int sum=0;
	    int start=0;
	    int end=nums.length-1;
	    while(start<end) {
	    sum += Integer.parseInt(String.valueOf(nums[start]) +String.valueOf(nums[end]));
	      start++;
	      end--;
	    }
	    if(nums.length%2!=0) {
	    	sum+=nums[start];
	    }
		return sum;
	}
	
	public static int countVowelWord(String[] words) {
		int count=0;
		for(String word:words) {
			if((word.charAt(0)=='a' ||word.charAt(0)=='e' || word.charAt(0)=='i' || word.charAt(0)=='o' || word.charAt(0)=='u')
					 && (word.charAt(word.length()-1)=='a' ||word.charAt(word.length()-1)=='e' || word.charAt(word.length()-1)=='i' || word.charAt(word.length()-1)=='o' || word.charAt(word.length()-1)=='u')) {
				count++;
			}
		}
		return count;
	}
	
	public static int delayedArival(int arrivalTime,int delyaedTime) {
		int totalArivbval=arrivalTime+delyaedTime;
		if(totalArivbval>=24) {
			totalArivbval-=24;
		}
		return totalArivbval;
	}
	
	public static int sumOfMultiples(int n) {
		int sum=0;
		for(int i=1;i<=n;i++) {
			if(i%3==0 || i%5==0 || i%7==0) {
				sum+=i;
			}
		}
		return sum;
	}
	
	public static int isWinner(int[] player1,int[] player2) {
		int player1Sum=countScore(player1);
		int player2Sum=countScore(player2);
		if(player1Sum>player2Sum) {
			return 1;
		}else if(player2Sum>player1Sum) {
			return 2;
		}else {
			return 0;
		}
	}
	
	private static int countScore(int[] player) {
		int score=0;
		for(int i=0;i<player.length;i++) {
			if(i==1) {
				if(player[i-1]==10) {
					score+=2*player[i];
				}else {
					score+=player[i];
				}
			} 
			else if(i>1) {
				if(player[i-1]==10 || player[i]==10) {
					score+=2*player[i];
				}else {
					score+=player[i];	
				}
			}
			
		}
		return score;
	}
	
	public static int[] distintDiffrence(int[] array) { // not correct ALways
		int n=array.length-1;
		int[] result= new int[array.length];
		for(int i=0;i<array.length;i++) {
			result[i]=array[i]-(n-i);
		}
		return result;
	}
	
	public static int[] distintDiffrence1(int[] array) {
		int[] result= new int[array.length];
		
		for(int i=0;i<array.length;i++) {
			HashSet<Integer> set = new HashSet<Integer>();
			HashSet<Integer> set1 = new HashSet<Integer>();
			
			for(int j=0;j<=i;j++) {
				set.add(array[j]);
			}
			
			for(int j=i+1;j<array.length;j++) {
				set1.add(array[j]);
			}
			
			result[i]=(set.size()-set1.size());
		}
		return result;
	}
	
	public static int totalDistanceTravled(int mainTank,int additionalTank) {
		int total=0;
	    while(mainTank>=5 && additionalTank>0) {
	    	mainTank=mainTank-5+1;
	    	total+=50;
	    	additionalTank--;
	    }
	    total+=mainTank*10;
	    return total;
	    
	}
	public static boolean isGood(int[] nums) {
		Arrays.sort(nums);
		int max=nums[nums.length-1];
		if(nums.length!=max+1) {
			return false;
		}
		Map<Integer, Long> map = Arrays.stream(nums).mapToObj(num->(Integer)num)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		
		ArrayList<Long> list = new ArrayList<Long>(map.values());
		int count=0;
		for(long num:list) {
			if(num>=2) {
				count++;
			}
		}
		if(map.get(max)==2 && count==1) {
			return true;
		}
		return false;
				
		
	}
	
	public static List<String> srtringSprator(List<String> words,String separtor){
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<words.size();i++) {
			//String[] strs  = words.get(i).split("["+separtor+"]");
			String[] strs  = words.get(i).split(Pattern.quote(separtor));
			for(String str:strs) {
				list.add(str);
			}
		}
		return list;
	}
	
	public static int purchseAmount(int purchaseAmount) {
		int r=purchaseAmount%10;
		int balance=10-r;
		
		if(r>5) {
			purchaseAmount=purchaseAmount+balance;
		}else {
			purchaseAmount=purchaseAmount-r;
		}
		return 100-purchaseAmount;
	}
	
	public static int maxPairSurm(int[] nums) {
		int sum=0;
		int maxSum=0;
		for(int i=1;i<nums.length;i++) {
			if(maxDigit(nums[i-1])==maxDigit(nums[i])) {
				sum=nums[i-1]+nums[i];
				maxSum = Math.max(maxSum, sum);
			}
		}
		return maxSum;
	}
	
    private static int maxDigit(int num) {
    	int maxDigit=0;
    	while(num>0) {
    		int r =num%10;
    		if(r>maxDigit) {
    			maxDigit=r;
    		}
    		num=num/10;
    	}
		return maxDigit;
    }
    
    public static boolean acronym(String[] words,String s) {
    	StringBuilder builder = new StringBuilder();
    	for(String word:words) {
    		builder.append(word.charAt(0));
    	}
    	return s.equals(builder.toString());
    }
    
    public static int minimumOperation(int[] nums,int k) {
    	HashSet<Integer> set= new HashSet<Integer>();
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
    
    public static int divisilbeNoDivisilbeSum(int n,int m) {
    	int num1=0;
    	int num2=0;
    	for(int i=1;i<=n;i++) {
    		if(i%m!=0) {
    			num1+=i;
    		}else {
    			num2+=i;
    		}
    	}
    	return num1-num2;
    }
    
    public static int heighestAltitude(int[] gain) {
    	int currentAltitude=0,maxAltitude=0;
    	for(int i=0;i<gain.length;i++) {
    		currentAltitude=currentAltitude+gain[i];
    		maxAltitude=Math.max(maxAltitude, currentAltitude);
    	}
		return maxAltitude;
    }
    
    public  static int[] findIndices(int[] nums,int valueDif,int indexDiff) {
    	for(int i=0;i<nums.length;i++) {
    		for(int j=i+1;j<nums.length;j++) {
    			if(Math.abs(i-j)>=indexDiff && Math.abs(nums[i]-nums[j])>=valueDif) {
    				return new int[] {i,j};
    			}
    		}
    	}
		return new int[] {-1,-1};
    }
    public static String toLower(String str) {
		return str.toLowerCase();
	}
    
    public static boolean wordPattern(String pattren,String s) {
    	String[] words = s.split(" ");
    	if(words.length!=pattren.length()) {
    		return false;
    	}
    	HashMap<Character, String> map = new HashMap<Character, String>();
    	HashMap<String, Character> strMap = new HashMap<String, Character>();
    	for(int i=0;i<words.length;i++) {
    		char ch=pattren.charAt(i);
    		String word=words[i];
    		if(!map.containsKey(ch)) {
    			if(strMap.containsKey(word)) {
    				return false;
    			}else {
    				map.put(ch, word);
    				strMap.put(word, ch);
    			}
    		}else {
    			if(!map.get(ch).equals(word)) {
    				return false;
    			}
    		}
    	}
		return true;
    }
    
    public static int[] leftSumRightSumDiff(int[] nums) {
    	int[] ans= new int[nums.length];
    	int sum=0;
    	int[] leftSum=new int[nums.length];
    	int[] rightSUm=new int[nums.length];
    	leftSum[0]=0;
    	rightSUm[nums.length-1]=0;
    	for(int i=0;i<nums.length-1;i++) {
    		sum+=nums[i];
    		leftSum[i+1]= sum;
    	}
    	sum=0;
    	for(int i=nums.length-1;i>=1;i--) {
    		sum+=nums[i];
    		rightSUm[i-1]=sum;
    	}
    	for(int i=0;i<leftSum.length;i++) {
    		ans[i]=Math.abs(leftSum[i]-rightSUm[i]);
    	}
		return ans;
    }
    
    public static int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        int leftSum = 0;
        for (int i = 0; i < n; i++) {
            int rightSum = totalSum - leftSum - nums[i];
            ans[i] = Math.abs(leftSum - rightSum);
            leftSum += nums[i];
        }
        return ans;
    }
    
    public static  List<List<Integer>>  arrayDiff(int[] nums1,int[] nums2) {
    	return Arrays.asList(arrayDiff1(nums1, nums2),arrayDiff1(nums2, nums1));
    	
    }
    private static ArrayList<Integer> arrayDiff1(int[] nums1,int[] nums2){
    	
    	HashSet<Integer> set = new HashSet<Integer>();
    	HashSet<Integer> set2 = new HashSet<Integer>();
    	for(int num:nums2) {
    		set2.add(num);
    	}
    	for(int num:nums1) {
    		if(!set2.contains(num)) {
    			set.add(num);
    		}
    	}
    	return new ArrayList<Integer>(set);
    }
    
    public static int noOfEmpTarget(int[] hours,int target) {
    	int count=0;
    	for(int hour:hours) {
    		if(hour>=target) {
    			count++;
    		}
    	}
		return count;
    }
    
    public static boolean longstContigus(String s) {
    	int oneCount=0,zeroCount=0;
    	int maxOnce=0,maxZero=0;
    	for(int i=0;i<s.length();i++) {
    		if(s.charAt(i)=='1') {
    			zeroCount=0;
    			oneCount++;
    				maxOnce=Math.max(maxOnce, oneCount);
    		}else {
    			oneCount=0;
    			zeroCount++;
    			maxZero=Math.max(maxZero, zeroCount);
    		}
    	}
		return maxOnce>maxZero;
    }
    
    public static int maxPower(String s) {
    	if(s==null || s.length()==0) {
    		return 0;
    	}
    	int currentStreak=1,maxStreek=1;
    	for(int i=1;i<s.length();i++) {
    		if(s.charAt(i)==s.charAt(i-1)) {
    			currentStreak++;
    		}else {
    			currentStreak=1;
    		}
    		maxStreek=Math.max(maxStreek, currentStreak);
    	}
		return maxStreek;
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
        min=Math.min(min, map.get('l')/2);
        min=Math.min(min, map.get('o')/2);
        min=Math.min(min, map.get('n'));
        
        return min;
    }

	public static void main(String[] args) {
		String s="alacbolno";
		System.out.println(maxBallons(s));
	}
	
	
}
