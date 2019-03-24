package com.sudoku;

import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuBoardTest {

    @Test
    public void solve(){
        SudokuBoard sudokuBoard = new SudokuBoard(5);
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();

        sudokuSolver.solve(sudokuBoard);

        boolean wartownik = false;

        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                if(sudokuBoard.checkElement(i,j, sudokuBoard.getBoard()[i][j]))
                {
                    wartownik = true;
                }
                else {
                    wartownik = false;
                    assertFalse(wartownik);
                }
            }
        }
        assertTrue(wartownik);

        SudokuBoard sudokuBoard1 = new SudokuBoard(5);

        sudokuSolver.solve(sudokuBoard1);

        assertNotEquals(sudokuBoard.getBoard(), sudokuBoard1.getBoard());
    }
}