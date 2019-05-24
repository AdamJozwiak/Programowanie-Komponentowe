package com.sudokuGUI;

import com.sudoku.*;

public class PoziomTrudnosci {
    private SudokuBoard copy;

    public SudokuBoard wybierzPoziom(int n) {
        SudokuBoard sudokuBoard = generateBoard();

        ///////////////////////////////////Klonowanie dobrze wypelnionego Boarda////////////////////////////////////////

        copy = sudokuBoard.clone();

        //////////////////////////////////Usuwanie losowych wartości z SudokuBoard//////////////////////////////////////
        int w, k;
        for (int i = 0; i < n; i++) {
            do {
                w = sudokuBoard.rand() - 1;
                k = sudokuBoard.rand() - 1;
            } while (sudokuBoard.get(w, k) == 0);
            sudokuBoard.set(w, k, 0);
        }
        return sudokuBoard;
    }

    public SudokuBoard generateBoard() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(10);
        backtrackingSudokuSolver.solve(sudokuBoard);
        return sudokuBoard;
    }

    public SudokuBoard getCopy() {
        return copy;
    }

}
