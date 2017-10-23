package com.github.stachu540.hirezapi.models.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;
import lombok.Data;


@Data
public abstract class Model {
  @JsonProperty("ret_msg")
  private String serverMessage;

  Date parse(String timestamp) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
    sdf.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
    return sdf.parse(timestamp);
  }
}
