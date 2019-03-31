package com.sudoku;

public class SudokuDRY {
    private SudokuField[] sudokuFields;

    public SudokuDRY(final SudokuField[] sudokuFields) {
        this.sudokuFields = sudokuFields;
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (sudokuFields[i].getFieldValue() == sudokuFields[j].getFieldValue()
                        && sudokuFields[i].getFieldValue() != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
