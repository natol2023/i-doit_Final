package com.idoit.utils;

import java.security.SecureRandom;

/**
 * Utility class for generating random values.
 */
public class RandomUtils {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();
    
    /**
     * Generates a random alphanumeric string of the specified length.
     * 
     * @param length The length of the string to generate
     * @return A random alphanumeric string
     */
    public static String generateRandomString(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(index));
        }
        return builder.toString();
    }
    
    /**
     * Generates a random alphanumeric string with a default length of 10 characters.
     * 
     * @return A random alphanumeric string
     */
    public static String generateRandomString() {
        return generateRandomString(10);
    }
} 