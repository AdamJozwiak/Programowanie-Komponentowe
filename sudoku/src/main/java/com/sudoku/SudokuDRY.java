package com.sudoku;

import java.util.Arrays;
import java.util.List;

public class SudokuDRY {
    private List<SudokuField> sudokuFields;

    public SudokuDRY(final List<SudokuField> sudokuFields) {
        this.sudokuFields = Arrays.asList(new SudokuField[9]);

        for (int i = 0; i < 9; i++) {
            this.sudokuFields.set(i, sudokuFields.get(i));
        }
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (sudokuFields.get(i).getFieldValue() == sudokuFields.get(j).getFieldValue()
                        && sudokuFields.get(i).getFieldValue() != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
