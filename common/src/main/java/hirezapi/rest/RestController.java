package hirezapi.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import hirezapi.Platform;
import hirezapi.json.Model;
import hirezapi.json.deserializer.BooleanTextDeserializer;
import hirezapi.json.deserializer.InstantTimeDeserializer;
import java.io.IOException;
import java.time.Instant;
import lombok.Getter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class RestController {
  private final OkHttpClient httpClient;
  private final ObjectMapper mapper;
  private final HttpUrl baseUrl;
  private final Logger log = LoggerFactory.getLogger(getClass());

  /**
   * The main REST Client Controller.
   * @param platform Game Platform with specific URL
   */
  public RestController(Platform platform) {
    this.baseUrl = platform.getBaseUrl();
    this.httpClient = buildHttpClient();
    this.mapper = buildObjectMapper();
  }

  private ObjectMapper buildObjectMapper() {
    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addDeserializer(Instant.class, new InstantTimeDeserializer());
    simpleModule.addDeserializer(Boolean.class, new BooleanTextDeserializer());

    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);
    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    mapper.registerModule(simpleModule);

    return mapper;
  }

  private OkHttpClient buildHttpClient() {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(log::info);
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

    return new OkHttpClient.Builder()
          .addInterceptor(loggingInterceptor)
          .build();
  }

  /**
   * Request query using {@link hirezapi.endpoints.AbstractEndpoint#buildUrl(String, String...)}.
   * @param uri           URI parsed using
   * {@link hirezapi.endpoints.AbstractEndpoint#buildUrl(String, String...)}
   * @param classResponse Class Response
   * @param <T>           POJO Object
   * @return response form requested URI
   */
  public <T> T request(String uri, Class<T> classResponse) {
    Request request = new Request.Builder()
          .get()
          .url(baseUrl.newBuilder().addPathSegments(uri).build())
          .build();
    try (Response response = httpClient.newCall(request).execute()) {
      byte[] responseBodyByte = response.body().bytes();
      if (hasError(response, responseBodyByte)) {
        handleError(response, responseBodyByte);
      }
      return mapper.readValue(responseBodyByte, classResponse);
    } catch (JsonParseException e) {
      throw new RestException("Cannot parse response", e);
    } catch (JsonMappingException e) {
      throw new RestException("Cannot map response", e);
    } catch (IOException e) {
      throw new RestException("Cannot handle response", e);
    }
  }

  private boolean hasError(Response response, byte[] responseBody) throws IOException {
    if (response.isSuccessful()) {
      Model model = formatResponse(responseBody);
      return !StringUtils.isBlank(model.getReturnedMessage())
            && (!response.request().url().encodedPathSegments().contains("createsessionjson")
            && model.getReturnedMessage().equals("Approved"));
    } else {
      return response.code() >= 400;
    }
  }

  private Model formatResponse(byte[] responseBody) throws IOException {
    return ((responseBody[0] == '['))
          ? mapper.readValue(responseBody, Model[].class)[0]
          : mapper.readValue(responseBody, Model.class);
  }

  private void handleError(Response response, byte[] responseBody) throws IOException {
    if (response.isSuccessful()) {
      Model model = formatResponse(responseBody);
      throw new RestException(model.getReturnedMessage());
    } else {
      throw new RestException(response.message());
    }
  }
}
