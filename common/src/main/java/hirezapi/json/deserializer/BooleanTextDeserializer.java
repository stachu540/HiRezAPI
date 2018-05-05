package hirezapi.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class BooleanTextDeserializer extends StdDeserializer<Boolean> {

    public BooleanTextDeserializer() {
        this(null);
    }

    public BooleanTextDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String b = p.getText();
        if (b.equalsIgnoreCase("y")) {
            return true;
        } else if (b.equalsIgnoreCase("n")) {
            return false;
        }
        return false;
    }
}
