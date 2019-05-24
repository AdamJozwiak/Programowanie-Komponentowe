package com.sudoku;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuFieldTest {

    @Test
    public void compareTo() {
        SudokuField sudokuField=new SudokuField(5);
        SudokuField sudokuField1=new SudokuField(6);
        SudokuField sudokuField2=new SudokuField(4);

        Assert.assertEquals(-1, sudokuField.compareTo(sudokuField1));
        Assert.assertEquals(0, sudokuField.compareTo(sudokuField));
        Assert.assertEquals(1, sudokuField.compareTo(sudokuField2));
    }
}