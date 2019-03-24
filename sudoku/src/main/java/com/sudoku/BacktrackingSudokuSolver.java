package com.sudoku;

import java.util.Arrays;

public class BacktrackingSudokuSolver implements SudokuSolver {
    public boolean solve(final SudokuBoard sudokuBoard) {

        /*if (w == 9) {
            k++;
            w = 0;
        }
        if (k == 9) {
            return true;
        }*/

        int w = -1, k = -1;

        for (int i = 8; i >= 0; i--) {
            for (int j = 8; j >= 0; j--) {
                if (sudokuBoard.getBoard()[i][j] == 0) {
                    w = i;
                    k = j;
                }
            }
        }
        if (w == -1) {
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            if (sudokuBoard.checkElement(w, k, num)) {
                sudokuBoard.setBoard(w, k, num);
                if (solve(sudokuBoard)) {
                    return true;
                }
            }
            sudokuBoard.setBoard(w, k, 0);
        }
        return false;
    }
}
