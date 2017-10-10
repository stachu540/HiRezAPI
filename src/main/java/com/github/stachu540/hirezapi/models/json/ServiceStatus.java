package com.github.stachu540.hirezapi.models.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.stachu540.hirezapi.api.StatusServer;
import com.github.stachu540.hirezapi.api.serverstatus.ServerStatusIncident;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
public class ServiceStatus extends Model {
    public enum Status {
        UP,
        DOWN
    }

    private final Status status;
    private final String version;

    private StatusServer serverStatus;

    public ServiceStatus(@JsonProperty("status") String status, @JsonProperty("version") String version) {
        this.status = Status.valueOf(status);
        this.version = version;
    }

    public ServerStatusIncident getLastIncident() {
        return serverStatus.getIncident(0);
    }

    public void setServerStatus(StatusServer status) {
        this.serverStatus = status;
        this.serverStatus.setStatus(this.status);
    }
}
