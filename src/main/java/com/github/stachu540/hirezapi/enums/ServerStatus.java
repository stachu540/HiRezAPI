package com.github.stachu540.hirezapi.enums;

public enum ServerStatus {
    Investigating,
    Identified,
    Monitoring,
    Verifying,
    Resolved,
    Scheduled,
    Update,
    In_progress,
    Completed;

    @Override
    public String toString() {
        return this.name().replace("_", " ");
    }

    public static ServerStatus get(String status) {
        return valueOf(status.replace(" ", "_"));
    }
}
