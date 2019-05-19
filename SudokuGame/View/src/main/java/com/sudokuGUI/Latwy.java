package com.sudokuGUI;

import com.sudoku.*;

public class Latwy {

    private SudokuBoard sudokuBoard;
    private SudokuBoard copy;

    public SudokuBoard latwy(){
        sudokuBoard = generateBoard();

        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                System.out.print(sudokuBoard.get(i, j));
            }
            System.out.println();
        }

        try {
            copy = (SudokuBoard) sudokuBoard.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        int w, k;
        for (int i = 0; i < 1; i++)
        {
            do {
                w = copy.rand() - 1;
                k = copy.rand() - 1;
            } while (copy.get(w, k) == 0);
            copy.set(w, k, 0);
        }

        System.out.println();

        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                System.out.print(sudokuBoard.get(i, j));
            }
            System.out.println();
        }

        return copy;
    }

    public SudokuBoard generateBoard(){
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(10);
        backtrackingSudokuSolver.solve(sudokuBoard);
        return sudokuBoard;
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }
}
