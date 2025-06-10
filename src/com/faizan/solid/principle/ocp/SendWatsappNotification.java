package com.faizan.solid.principle.ocp;

public class SendWatsappNotification implements NotificationService{

	@Override
	public void sendNotification(String message) {
	  System.out.println("Send via watsapp");
	}

}
