package com.experis.JDBCproject.log;

import org.springframework.stereotype.Service;

@Service
public class logToConsole {
    public static void log(String msg){
        System.out.println(msg);
    }
}
