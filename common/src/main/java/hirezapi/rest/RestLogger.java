package hirezapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

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
        logger.info("\tURI: {}", request.getURI());
        logger.info("\tMethod: {}", request.getMethod());
        logger.info("\tHeaders: {}", request.getHeaders());
        logger.info("\tBody: {}", new String(body));
        logger.info("-----------------------------");
        logger.info("\tResponse:{}", System.lineSeparator() + new BufferedReader(new InputStreamReader(response.getBody())).lines().collect(Collectors.toList()));
    }
}
