package com.zest.employee.exception;

public class UserNotFound extends RuntimeException{
    public UserNotFound(){
        super("USER NOT FOUND !");
    }
}
