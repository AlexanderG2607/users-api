package com.globalogic.bci.usersapi.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("Usuario no encontrado");
    }
}
