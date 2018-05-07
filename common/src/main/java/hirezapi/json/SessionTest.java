package hirezapi.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"rawMessage"})
public class SessionTest {
  private final boolean successful;
  private final Instant timestamp;
  private final String rawMessage;

  private static final Pattern PATTERN = Pattern.compile("^(.+): developer: ([0-9]{4}) time: "
        + "(?<timestamp>.+) signature: (.+) session: (.+)$");

  /**
   * Test Session POJO Object.
   * @param rawMessage RAW Message
   */
  public SessionTest(String rawMessage) {
    Matcher matcher = PATTERN.matcher(rawMessage.replace("\"", ""));

    this.rawMessage = rawMessage;
    if (matcher.find()) {
      this.successful = true;
      this.timestamp = parse(matcher.group("timestamp"));
    } else {
      this.successful = false;
      this.timestamp = Instant.now();
    }
  }

  private Instant parse(String timestamp) {
    if (timestamp.contains("\\/")) {
      timestamp = timestamp.replace("\\/", "/");
    }
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
      sdf.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
      return sdf.parse(timestamp).toInstant();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return Instant.now();
  }
}
