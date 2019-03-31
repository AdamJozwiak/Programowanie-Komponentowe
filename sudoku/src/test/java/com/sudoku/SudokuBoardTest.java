package com.sudoku;

import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuBoardTest {

    @Test
    public void solve(){
        SudokuBoard sudokuBoard = new SudokuBoard(5);
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();

        sudokuSolver.solve(sudokuBoard);


        assertTrue(sudokuBoard.checkBoard());

        //SudokuBoard sudokuBoard1 = new SudokuBoard(5);

        //sudokuSolver.solve(sudokuBoard1);

       // assertNotEquals(sudokuBoard.getBoard(), sudokuBoard1.getBoard());
    }
}