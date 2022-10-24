package com.halloid.sdk.exception;

public class InvalidTokenSignatureException extends RuntimeException {

    public InvalidTokenSignatureException(String message) {
        super(message);
    }
}
