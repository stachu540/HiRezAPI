package hirezapi.exceptions;

public class InvalidQueueException extends RuntimeException {
  public InvalidQueueException(String message) {
    this(message, null);
  }

  public InvalidQueueException(String message, Throwable cause) {
    super(message, cause);
  }
}
