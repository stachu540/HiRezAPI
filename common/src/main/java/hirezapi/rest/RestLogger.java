package hirezapi.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestLogger implements ClientHttpRequestInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        ClientHttpResponse response = execution.execute(request, body);

        log(request, body, response);

        return response;
    }

    private void log(HttpRequest request, byte[] body, ClientHttpResponse response) throws IOException {
        // do logging
        logger.info("Request:");
        logger.info("\tURI: %s", request.getURI());
        logger.info("\tMethod: %s", request.getMethod());
        logger.info("\tHeaders: %s", request.getHeaders());
        logger.info("\tHeaders: %s", request.getHeaders());
        logger.info("\tBody: %s", new String(body));
    }
}
