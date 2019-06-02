package com.myExceptions;

public class ForbiddenCharException extends CellErrorException {

    public ForbiddenCharException(String message, Throwable cause) {
        super(message, cause);
    }
}
