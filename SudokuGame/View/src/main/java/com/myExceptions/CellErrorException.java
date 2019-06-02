package com.myExceptions;

import java.util.ResourceBundle;

public class CellErrorException extends Exception {

    private ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");

    public CellErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CellErrorException(String message) {
        super(message);
    }
}
