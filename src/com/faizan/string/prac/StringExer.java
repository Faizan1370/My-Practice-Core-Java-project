package com.faizan.string.prac;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringExer {
	
	public static void countCharWithCount() {
		String str ="Hello Bro";
		str.chars().mapToObj(s->(char)s)
		//.filter(s->!Character.isWhitespace(s)) //if white space not required
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet()
		.stream()
		.forEach(entry->System.out.println(entry));
	}
	
	public static void sortStringArray() {
		String[] array= {"Zimbabwe", "South-Africa", "India", "America", "Yugoslavia", " Australia", "Denmark", "France", "Netherlands", "Italy", "Germany"};
		Collections.sort(Arrays.asList(array));
		System.out.println(Arrays.toString(array));
	}
	public static void sortStringArray1() {
		String[] array= {"Zimbabwe", "South-Africa", "India", "America", "Yugoslavia", " Australia", "Denmark", "France", "Netherlands", "Italy", "Germany"};
		
		for(int i=0;i<array.length-1;i++) {
			for(int j=i+1;j<array.length;j++) {
				if(array[i].compareTo(array[j])>0) {
					String temp=array[i];
					array[i] = array[j];
					array[j]=temp;
				}
			}
		}
		System.out.println(Arrays.toString(array));
		
	}
	
	public static void countDigitCharSpace() {
		int countcha=0;
		int coountDigit=0;
		int countSpace=0;
		int other=0;
		
	     String str="faizan md 143@gakf";
	     for(int i=0;i<str.length();i++) {
	    	 if(Character.isDigit(str.charAt(i))) {
	    		 coountDigit++;
	    	 }else if(Character.isWhitespace(str.charAt(i))){
	    		 countSpace++;
	    	 }else {
	    		 other++;
	    	 }
	     }
	     System.out.println(coountDigit +" "+countSpace +" "+other);
	     
	   
	     
	}
	
	public static void checkAnagramString()
	{
		String str="faizan";
		char[] charArray = str.toCharArray();
		String str1="naziaf";
		char[] charArray1 = str1.toCharArray();
		boolean isAnagram=false;
		Arrays.sort(charArray);
		Arrays.sort(charArray1);
		
		System.out.println(Arrays.toString(charArray));
		System.out.println(Arrays.toString(charArray1));
		if(str.length()!=str.length()) {
			isAnagram=false;
		}
		
		for(int i=0;i<charArray.length;i++) {
			if(charArray[i]==charArray1[i]) {
				isAnagram=true;
			}else {
				isAnagram=false;
				break;
			}
		}
		System.out.println(isAnagram);
		 //secound approach
		for(int j=0;j<charArray.length;j++) {
			if(str.indexOf(charArray1[j])==-1) {
				isAnagram=true;
			  break;
			}else {
				isAnagram=true;
			}
		}
		System.out.println(isAnagram);
		
	}
	public static void checkLogestPrefixInStringAarray() {
		String[] array = {"faizan","farhan","dgg"};
		String prefix=array[0];
		
		for(int i=1;i<array.length;i++) {
			while(!array[i].startsWith(prefix)) {
				prefix = prefix.substring(0,prefix.length()-1);
				 if (prefix.isEmpty()) {
		                System.out.println(""); // No common prefix
		                return;
		            }
			}
		}
		System.out.println(prefix);
	}
	
	public static void checkStringPalin() {
		   String str = "radar";
		    int start = 0;
		    int end = str.length() - 1;
		    
		    while (end > start) {
		        if (str.charAt(start) != str.charAt(end)) {
		            System.out.println("Not a palindrome");
		            return; // exit immediately
		        }
		        start++;
		        end--;
		    }
		    
		    System.out.println("Palindrome");
	}
	
	public static void checkFirstNonRepeatingChar() {
		String str="faizan";
		Character character = str.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
		.entrySet()
		.stream()
		.filter(entry->entry.getValue()==1l)
		.map(entry->entry.getKey())
		.findFirst()
		.get();
		System.out.println(character);
	}
	public static void checkFirstNonRepeatingChar1() {
		String str="faizan";
		 Character character = str.chars().mapToObj(c->(char)c)	
		.filter(c->str.indexOf(c)==str.lastIndexOf(c))
		.findFirst()
		.get();
		 System.out.println(character);
	}
	
	public static void checkFirstRepeatingChar() {
		String str="Faizan";
		 Map<String, Long> map = Arrays.stream(str.split(""))  //correct way
		            .collect(Collectors.groupingBy(
		                s -> s,                     // group by the string character
		                () -> new LinkedHashMap<>(), // use LinkedHashMap
		                Collectors.counting()
		            ));
		Character character = str.chars().mapToObj(c->(char)c)
		//.collect(Collectors.groupingBy(Function.identity(),()->new LinkedHashMap<>(),Collectors.counting())) // also correct
		.collect(Collectors.groupingBy(Function.identity(),()->new LinkedHashMap<>(),Collectors.counting()))
		.entrySet()
		.stream()
		.filter(entry->entry.getValue()>1L)
		.map(entry->entry.getKey())
		.findFirst()
		.get();
		System.out.println(character);
	}
	
	public static void checkFirstRepeatingChar1() {
		String str="Faizan";
		String string = Arrays.stream(str.split(""))
		.filter(c->str.indexOf(c)!=str.lastIndexOf(c))
		.findFirst()
		.get();
		System.out.println(string);
	}
	public static void stringCompreses() {
		String str = "aaacccdddtttt";
		StringBuilder builder = new StringBuilder();
		int count=1;
		for(int i=0;i<str.length()-1;i++) {
			if(str.charAt(i)==str.charAt(i+1)) {
				count++;
			}else {
				builder.append(str.charAt(i)).append(count);
				count=1;
			}
		}
		builder.append(str.charAt(str.length() - 1)).append(count);
		System.out.println(builder.toString());
	}
	
	public static void main(String[] args) {
		//countCharWithCount();
		//sortStringArray();
		//sortStringArray1();
		//countDigitCharSpace();
		//checkAnagramString();
		checkLogestPrefixInStringAarray();
		//checkStringPalin();
		//checkFirstNonRepeatingChar1();
		//checkFirstRepeatingChar();
		//checkFirstRepeatingChar1();
		//stringCompreses();
	}

}
