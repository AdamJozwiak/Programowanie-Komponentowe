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
    }
}