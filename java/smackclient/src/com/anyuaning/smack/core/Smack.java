package com.anyuaning.smack.core;

import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;

public interface Smack {

    public boolean connect();
    
    public boolean isConnected();
    
    public boolean disconnect();

    public boolean register();

    public boolean login();
    
    public boolean isAuthenticated();
    
    public String sendMessage(String toUser, String msg, ChatMessageListener listener);
    
    public String reciveMessage();
}
