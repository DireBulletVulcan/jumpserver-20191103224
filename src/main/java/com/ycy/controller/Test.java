package com.ycy.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Test {
    
    public static final String DEFAULT_PASSWORD = "123456";
    
    public static void main(String[] args) {
        String defaultHashedPassword = getDefaultHashedPassword();
        System.out.println("defaultHashedPassword::::" + defaultHashedPassword);
    }
    
    public static String getDefaultHashedPassword() {
        return BCrypt.withDefaults().hashToString(12, DEFAULT_PASSWORD.toCharArray());
    }
}