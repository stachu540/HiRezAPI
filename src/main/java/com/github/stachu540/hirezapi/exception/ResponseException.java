package com.github.stachu540.hirezapi.exception;

import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class ResponseException extends Exception {
  public ResponseException(ClientHttpResponse response) throws IOException {
    super(
        String.format(
            "[status-code: %d; message: %s]",
            response.getRawStatusCode(), response.getStatusText()));
  }
}
