package com.shev.server.exeption;

public class IllegalDateParametersException extends Exception {
    public IllegalDateParametersException() {
    }

    public IllegalDateParametersException(String message) {
        super(message);
    }

    public IllegalDateParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalDateParametersException(Throwable cause) {
        super(cause);
    }

    public IllegalDateParametersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
