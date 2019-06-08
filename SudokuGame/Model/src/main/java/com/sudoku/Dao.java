package com.sudoku;

import myExceptions.DataException;
import myExceptions.FileException;

public interface Dao<T> {
    T read() throws FileException, DataException;
    void write(T obj) throws FileException, DataException;
}
