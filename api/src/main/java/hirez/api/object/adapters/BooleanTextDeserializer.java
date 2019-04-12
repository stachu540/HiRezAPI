package hirez.api.object.adapters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;

public class BooleanTextDeserializer extends JsonDeserializer<Boolean> {

    @Override
    public Boolean getNullValue(DeserializationContext ctxt) throws JsonMappingException {
        return false;
    }

    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return p.getText().equalsIgnoreCase("y") || p.getText().equalsIgnoreCase("true");
    }
}
