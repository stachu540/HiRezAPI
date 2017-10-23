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

  public static ServerStatus get(String status) {
    return valueOf(status.replace(" ", "_"));
  }

  @Override
  public String toString() {
    return this.name().replace("_", " ");
  }
}
