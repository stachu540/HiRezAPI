package com.github.stachu540.hirezapi.api.rest;

import com.github.stachu540.hirezapi.exception.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestErrorHandler implements ResponseErrorHandler {
  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    return !response.getStatusCode().equals(HttpStatus.OK)
        && !response.getStatusCode().equals(HttpStatus.NO_CONTENT);
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    try {
      throw new ResponseException(response);
    } catch (ResponseException e) {
      System.err.println(e.getMessage());
    }
  }
}
