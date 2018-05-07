package hirezapi.session;

import hirezapi.json.SessionCreation;
import lombok.Getter;

@Getter
public class SessionCreationException extends RuntimeException {
  private final SessionCreation sessionCreation;

  public SessionCreationException(SessionCreation sessionCreation) {
    super(sessionCreation.getReturnedMessage());
    this.sessionCreation = sessionCreation;
  }
}
