package hirez.api.object.adapters;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.DurationDeserializer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@JacksonAnnotationsInside
@Retention(RetentionPolicy.RUNTIME)
@JsonDeserialize(using = DurationTimeDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.STRING)
public @interface DurationTime {
    ChronoUnit value() default ChronoUnit.SECONDS;
}
