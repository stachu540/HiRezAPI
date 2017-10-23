package com.github.stachu540.hirezapi.models;

import com.github.stachu540.hirezapi.annotations.Endpoint;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Endpoint("testsession")
@EqualsAndHashCode(callSuper = true)
public class TestSession extends StringifyModel {
  private final String message;
  private final String devId;
  private final Date timestamp;
  private final String signature;

  public TestSession(String message) {
    Matcher matcher =
        Pattern.compile("^(?<message>.+): developer: (?<devId>[0-9]{4}) time: (?<timestamp>.+) signature: (?<sig>.+) session: (?<session>.+)$").matcher(message.replace("\"", ""));
    matcher.find();
    this.message = matcher.group("message");
    this.devId = matcher.group("devId");
    this.timestamp = parse(matcher.group("timestamp"));
    this.signature = matcher.group("sig");
  }

  public boolean isSuccessful() {
    return message.equals("This was a successful test with the following parameters added");
  }
}
