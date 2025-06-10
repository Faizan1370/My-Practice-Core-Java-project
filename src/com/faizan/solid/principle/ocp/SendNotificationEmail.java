package com.faizan.solid.principle.ocp;

public class SendNotificationEmail implements NotificationService{

	@Override
	public void sendNotification(String message) {
		System.out.println("send via email");
		
	}

}
