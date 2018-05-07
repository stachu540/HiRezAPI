package hirezapi.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class BooleanTextDeserializer extends JsonDeserializer<Boolean> {
  @Override
  public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    try {
      return p.getBooleanValue();
    } catch (IOException ignore) {
      String b = p.getText();
      if (b.equalsIgnoreCase("y")) {
        return true;
      } else if (b.equalsIgnoreCase("n")) {
        return false;
      }
      return false;
    }
  }
}
