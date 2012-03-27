package com.anhuioss.java.asynchronous;

/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 12-3-27
 * Time: 下午3:16
 * To change this template use File | Settings | File Templates.
 */
public class Cstudent extends Student {
    
    public boolean knownMessage(String msg) {
        
        System.out.println(getCurrentTime() + "B known: " + msg);
        
        return true;   
    }


}
