package com.faizan.design.pattrens.decorator.prac2;



public class ChochoSyrupDecorator implements Icream{
	
	   private Icream icecream;  
	   
	   public ChochoSyrupDecorator(Icream icecream) {
		   this.icecream=icecream;
	   }
		

		@Override
		public int getPrice() {
			// TODO Auto-generated method stub
			return icecream.getPrice()+7;
		}

		@Override
		public String getDiscription() {
			// TODO Auto-generated method stub
			return icecream.getDiscription()+" Chcho Cyrup Decorator";
		}

}
