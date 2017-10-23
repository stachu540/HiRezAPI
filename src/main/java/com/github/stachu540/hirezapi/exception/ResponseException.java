package com.github.stachu540.hirezapi.exception;

import java.io.IOException;
import org.springframework.http.client.ClientHttpResponse;

public class ResponseException extends Exception {
  public ResponseException(ClientHttpResponse response) throws IOException {
    super(
        String.format(
            "[status-code: %d; message: %s]",
            response.getRawStatusCode(), response.getStatusText()));
  }
}
