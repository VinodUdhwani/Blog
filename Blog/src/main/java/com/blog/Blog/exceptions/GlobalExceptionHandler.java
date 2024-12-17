package com.blog.Blog.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public String ResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        String message=resourceNotFoundException.getMessage();
        return message;
    }
}
