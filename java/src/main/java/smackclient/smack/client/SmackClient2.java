package smackclient.smack.client;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;

import smackclient.smack.core.Client;
import smackclient.smack.core.Smack;
import smackclient.smack.core.SmackImpl;

public class SmackClient2 {

	public static final String HOST = "192.168.1.130";
    public static final int PORT = 5222;
    
	public static void main(String[] args) {
		
		
		Client client = new Client();
		client.setHost(HOST);
		client.setPort(PORT);
		client.setUsername("user2#imei");
//		client.setUsername("user_token");
		client.setPassword("123");
		final Smack smack = new SmackImpl(client);
		
		new Thread() {
			public void run() {
				smackInit(smack);
				
				if (smack.isConnected()) {
					
//					Message msg = new Message();
//					msg.setBody("user2 say hello");
//					msg.setFrom("localhost");
//					msg.setTo("user#imei");
					smack.sendMessage("user", "user2 say hello", new ChatMessageListener() {
						
						@Override
						public void processMessage(Chat chat, Message message) {
							System.out.println("Received message�� " + message);
						}
					});
					
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
