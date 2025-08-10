package com.faizan.leetcode.revision;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeetCodeRevision4 {
	
	public static String goalParser(String s) {
		StringBuilder builder = new StringBuilder();
		int i=0;
		while(i<s.length()) {
			char ch=s.charAt(i);
			if(ch=='G') {
				builder.append("G");
				i++;
			}else if(ch=='(' && s.charAt(i+1)==')') {
				builder.append("o");
				i=i+2;
			}else {
				builder.append("al");
				i=i+4;
			}
		}
		return builder.toString();
	}
	
	public static String goalParser1(String s) { // needs some fixex
	    StringBuilder builder = new StringBuilder();
	    for (int i = 0; i < s.length() - 1; i++) {
	        if (s.charAt(i) == 'G') {
	            builder.append('G');
	        } else if (s.charAt(i) == '(' && s.charAt(i + 1) == ')') {
	            builder.append("o");
	        } else if (s.charAt(i) == '(' && s.charAt(i + 1) == 'a') {
	            builder.append("al");
	        }
	    }
	    if (s.charAt(s.length() - 1) == 'G') {
	        builder.append('G');
	    }
	    return builder.toString();
	}
	
	public static int uniqueSum(int[] nums) {
		Integer sum = Arrays.stream(nums).mapToObj(num->(Integer)num)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet()
		.stream()
		.filter(entry->entry.getValue()==1L)
		.map(entry->entry.getKey())
		.reduce(0,(a,b)->a+b);
		return sum;
	
		
	}
	
	public static String mergeString(String word1,String word2) {
		String result="";
		if(word1.length()>=word2.length()) {
			result=merger(word1, word2);
		}else if(word2.length()>word1.length()){
			result =merger(word2, word1);
		}
		
		return result;
		
	}
	private static String merger(String word1,String word2) {
		StringBuilder builder = new StringBuilder();
		int len1=word1.length();
		int len2=word2.length();
		for(int i=0;i<len2;i++) {
			builder.append(word1.charAt(i)).append(word2.charAt(i));
		}
		if(word1.length()!=word2.length()) {
		builder.append(word1.substring(len2));
		}
		return builder.toString();
	}
	
	public static int secondLargest(String s) {
		 int largest = -1;
		 int secondLargest = -1;
		for(int i=0;i<s.length();i++) {
			if(Character.isDigit(s.charAt(i))) {
				int digit = Character.getNumericValue(s.charAt(i));
				if(digit>largest) {
					secondLargest=largest;
					largest=digit;
				}else if(digit>secondLargest && digit<largest) {
					secondLargest= digit;
				}
			}
		}
		return secondLargest;
	}
	
	public static int secondLargest1(String s) {
		return s.chars().mapToObj(c->(char)c)
		.distinct()
		.filter(c->Character.isDigit(c))
		.sorted(Comparator.reverseOrder())
		.skip(1)
		.map(c -> Character.getNumericValue(c))
		.findFirst()
		.orElse(-1);
	}
	
	public static String truncateSentence(String sentence,int k) {
		String[] words=sentence.split(" ");
		StringBuilder builder = new StringBuilder();
		if(words.length<k) {
			return sentence;
		}
		for(int i=0;i<k;i++) {
			if(i!=k-1) {
			builder.append(words[i] +" ");
			}else {
				builder.append(words[i]);
			}
		}
		return builder.toString();
		
	}
	
	public static int productSign(int[] nums) {
		int neg=0;
		
		for(int i=0;i<nums.length;i++) {
			if(nums[i]<0) {
				neg++;
			}else if(nums[i]==0) {
				return 0;
			}
		}
		if(neg%2==0) {
			return 1;
		}else {
			return -1;
		}
	}
	
	public static int productSign1(int[] nums) {
	    int neg = 0;
	    for (int num : nums) {
	        if (num == 0) return 0;
	        if (num < 0) neg++;
	    }
	    return (neg % 2 == 0) ? 1 : -1;
	}
	
	public static boolean checkPangram(String sentence) {
		HashSet<Character> characters = new HashSet<Character>();
		for(int i=0;i<sentence.length();i++) {
			characters.add(sentence.charAt(i));
		}
		if(characters.size()==26) {
			return true;
		}
		return false;
	}
	
	public static String sortingSentence(String sentence) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		String[] words = sentence.split(" ");
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<words.length;i++) {
			map.put(Character.getNumericValue(words[i].charAt(words[i].length()-1)), words[i].substring(0,words[i].length()-1));
		}
		for(int i=1;i<=words.length;i++) {
			builder.append(map.get(i)).append(" ");
		}
		return builder.toString().trim();
	}
	
	public static String sortigSentence1(String sentence) {
		String[] words = sentence.split(" ");
		String[] result= new String[words.length];
		
		for(String word:words) {
			int pos=Character.getNumericValue(word.charAt(word.length()-1))-1;
			result[pos]=word.substring(0, word.length()-1);
			
			
		}
		
		return String.join(" ", result);
	}
	
	public static int[] arrayConcat(int[] array) {
		int[] result= new int[(array.length)*2];
		//int pointer=array.length;
		for(int i=0;i<array.length;i++) {
			result[i]=array[i];
			 result[i + array.length] = array[i]; 
			//result[pointer]=array[i];
			//pointer++;
		}
		return result;
	}
	
	public static boolean checkCharacterOccurenceEqual(String s) {
		Map<Character, Long> map = s.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		//HashSet<Long> set= new HashSet<Long>(map.values());
		//if(set.size()==1L) {
			//return true;
	//	}
		return  new HashSet<>(map.values()).size() == 1;
	}
	
	public static String kthDistictElement(String[] array ,int k) {
		 List<String> list = Arrays.stream(array).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()))
		 .entrySet()
		 .stream()
		 .filter(entry->entry.getValue()==1L)
		 .map(entry->entry.getKey())
		 .collect(Collectors.toList());
		 if(list.size()<k) {
			 return "";
		 }
		 return list.get(k-1);
	}
	
	public static int smallestIndex(int[] nums) {
		if (nums == null || nums.length == 0) return -1;
		int smallIndex=0;
		int minVal = nums[0] % 10;  // Initialize with first element’s last digit

		for(int i=0;i<nums.length;i++) {
			int r=nums[i]%10;
			if(r<minVal) {
				minVal=r;
				smallIndex=i;
			}
		}
		return smallIndex;
	}
	public static int smallestEqual(int[] nums) {
		int index=-1;
		for(int i=0;i<nums.length;i++) {
			if(i%10 == nums[i]) {
				index=i;
				break;
			}
		}
		return index;
	}
	

	

	public static void main(String[] args) {
		int[] nums = {24, 34, 40, 14};
		System.out.println(smallestEqual(nums));
		//String[] array = {"a", "b", "c", "a", "c", "d", "g", "h"};
		//System.out.println(kthDistictElement(array, 2));
		//String s="abc";
		//System.out.println(checkCharacterOccurenceEqual(s));
		//int[] array= {1,2,3};
		//System.out.println(Arrays.toString(arrayConcat(array)));
		//String s="is2 sentence4 This1 a3";
		//System.out.println(sortigSentence1(s));
		//String sentence="thequickbrownfoxjumpsoverthelazydog";
		//System.out.println(checkPangram(sentence));
		//int[] nums= {-1,-2,-3,-4,3,2,1};
		//System.out.println(productSign(nums));
		//String sentence="hello how are you leetcode";
		//System.out.println(truncateSentence(sentence, 4));
		//String s="abc1112";
		//System.out.println(secondLargest1(s));
		//String word1="abczx";
		//String word2="pqr";
		//System.out.println(mergeString(word1, word2));
		//int[] nums= {1,2,2,3};
		 //System.out.println(uniqueSum(nums)); 
		//String s="G()(al)";
		//String str="(al)G(al)()()G";
		//System.out.println(goalParser1(str));
	}

}
