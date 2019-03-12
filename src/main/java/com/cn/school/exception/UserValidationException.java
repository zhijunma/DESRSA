package com.cn.school.exception;

public class UserValidationException extends RuntimeException{
    public UserValidationException(String msg) {
        super(msg);
    }
}
