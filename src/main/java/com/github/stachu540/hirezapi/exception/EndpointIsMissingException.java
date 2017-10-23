package com.github.stachu540.hirezapi.exception;

import com.github.stachu540.hirezapi.models.json.Model;

public class EndpointIsMissingException extends RuntimeException {
  public <T extends Model> EndpointIsMissingException(Class<T> classModel) {
    super(
        String.format(
            "Missing annotation value for class: %s [full_path:%s]",
            classModel.getSimpleName(), classModel.getCanonicalName()));
  }
}
