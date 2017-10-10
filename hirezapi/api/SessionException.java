package pl.stachuofficial.hirezapi.api;

class SessionException extends Exception {

    SessionException(String message) {
        super(message);
    }

    SessionException(Throwable message) {
        super(message);
    }

}
