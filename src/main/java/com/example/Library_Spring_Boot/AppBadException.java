package com.example.Library_Spring_Boot;

public class AppBadException extends RuntimeException{

    public AppBadException(String message) {
        super(message);
    }
}
