package hirezapi.json;

import lombok.Data;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@ToString(exclude = {"rawMessage"})
public class SessionTest {
    private final boolean successful;
    private final Instant timestamp;
    private final String rawMessage;

    public SessionTest(String message) {
        Matcher matcher =
                Pattern.compile("^(.+): developer: ([0-9]{4}) time: (?<timestamp>.+) signature: (.+) session: (.+)$").matcher(message.replace("\"", ""));
        this.rawMessage = message;
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
        } catch (ParseException ignore) {}
        return Instant.now();
    }
}
