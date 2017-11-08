package com.github.stachu540.hirezapi.api;

import com.github.stachu540.hirezapi.HiRezAPI;
import com.github.stachu540.hirezapi.annotations.Endpoint;
import com.github.stachu540.hirezapi.api.rest.RestClient;
import com.github.stachu540.hirezapi.enums.url.BasePlatform;
import com.github.stachu540.hirezapi.exception.EndpointIsMissingException;
import com.github.stachu540.hirezapi.exception.SessionException;
import com.github.stachu540.hirezapi.models.TestSession;
import com.github.stachu540.hirezapi.models.json.CreateSession;
import com.github.stachu540.hirezapi.models.json.Model;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.SimpleTimeZone;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Authentication class response for handling session keys and replacing if it is expired.
 * @author <a href="damian@stachuofficial.pl">Damian Staszewski</a>
 * @since 2.0
 * @param <T> enum type extended {@link BasePlatform}
 * @param <H> api
 */
@Setter
@Getter
public class Authentication<M extends Map<T, String>, T extends BasePlatform, H extends HiRez<T>> {
  private final String devId;
  private final String authKey;
  private final M sessions;

  @Getter(AccessLevel.NONE)
  private final H api;

  private final T platform;

  private final RestClient restClient = new RestClient(this);

  /**
   * Constructor class for session handler.
   * @param main the core class {@link HiRezAPI}
   * @param basePlatform Base Platform
   * @param api api type
   */
  @SuppressWarnings("unchecked")
  public Authentication(HiRezAPI main, T basePlatform, H api) {
    this.devId = main.getDevId();
    this.authKey = main.getAuthKey();
    this.platform = basePlatform;
    this.api = api;
    this.sessions = (M) main.getSessionCache();
  }

  /**
   * Getting url.
   * @param endpoint base url
   * @param args endpoint arguments
   * @return formatted URL
   */
  public String getUrl(String endpoint, String... args) {
    return String.format(
        "%s/%s%s",
        platform.getUrl(),
        getEndpoint(endpoint),
        (args.length > 0) ? "/" + String.join("/", args) : "");
  }

  /**
   * Checking if session key is exist.
   * @return existing session key for specified {@link BasePlatform}
   */
  public boolean hasSessionKey() {
    return sessions.containsKey(platform) && sessions.get(platform) != null;
  }

  /**
   * Getting data from base url.
   * @param endpoint base url
   * @param classModel Class model
   * @param args arguments
   * @param <O> basic model
   * @return data from specified base url to class model
   */
  <O> O get(String endpoint, Class<O> classModel, String... args) {
    O objectData = restClient.request(endpoint, classModel, args);
    if (objectData instanceof Model) {
      try {
        Model model = (Model) objectData;
        if (model.getServerMessage() != null && !endpoint.equals("createsession")) {
          throw new SessionException(model.getServerMessage());
        }
      } catch (SessionException e) {
        e.printStackTrace();
      }
    }
    return objectData;
  }

  /**
   * getting data from class model containing {@link Endpoint}.
   * @param classModel class model with {@link Endpoint} annotation
   * @param args arguments
   * @param <T> basic class {@link Model}
   * @return data from specified model contains {@link Endpoint} annotation into class model
   * @throws EndpointIsMissingException class extended {@link Model} doesn't contain {@link Endpoint} annotation
   */
  <T extends Model> T get(Class<T> classModel, String... args) {
    String endpoint = classModel.getDeclaredAnnotation(Endpoint.class).value();
    if (endpoint == null && classModel.isAnnotationPresent(Endpoint.class)) {
      throw new EndpointIsMissingException(classModel);
    } else {
      return get(endpoint, classModel, args);
    }
  }

  /**
   * Creating session for specify {@link BasePlatform}.
   */
  @SuppressWarnings("unchecked")
  private void createSession() {
    CreateSession session = api.createSession();
    if (session.getRetMsg().equals("Approved")) {
      sessions.put(platform, session.getSessionId());
    }
  }

  /**
   * Testing created session.
   * @return test successful
   */
  private boolean testSession() {
    TestSession session = api.testSession();
    return session.isSuccessful();
  }

  /**
   * Getting base url endpoint.
   * @param endpoint endpoint name
   * @return base URL from specify {@link BasePlatform}
   */
  private String getEndpoint(String endpoint) {
    String baseEndpoint = String.format("%s%s", endpoint, "json");
    switch (endpoint) {
      case "ping":
        return baseEndpoint;
      case "createsession":
        return String.format(
            "%s/%s/%s/%s", baseEndpoint, devId, getSignatue(endpoint), getTimestamp());
      default:
        if (hasSessionKey() && (endpoint.equals("testsession") || testSession())) {
          return String.format(
              "%s/%s/%s/%s/%s",
                baseEndpoint, devId, getSignatue(endpoint), sessions.get(platform), getTimestamp());
        } else {
          createSession();
          return getEndpoint(endpoint);
        }
    }
  }

  /**
   * Getting signature.
   * @param endpoint endpoint name
   * @return MD5 converted signature format <code>devId + endpoint + authKey + {@link #getTimestamp()}</code>
   */
  private String getSignatue(String endpoint) {
    try {
      String sig = devId + endpoint + authKey + getTimestamp();
      StringBuilder sb = new StringBuilder();

      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(sig.getBytes());
      byte[] bytes = md.digest();

      for (byte bit : bytes) {
        String hex = Integer.toHexString(0xff & bit);
        if (hex.length() == 1) {
          sb.append("0");
        }
        sb.append(hex);
      }
      return sb.toString();
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  /**
   * Getting current timestamp.
   * @return timestamp formatted into UTC timezone <code>yyyMMddHHmmss</code>
   * @see SimpleDateFormat
   */
  private String getTimestamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
    return sdf.format(new Date());
  }
}
