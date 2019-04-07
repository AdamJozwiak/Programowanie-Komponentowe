package com.sudoku;

public class SudokuField {
    private int value;

    public SudokuField() {
    }

    public SudokuField(int fieldValue) {
        setFieldValue(fieldValue);
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        this.value = value;
    }
}
