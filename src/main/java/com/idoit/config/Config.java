package com.idoit.config;

/**
 * Configuration class for storing application credentials and URLs.
 */
public class Config {
    // Application URL
    public static final String URL = "https://demo.i-doit.com/";
    
    // Login credentials
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "admin";
    
    // Timeouts (in seconds)
    public static final int DEFAULT_TIMEOUT = 10;
    public static final int SHORT_TIMEOUT = 5;
    public static final int LONG_TIMEOUT = 20;
} 