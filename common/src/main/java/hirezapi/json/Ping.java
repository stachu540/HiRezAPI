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
@ToString(exclude = "rawMessage")
public class Ping {
    private final String game;
    private final String version;
    private final String versionName;
    private final boolean pingRecived;
    private final Instant timestamp;

    private final String rawMessage;

    public Ping(String rawMessage) {
        this.rawMessage = rawMessage.replace("\"", "");
        Matcher matcher = Pattern
                .compile("^(?<game>Smite|Paladins)API \\(ver (?<version>(?:[0-9]+\\.){3}[0-9]+)\\) \\[PATCH - (?<versionName>.+)] - (?<ping>.+)\\. Server Date:(?<timestamp>.+)$")
                .matcher(this.rawMessage);
        matcher.find();
        this.game = matcher.group("game");
        this.version = matcher.group("version");
        this.versionName = matcher.group("versionName");
        this.pingRecived = matcher.group("ping").contains("successful");
        this.timestamp = parse(matcher.group("timestamp"));
    }

    private Instant parse(String timestamp) {
        if (timestamp.contains("\\/")) timestamp = timestamp.replace("\\/", "/");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
            sdf.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
            return sdf.parse(timestamp).toInstant();
        } catch (ParseException ignore) {
        }
        return Instant.now();
    }
}
