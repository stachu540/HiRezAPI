package com.github.stachu540.hirezapi.exception;

public class ProfileHiddenException extends Exception {
    public ProfileHiddenException(String username, long id) {
        super(String.format("Profile is hidden: %s (id: %d)", username, id));
    }
}
