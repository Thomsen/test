package com.anyuaning.smack.core;

public interface Smack {

    public boolean connect();
    
    public boolean isConnected();
    
    public boolean disconnect();

    public boolean register();

    public boolean login();
    
    public boolean isAuthenticated();
}
