package com.anyuaning.smack.client;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;

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
//		client.setUsername("user_token");
		client.setUsername("7bd4cdd6-3f0b-407d-8d4b-16c4cb86ddcf#imei");
		client.setPassword("123");
		final Smack smack = new SmackImpl(client);
		
		new Thread() {
			public void run() {
				smackInit(smack);
				if (smack.isConnected()) {

//					smack.sendMessage("user2#imei", "user1 say hello", new ChatMessageListener() {
//						
//						@Override
//						public void processMessage(Chat chat, Message message) {
//							System.out. println("Received message�� " + message);
//						}
//					});

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
