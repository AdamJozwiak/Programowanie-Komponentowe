package com.sudoku;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class SudokuBoardTest {

    @Test
    public void solve(){
        SudokuBoardDaoFactory sudokuBoardDaoFactory=new SudokuBoardDaoFactory();
        SudokuBoard sudokuBoard = new SudokuBoard(5);
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();

        sudokuSolver.solve(sudokuBoard);

        assertTrue(sudokuBoard.checkBoard());
        //sudokuBoardDaoFactory.getFileDao("C:\\Users\\Michał\\Desktop\\xd.txt").write(sudokuBoard);
       //int b=sudokuBoard.hashCode();
       // System.out.println(sudokuBoard.toString());
    }
    @Test
    public void toStringTest(){
        SudokuBoard sudokuBoard=new SudokuBoard(0);
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                sudokuBoard.set(i,j,1);
            }
        }
        String board="SudokuBoard[sudokuField=[[1, 1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1, 1]]]";
        assertEquals(board,sudokuBoard.toString());
    }

    @Test
    public void equalsTest(){
        SudokuBoard sudokuBoard=new SudokuBoard(1);
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                sudokuBoard.set(i,j,1);
            }
        }
        SudokuBoard sudokuBoard1=new SudokuBoard(1);


        assertTrue(sudokuBoard.equals(sudokuBoard));

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                sudokuBoard1.set(i,j,2);
            }
        }

        assertFalse(sudokuBoard.equals(sudokuBoard1));
    }
}