package com.anyuaning.smack.client;

import com.anyuaning.smack.core.Client;
import com.anyuaning.smack.core.Smack;
import com.anyuaning.smack.core.SmackImpl;
import com.anyuaning.smack.learn.Connection;

public class SmackClient {

	public static final String HOST = "192.168.1.130";
    public static final int PORT = 5222;
    
	public static void main(String[] args) {
		
		
		Client client = new Client();
		client.setHost(HOST);
		client.setPort(PORT);
//		client.setUsername("user#imei");
		client.setUsername("user_token");
		client.setPassword("123");
		final Smack smack = new SmackImpl(client);
		
		new Thread() {
			public void run() {
				smackInit(smack);
				if (smack.isConnected()) {
					for ( ; true ; ) {
						
					}
				}
			}
		}.start();
		

	}
	
	private static void smackInit(final Smack smack) {
		
		System.out.println("client connect ...");
		smack.connect();
		System.out.println("client connected");
		
		System.out.println("client register  ...");
		smack.register();
		System.out.println("client registered");
		
		System.out.println("client login ...");
		smack.login();
		System.out.println("client logined");
	}
}
