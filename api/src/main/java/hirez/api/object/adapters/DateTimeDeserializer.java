package hirez.api.object.adapters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateTimeDeserializer extends JsonDeserializer<Date> implements ContextualDeserializer {

    private DateTimeFormat annotationDTF;

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (p.hasToken(JsonToken.VALUE_NULL)) return null;
        String text = p.getText();
        if (text.isEmpty()) return null;

        return Date.from(DateTimeFormatter.ofPattern(annotationDTF.value(), Locale.US)
                .withZone(ZoneId.from(ZoneOffset.UTC))
                .parse(text, Instant::from));
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        annotationDTF = property.getMember().getAnnotation(DateTimeFormat.class);

        return this;
    }
}
