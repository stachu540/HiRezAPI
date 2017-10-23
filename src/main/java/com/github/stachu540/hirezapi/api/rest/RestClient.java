package com.github.stachu540.hirezapi.api.rest;

import com.github.stachu540.hirezapi.api.Authentication;
import org.springframework.web.client.RestTemplate;

public class RestClient {

  private final RestTemplate rest = new RestTemplate();
  private final Authentication authentication;

  public RestClient(Authentication authentication) {
    this.authentication = authentication;
    this.rest.setErrorHandler(new RestErrorHandler());
  }

  public <O extends Object> O request(String endpoint, Class<O> model, String... args) {
    String url = authentication.getUrl(endpoint, args);
    authentication.getLogger().debug("Request for endpoint url: {}", url);
    return rest.getForObject(url, model);
  }
}
