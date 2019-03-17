package com.sudoku;

import java.util.Random;


public class SudokuBoard {
    private int[][] board;

    public int rand() {
        Random generator = new Random();
        return generator.nextInt(9) + 1;
    }

    public void firstFill(int n) {
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


        /*for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[w][k] == board[pom1 + i][pom2 + j] && (pom1 + i != w || pom2 + j != k)) {
                    return false;
                }
            }
        }*/
        return true;
    }

    public boolean fillBoard(int w, int k) {
        if (w == 9) {
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

    }

    public int[][] getBoard() {
        return board;
    }


    public static void main(final String[] args) {

        SudokuBoard sudokuBoard = new SudokuBoard();
        //udokuBoard.board = new int [9][9];
        sudokuBoard.firstFill(3);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudokuBoard.board[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();


        sudokuBoard.fillBoard(0, 0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudokuBoard.board[i][j] + " ");
            }
            System.out.println();
        }

    }
}
