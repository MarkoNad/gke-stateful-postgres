package com.gkedemo.exception;

public class RemoteInvocationFailedException extends RuntimeException {

    public RemoteInvocationFailedException(String message) {
        super(message);
    }

    public RemoteInvocationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteInvocationFailedException(Throwable cause) {
        super(cause);
    }

    public RemoteInvocationFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
