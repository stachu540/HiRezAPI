package hirezapi.endpoints;

import hirezapi.HiRezApi;
import hirezapi.Platform;
import hirezapi.json.SessionCreation;
import hirezapi.session.SessionCreationException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public abstract class AbstractEndpoint {
  protected final HiRezApi api;
  @Getter(AccessLevel.PROTECTED)
  private final Logger log = LoggerFactory.getLogger(getClass());

  protected String buildUrl(String endpoint, String... queryParams) {
    final String realEndpoint = ((!endpoint.toLowerCase().endsWith("json"))
          ? endpoint + "json" : endpoint);
    final String timestamp = getTimestamp();

    log.debug("Building URL with Endpoint \"{}\" with {}", endpoint,
          (queryParams.length > 0)
          ? String.format("Params: \"[%s]\"", String.join("/", queryParams))
                : "no params");

    switch (endpoint.toLowerCase()) {
      case "ping":
        return realEndpoint;
      case "createsession":
        return String.format("%s/%s/%s/%s", realEndpoint,
              api.getConfiguration().getDevId(),
              generateSignature(endpoint, timestamp),
              timestamp);
      default:
        if (hasSession(api.getConfiguration().getPlatform())) {
          log.debug("Execute Endpoint \"{}\" with {}", endpoint, (queryParams.length > 0)
                ? String.format("Params: \"[%s]\"", String.join("/", queryParams)) : "no params");
          return String.format("%s/%s/%s/%s/%s", realEndpoint,
                api.getConfiguration().getDevId(),
                generateSignature(endpoint, timestamp),
                api.sessionEndpoint().getSessionStorage().get(api.getConfiguration().getPlatform()),
                timestamp)
                + ((queryParams.length > 0) ? "/" + String.join("/", queryParams) : "");
        } else {
          log.debug("The Session key is missing. Starting create session");
          SessionCreation session = api.sessionEndpoint().create();
          if (!session.isApproved()) {
            throw new SessionCreationException(session);
          }
          return buildUrl(endpoint, queryParams);
        }
    }
  }

  private boolean hasSession(Platform platform) {
    return api.sessionEndpoint().getSessionStorage()
          .contains(platform);
  }

  private String generateSignature(String endpoint, String timestamp) {
    String templateSignature = new StringBuilder(api.getConfiguration().getDevId())
          .append(endpoint)
          .append(api.getConfiguration().getAuthKey())
          .append(timestamp)
          .toString();
    StringBuilder signatureBuilder = new StringBuilder();
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(templateSignature.getBytes());
      byte[] bytes = md.digest();

      for (byte bit : bytes) {
        String hex = Integer.toHexString(0xff & bit);
        if (hex.length() == 1) {
          signatureBuilder.append("0");
        }
        signatureBuilder.append(hex);
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    return signatureBuilder.toString();
  }

  private String getTimestamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
    return sdf.format(new Date());
  }
}
