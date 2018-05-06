package hirezapi.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import hirezapi.Platform;
import lombok.Getter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Getter
public class RestController {
    private final OkHttpClient httpClient;
    private final ObjectMapper mapper;
    private final HttpUrl baseUrl;
    private final SimpleModule simpleModule;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private RestErrorHandler errorHandler = new RestErrorHandler(this);

    public RestController(Platform platform) {
        this.baseUrl = platform.getBaseUrl();
        this.httpClient = buildHttpClient();
        this.mapper = buildObjectMapper();
        this.simpleModule = new SimpleModule();
    }

    public <T> void addDeserializer(Class<T> type, JsonDeserializer<? extends T> deserializer) {
        simpleModule.addDeserializer(type, deserializer);
    }

    private ObjectMapper buildObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        return mapper;
    }

    private OkHttpClient buildHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(log::debug);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    public <T> T request(String uri, Class<T> classResponse) {
        mapper.registerModule(simpleModule);
        Request request = new Request.Builder()
                .get()
                .url(baseUrl.newBuilder().addPathSegments(uri).build())
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (errorHandler.hasError(response)) {
                errorHandler.handleError(response);
            }
            return mapper.readValue(response.body().bytes(), classResponse);
        } catch (JsonParseException e) {
            throw new RestException("Cannot parse response", e);
        } catch (JsonMappingException e) {
            throw new RestException("Cannot map response", e);
        } catch (IOException e) {
            throw new RestException("Cannot handle response", e);
        }

    }
}
