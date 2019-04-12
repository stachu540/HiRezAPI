package hirez.api.object;

import hirez.api.HiRezUtils;
import lombok.Data;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class TestSession {
    private final String rawMessage;
    private final boolean successful;
    private final Date timestamp;

    public TestSession(String raw) {
        this.rawMessage = raw;

        Matcher matcher = Pattern.compile("^(.+): developer: ([0-9]{4}) time: " + "(?<timestamp>.+) signature: (.+) session: (.+)$").matcher(raw.replace("\"", ""));
        if (matcher.find()) {
            successful = true;
            timestamp = HiRezUtils.parse(matcher.group("timestamp"));
        } else {
            successful = false;
            timestamp = new Date();
        }
    }
}
