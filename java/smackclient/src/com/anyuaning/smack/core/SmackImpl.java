package com.anyuaning.smack.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import net.iharder.Base64;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaIdFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.stringencoder.Base64.Encoder;
import org.jivesoftware.smackx.iqregister.packet.Registration;

import com.anyuaning.smack.iq.User;

public class SmackImpl implements Smack {

    private static XMPPTCPConnection connection;
    
    private static Client client;
    
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
            
            // dependence smack-java7
//            org.jivesoftware.smack.util.stringencoder.Base64.setEncoder(new Encoder() {
//    			
//    			@Override
//    			public String encodeToString(byte[] input, int offset, int len) {
//    				return Base64.encodeBytes(input, offset, len);
//    			}
//    			
//    			@Override
//    			public byte[] encode(byte[] input, int offset, int len) {
//    				String string = encodeToString(input, offset, len);
//                    try {
//                        return string.getBytes(StringUtils.USASCII);
//                    } catch (UnsupportedEncodingException e) {
//                        throw new AssertionError(e);
//                    }
//    			}
//    			
//    			@Override
//    			public byte[] decode(byte[] input, int offset, int len) {
//    				// TODO Auto-generated method stub
//    				return null;
//    			}
//    			
//    			@Override
//    			public byte[] decode(String string) {
//    				// TODO Auto-generated method stub
//    				return null;
//    			}
//    		});
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
    	
//    	StanzaListener packetListener = new StanzaListener() {
//			
//			@Override
//			public void processPacket(Stanza packet) throws NotConnectedException {
//				if (packet instanceof IQ) {
//					
//				} else if (packet instanceof Message) {
//					
//				}
//			}
//		};
//		StanzaFilter packetFilter = new AndFilter(new StanzaIdFilter(regter.getStanzaId()),
//				new StanzaTypeFilter(IQ.class));
//		connection.addAsyncStanzaListener(packetListener, packetFilter);
    	
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
			
//			StanzaFilter pf = new StanzaTypeFilter(UserIQ.class);
//			connection.addAsyncStanzaListener(null, pf);
			
		} catch (XMPPException e) {
			e.printStackTrace();
		} catch (SmackException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
        return false;
    }

}
