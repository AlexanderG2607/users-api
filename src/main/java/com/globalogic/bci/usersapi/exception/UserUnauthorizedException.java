package com.globalogic.bci.usersapi.exception;

public class UserUnauthorizedException extends RuntimeException{
    public UserUnauthorizedException(String mesasage) {
        super(mesasage);
    }

}
