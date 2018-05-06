package hirezapi.rest;

import hirezapi.json.Model;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RestErrorHandler {
    private final RestController restController;

    public boolean hasError(Response response) throws IOException {
        if (response.isSuccessful()) {
            Model model = restController.getMapper().readValue(response.body().bytes(), Model.class);
            return StringUtils.isBlank(model.getReturnedMessage());
        } else {
            return response.code() >= 400;
        }
    }

    public void handleError(Response response) throws IOException {
        if (response.isSuccessful()) {
            Model model = restController.getMapper().readValue(response.body().bytes(), Model.class);
            throw new RestException(model.getReturnedMessage());
        } else {
            throw new RestException(response.message());
        }
    }
}
