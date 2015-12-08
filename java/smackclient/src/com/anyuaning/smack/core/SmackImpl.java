package com.anyuaning.smack.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaIdFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.packet.Registration;

import com.anyuaning.smack.listener.NotifiStanzaListener;
import com.anyuaning.smack.listener.RegiStanzaListener;

public class SmackImpl implements Smack {

    private static XMPPTCPConnection connection;
    
    private Client client;
    
    public SmackImpl(Client client) {
    	this.client = client;
    }

    @Override
    public boolean connect() {
        if (null == connection) {
        	XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
        	
        	configBuilder.setHost(client.getHost());
        	configBuilder.setServiceName(client.getHost());
        	configBuilder.setPort(client.getPort());
        	
//        	configBuilder.setSecurityMode(SecurityMode.required);
        	configBuilder.setCompressionEnabled(false);
        	
            connection = new XMPPTCPConnection(configBuilder.build());
            
            // dependence smack-java7, org.jivesoftware.smack.util.stringencoder.Base64.setEncoder
        }
        
        try {
			connection.connect();
			
			return true;
		} catch (SmackException | IOException | XMPPException e) {
			e.printStackTrace();
		}
        
        return false;
    }

    @Override
    public boolean register() {
    	
    	Map<String, String> atts = new HashMap<String, String>();
    	atts.put("username", client.getUsername());
    	atts.put("password", client.getPassword());
    	Registration regter = new Registration(atts);  // smack-extensions
    	regter.setType(IQ.Type.set);
    	
		StanzaFilter packetFilter = new AndFilter(new StanzaIdFilter(regter.getStanzaId()),
				new StanzaTypeFilter(IQ.class));
		connection.addAsyncStanzaListener(new RegiStanzaListener(), packetFilter);
    	
    	try {
			connection.sendStanza(regter);
		} catch (NotConnectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return false;
    }

    @Override
    public boolean login() {
    	
    	try {
			connection.login(client.getUsername(), client.getPassword());
			
			StanzaFilter msgFilter = new StanzaTypeFilter(Message.class);
			connection.addAsyncStanzaListener(new NotifiStanzaListener(), msgFilter);
			
		} catch (XMPPException e) {
			e.printStackTrace();
		} catch (SmackException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
        return false;
    }

	@Override
	public boolean isConnected() {
		return (connection == null) ? false : connection.isConnected();
	}

	@Override
	public boolean isAuthenticated() {
		return (connection == null) ? false : connection.isAuthenticated();
	}

	@Override
	public boolean disconnect() {
		if (null != connection && connection.isConnected()) {
			connection.disconnect();
			return true;
		}
		return false;
	}

	@Override
	public String sendMessage(String toUser, String msg, ChatMessageListener listener) {
		String userJID = toUser + "@" + connection.getServiceName();
		Chat chat = ChatManager.getInstanceFor(connection).createChat(userJID, listener);
		try {
			chat.sendMessage(msg);
		} catch (NotConnectedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String reciveMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
