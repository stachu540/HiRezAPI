package com.github.stachu540.hirezapi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Endpoint target for API.
 * @author <a href="damian@stachuofficial.pl">Damian Staszewski</a>
 * @since 2.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Endpoint {
  /**
   * endpoint name
   */
  String value();
}
