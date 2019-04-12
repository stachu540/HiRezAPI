package hirez.api;

import hirez.api.object.interfaces.ReturnedMessage;

public class HiRezException extends RuntimeException {
    public HiRezException(ReturnedMessage message) {
        this(message.getReturnedMessage());
    }

    public HiRezException(String message) {
        super(message);
    }

    public HiRezException(Throwable cause) {
        super(cause);
    }

    public HiRezException(String message, Throwable cause) {
        super(message, cause);
    }
}
