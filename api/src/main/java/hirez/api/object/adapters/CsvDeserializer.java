package hirez.api.object.adapters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CsvDeserializer extends JsonDeserializer<List<String>> {

    @Override
    public List<String> getNullValue(DeserializationContext ctxt) throws JsonMappingException {
        return Collections.emptyList();
    }

    @Override
    public List<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String v = p.getValueAsString();
        if (v.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(v.split(","));
    }
}
