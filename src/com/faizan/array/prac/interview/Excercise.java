package com.faizan.array.prac.interview;

import java.util.Arrays;

public class Excercise {
	static String computerMove;
	
	public static void removeSpaceLeadTrail() {
		String str=" abc xy\t";
		System.out.println(str.trim());
		System.out.println(str.strip());
	}
	
	public static void fact() {
		int num=5;
		int fact=1;
		for(int i=1;i<=num;i++) {
			fact = fact*i;
		}
		
		System.out.println(fact);
	}
	public static void textBlock() {
		String textblock ="""
				Hello
				bro
				how 
				are you
				""";
		System.out.println(textblock);
	}
	public static void multilableSwitch() {
		int choice =76;
		int x=switch (choice) {
		case 1,2,3: {
			
			yield choice;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choice);
		};
		
		System.out.println(x);
				
	}
	

	public static void output() {
		String s3 = "journalDev";
		int start = 1;
		char end = 5;

		System.out.println(s3.substring(start, end));
	}
	
	public static void incremenAddDecrement() {
		int i=4;
		i= i-- - --i;
		System.out.println(i);
		
		 i = i++ + ++i;
	     System.out.println("normal 1 :"+i);
	     
	     i = --i - i--;
	        System.out.println("static2 :"+i);
	        
	        i = ++i + i++ +i;
	        System.out.println("normal2 :"+i);
	}
	
	public static void chekcString() {
		String str="java";
		String str1="java";
		System.out.println("check value ref +"+str==str1);
		System.out.println("check value ref :"+(str==str1));
	}
	
	public static void isPrime() {
	    int input = 23;

	    if (input == 0 || input == 1) {
	        System.out.println("Not a valid value");
	        return;
	    }

	    if (input == 2) {
	        System.out.println("Yes, prime");
	        return;
	    }

	    boolean isPrime = true;
	    System.out.println(input/2);
	    for (int i = 2; i <= input / 2; i++) {
	        if (input % i == 0) {
	            isPrime = false;
	            break;
	        }
	    }

	    if (isPrime) {
	        System.out.println("Yes, prime");
	    } else {
	        System.out.println("Not prime");
	    }
	}
	
	
	public static void checkVowel() {
		String str="fly";
		if(str.toLowerCase().matches(".*[aeiou].*")) {
			System.out.println("found vovewl");
		}else {
			System.out.println("not found");
		}
		
	}
	
	public static void test() {
		   String s6 = "hello";
	         String s7 = new String("hello");

	         s7 = s7.intern();
	         System.out.println(s6 == s7);
	}
	public static void test1() {
		String str="Java|Python|C++";
		String[] split = str.split("\\|");
		System.out.println(Arrays.toString(split));
	}
	
	public static void test2() {
		char[] ca ={0x4e, '\u004e', 78};  //without single qoute compile time error
		System.out.println((ca[0] == ca[1]) + " "+ (ca[0] == ca[2]));
	}
	
	public static void test3() {
		  int $ = 0;
		String a_b = null;  
		System.out.println($);  
		System.out.println(a_b);
	}
	
	public static void test4() {
		switch ( 2 )   
		{  
		case 0:  
		computerMove = "Rock";  
		break;  
		case 1:  
		computerMove = "Scissors";  
		break;  
		case 2:  
		computerMove = "Paper";  
		break;  
		}  
		System.out.println("Computer's move is " + computerMove);
	}

	
	
	public static void main(String[] args) {
		test4();
		//removeSpaceLeadTrail();
		//fact();
		//textBlock();
		//multilableSwitch();
		//output();
		//incremenAddDecrement();
		//chekcString();
		//isPrime();
		//checkVowel();
	}

}
