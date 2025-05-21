package com.zest.employee.exception;

public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist(){
        super("USER IS ALREADY EXISTS !");
    }
}
