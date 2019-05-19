package com.sudoku;

import java.util.Arrays;

public class BacktrackingSudokuSolver implements SudokuSolver {
    public boolean solve(final SudokuBoard sudokuBoard) {
        int w = -1, k = -1;

        for (int i = 8; i >= 0; i--) {
            for (int j = 8; j >= 0; j--) {
                if (sudokuBoard.get(i, j) == 0) {
                    w = i;
                    k = j;
                }
            }
        }
        if (w == -1) {
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            sudokuBoard.set(w, k, num);
            if (sudokuBoard.checkBoard()) {
                if (solve(sudokuBoard)) {
                    return true;
                }
            }
            sudokuBoard.set(w, k, 0);
        }
        return false;
    }
}
