package com.lounwb.bank.exceptions;

/**
 * App异常
 * @author Lounwb
 * @version 1.0
 * @since 1.0
 */

public class AppException extends Exception{
    public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }
} 
