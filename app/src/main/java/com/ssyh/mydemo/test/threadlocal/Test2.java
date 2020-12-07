package com.ssyh.mydemo.test.threadlocal;

import java.sql.Connection;

class Test2 {
     private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();


    public static void main(String[] args){

    }
}
