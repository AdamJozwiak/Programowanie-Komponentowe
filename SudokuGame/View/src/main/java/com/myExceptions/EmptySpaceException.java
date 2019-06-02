package com.myExceptions;

public class EmptySpaceException extends CellErrorException {

    public EmptySpaceException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
