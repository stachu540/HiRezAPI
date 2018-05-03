package hirezapi.rest;

public class RestException extends RuntimeException {
    public RestException(String message) {
        this(message, null);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }
}
