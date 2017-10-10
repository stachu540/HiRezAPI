package com.github.stachu540.hirezapi.models;

import com.github.stachu540.hirezapi.annotations.Endpoint;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Endpoint("ping")
@EqualsAndHashCode(callSuper =  true)
public class Ping extends StringifyModel {

    private final String game;
    private final String version;
    private final String versionName;
    private final boolean pingRecived;
    private final Date timestamp;

    public Ping(String message) {
        Matcher matcher = Pattern
                .compile("^(?<game>Smite|Paladins)API \\(ver (?<version>(?:[0-9]+\\.){3}[0-9]+)\\) \\[PATCH - (?<versionName>.+)\\] - (?<ping>.+)\\. Server Date:(?<timestamp>.+)$")
                .matcher(message.replace("\"", ""));
        matcher.find();
        this.game = matcher.group("game");
        this.version = matcher.group("version");
        this.versionName = matcher.group("versionName");
        this.pingRecived = matcher.group("ping").contains("successful");
        this.timestamp = parse(matcher.group("timestamp"));
    }
}
