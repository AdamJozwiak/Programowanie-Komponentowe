package com.sudoku;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SudokuBoxTest {
    @Test
    public void verify() {
        List<SudokuField> box = Arrays.asList(new SudokuField[9]);

        for (int i = 0; i < 9; i++) {
            box.set(i, new SudokuField());
        }

        box.get(1).setFieldValue(2);
        box.get(6).setFieldValue(2);
        SudokuBox sudokuBox = new SudokuBox(box);
        assertFalse(sudokuBox.verify());
        box.get(6).setFieldValue(3);
        assertTrue(sudokuBox.verify());
    }

}
