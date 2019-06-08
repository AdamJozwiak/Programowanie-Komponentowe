package com.sudoku;

import myExceptions.DataException;
import myExceptions.FileException;

public interface Dao<T> {
    public T read() throws FileException, DataException;
    public void write(T obj) throws FileException, DataException;
}
