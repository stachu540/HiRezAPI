package hirezapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import hirezapi.json.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class RestErrorHandler implements ResponseErrorHandler {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            Model model = mapper.readValue(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8), Model.class);
            return StringUtils.isBlank(model.getReturnedMessage());
        } else {
            return response.getRawStatusCode() >= 400;
        }
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (hasError(response)) {
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                Model model = mapper.readValue(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8), Model.class);
                throw new RestException(model.getReturnedMessage());
            } else {
                throw new RestException(response.getStatusText());
            }
        }
    }
}
