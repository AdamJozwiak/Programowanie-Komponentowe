package com.sudoku;

import java.io.Serializable;
import java.util.List;

public class SudokuColumn extends SudokuDRY implements Cloneable, Serializable {
    public SudokuColumn(final List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
