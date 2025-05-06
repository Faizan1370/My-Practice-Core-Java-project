package com.faizan.design.pattrens.singleton;

public class Singleton {
	
   private static Singleton singleton;
   
   private static Singleton singleton2 = new Singleton();
   
   private static volatile Singleton singleton3;
   
   private Singleton() {
       // private constructor to prevent instantiation
   }
   
	
	public static Singleton getInstanceObj() {
		if(singleton == null) {
			singleton = new Singleton(); // ✅ Assign it once
		}
		return singleton;
	}
	
	  
    // Another thread-safe method using double-checked locking for better performance
    public static Singleton getInstanceForThreadSafe() {
        if (singleton3 == null) {
            synchronized (Singleton.class) {
                if (singleton3 == null) {
                	singleton3 = new Singleton();
                }
            }
        }
        return singleton3;
    }
	
	public static Singleton getInstanceAnotherThreeadSafe() {
		
		return singleton2;
	}

}
