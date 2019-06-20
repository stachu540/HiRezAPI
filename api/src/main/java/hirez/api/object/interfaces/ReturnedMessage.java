package hirez.api.object.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface ReturnedMessage {
    @JsonProperty("ret_msg")
    String getReturnedMessage();
}
