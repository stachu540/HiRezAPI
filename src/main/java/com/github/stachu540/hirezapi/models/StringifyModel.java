package com.github.stachu540.hirezapi.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;

public class StringifyModel {
  Date parse(String timestamp) {
    if (timestamp.contains("\\/")) {
      timestamp = timestamp.replace("\\/", "/");
    }
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
      sdf.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
      if (timestamp == null) {
        return new Date();
      } else {
        return sdf.parse(timestamp);
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
}
