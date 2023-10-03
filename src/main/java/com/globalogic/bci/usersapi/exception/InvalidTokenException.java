package com.globalogic.bci.usersapi.exception;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String email) {
        super("El usuario con email '" + email + "' ya existe.");
    }
}
