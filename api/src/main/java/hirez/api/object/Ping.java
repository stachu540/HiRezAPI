package hirez.api.object;

import hirez.api.HiRezUtils;
import lombok.Data;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Ping {
    private final String rawMessage;

    private final String game;
    private final String version;
    private final String patchVersion;
    private final boolean pingReceived;
    private final Date timestamp;

    public Ping(String raw) {
        this.rawMessage = raw;

        Matcher matcher = Pattern.compile("^(?<game>.+)API \\(ver (?<version>(?:[0-9]+\\.){3}[0-9]+)\\)" + " \\[PATCH - (?<versionName>.+)] - (?<ping>.+)\\. Server Date:(?<timestamp>.+)$").matcher(raw);

        matcher.find();

        this.game = matcher.group("game");
        this.version = matcher.group("version");
        this.patchVersion = matcher.group("versionName");
        this.pingReceived = matcher.group("ping").contains("successful");
        this.timestamp = HiRezUtils.parse(matcher.group("timestamp"));
    }
}
