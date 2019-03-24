package com.sudoku;

import java.util.Random;


public class SudokuBoard {
    private int[][] board;

    public SudokuBoard(int n) {
        board = new int[9][9];
        int w, k;
        for (int i = 0; i < n; i++) {
            do {
                w = rand() - 1;
                k = rand() - 1;
            } while (board[w][k] != 0);

            do {
                board[w][k] = rand();
            } while (!checkElement(w, k, board[w][k]));
        }
    }

    public int rand() {
        Random generator = new Random();
        return generator.nextInt(9) + 1;
    }


    public boolean checkElement(int w, int k, int num) {

        int pom1 = (w / 3) * 3;
        int pom2 = (k / 3) * 3;
        for (int i = 0; i < 9; i++) {
            if (i != w && num == board[i][k]) {
                return false;
            }
            if (i != k && num == board[w][i]) {
                return false;
            }
            if (num == board[pom1 + i / 3][pom2 + i % 3] && (pom1 + i / 3 != w || pom2 + i % 3 != k)) {
                return false;
            }
        }

        return true;
        /*for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[w][k] == board[pom1 + i][pom2 + j] && (pom1 + i != w || pom2 + j != k)) {
                    return false;
                }
            }
        }*/
    }

    public int[][] getBoard() {
        int[][] board2 = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board2[i][j] = board[i][j];
            }
        }
        return board2;
    }

    public void setBoard(int w, int k, int value) {
        board[w][k] = value;
    }

    public boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!checkElement(i, j, board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    /*public void firstFill(int n) {
        board = new int[9][9];
        int w, k;
        for (int i = 0; i < n; i++) {
            do {
                w = rand() - 1;
                k = rand() - 1;
            } while (board[w][k] != 0);
            do {
                board[w][k] = rand();
            } while (!checkElement(w, k, board[w][k]));
        }
    }*/

    /*public boolean fillBoard(int w, int k) {
            k++;
            w = 0;
        }
        if (k == 9) {
            return true;
        }
        if (board[w][k] != 0) {
            return fillBoard(w + 1, k);
        }
        for (int num = 1; num <= 9; num++) {
            if (checkElement(w, k, num)) {
                board[w][k] = num;
                if (fillBoard(w + 1, k)) {
                    return true;
                }
            }
            board[w][k] = 0;
        }
        return false;
    }*/


    public static void main(final String[] args) {

        SudokuBoard sudokuBoard = new SudokuBoard(10);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudokuBoard.getBoard()[i][j] + " ");
            }
            System.out.println();

        }

        System.out.println();
        System.out.println();
        System.out.println();

        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        backtrackingSudokuSolver.solve(sudokuBoard);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudokuBoard.getBoard()[i][j] + " ");
            }
            System.out.println();

        }
    }
}