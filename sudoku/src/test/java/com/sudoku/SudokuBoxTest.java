package com.sudoku;

import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuBoxTest {
    @Test
    public void verify() {
        SudokuField[] box = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            box[i] = new SudokuField();
        }
        box[1].setFieldValue(2);
        box[6].setFieldValue(2);
        SudokuBox sudokuBox = new SudokuBox(box);
        assertFalse(sudokuBox.verify());
        box[6].setFieldValue(3);
        assertTrue(sudokuBox.verify());
    }

}
