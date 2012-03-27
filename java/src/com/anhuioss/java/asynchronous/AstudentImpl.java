package com.anhuioss.java.asynchronous;

/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 12-3-27
 * Time: 下午3:17
 * To change this template use File | Settings | File Templates.
 */
public class AstudentImpl extends Astudent {
    
    public static void main(String[] args) {

     new Thread(new Astudent()) .start();
             
    }
}
