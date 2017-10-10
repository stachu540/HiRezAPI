package com.github.stachu540.hirezapi.exception;

public class UnknownPlayerException extends Exception{
    public UnknownPlayerException(String username) {
        super(String.format("Player not found: %s", username));
    }
}
