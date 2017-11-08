package com.github.stachu540.hirezapi.api.rest;

import com.github.stachu540.hirezapi.api.Authentication;
import com.jcabi.log.Logger;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="damian@stachuofficial.pl">Damian Staszewski</a>
 * @since 2.0
 */
public class RestClient {

  private final RestTemplate rest = new RestTemplate();
  private final Authentication authentication;

  public RestClient(Authentication authentication) {
    this.authentication = authentication;
    this.rest.setErrorHandler(new RestErrorHandler());
  }

  /**
   * Request from API
   * @param endpoint URL endpoint
   * @param model Class model
   * @param args arguments
   * @param <O> Object model
   * @return Data Response
   */
  public <O extends Object> O request(String endpoint, Class<O> model, String... args) {
    String url = authentication.getUrl(endpoint, args);
    Logger.debug(this,"Request for endpoint url: %s", url);
    return rest.getForObject(url, model);
  }
}
