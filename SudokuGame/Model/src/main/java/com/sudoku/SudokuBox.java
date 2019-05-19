package com.sudoku;

import java.io.Serializable;
import java.util.List;

public class SudokuBox extends SudokuDRY implements Cloneable, Serializable {
    public SudokuBox(final List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
