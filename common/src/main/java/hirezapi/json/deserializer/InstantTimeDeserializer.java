package hirezapi.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

public class InstantTimeDeserializer extends JsonDeserializer<Instant> {
  @Override
  public Instant deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
    DateFormat format = new SimpleDateFormat("M/d/yyyy h:mm:ss a");
    format.setTimeZone(TimeZone.getTimeZone("UTC"));
    try {
      Date date = format.parse(p.getText());
      return date.toInstant();
    } catch (ParseException e) {
      throw new IOException("Cannot parsing date", e);
    }
  }
}
