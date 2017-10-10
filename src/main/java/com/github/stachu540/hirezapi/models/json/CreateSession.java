package com.github.stachu540.hirezapi.models.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.stachu540.hirezapi.annotations.Endpoint;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.ParseException;
import java.util.Date;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = false)
@Endpoint("createsession")
public class CreateSession extends Model {
    private String sessionId;
    private String retMsg;
    private Date timestamp;

    public void setTimestamp(String timestamp) {
        try {
            this.timestamp = parse(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
