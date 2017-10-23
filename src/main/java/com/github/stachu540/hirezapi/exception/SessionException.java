package com.github.stachu540.hirezapi.exception;

public class SessionException extends Exception {
  public SessionException(String message) {
    super(String.format("There is exception on session: %s", message));
  }
}
