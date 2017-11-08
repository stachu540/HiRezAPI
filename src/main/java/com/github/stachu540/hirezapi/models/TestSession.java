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
  private final boolean successful;
  private final Date timestamp;

  public TestSession(String message) {
    Matcher matcher =
        Pattern.compile("^(.+): developer: ([0-9]{4}) time: (?<timestamp>.+) signature: (.+) session: (.+)$").matcher(message.replace("\"", ""));
    if (matcher.find()) {
      this.successful = true;
      this.timestamp = parse(matcher.group("timestamp"));
    } else {
      this.successful = false;
      this.timestamp = new Date();
    }
  }
}
