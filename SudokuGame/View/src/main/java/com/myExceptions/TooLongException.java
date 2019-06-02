package com.myExceptions;

public class TooLongException extends CellErrorException {

    public TooLongException(String message, Throwable cause){
        super(message, cause);
    }

    public TooLongException(String message){
        super(message);
    }
}
