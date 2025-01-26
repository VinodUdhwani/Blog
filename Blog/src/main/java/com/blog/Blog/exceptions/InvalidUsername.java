package com.blog.Blog.exceptions;

public class InvalidUsername extends RuntimeException{
    private String message;

    public InvalidUsername(String message) {
        super(message);
        this.message = message;
    }
}
