package hirez.api.object.interfaces;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface IDObject<T> {
    @JsonProperty("id")
    @JsonAlias({"ID", "Id"})
    T getId();
}
