package hirez.api.object.adapters;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeDeserializer extends JsonDeserializer<Date> implements ContextualDeserializer {

    private DateTimeFormat annotationDTF;

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        SimpleDateFormat sdf = new SimpleDateFormat(annotationDTF.value());
        sdf.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));

        try {
            return sdf.parse(p.getText());
        } catch (ParseException e) {
            throw new JsonParseException(p, "Cannot parse to Date format!", e);
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        annotationDTF = property.getMember().getAnnotation(DateTimeFormat.class);

        return this;
    }
}
