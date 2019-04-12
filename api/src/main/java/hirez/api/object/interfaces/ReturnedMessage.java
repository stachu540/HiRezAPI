package hirez.api.object.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;

public interface ReturnedMessage {
    @JsonProperty("ret_msg")
    String getReturnedMessage();
}
