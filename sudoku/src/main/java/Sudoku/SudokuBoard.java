package Sudoku;

import java.util.Random;

public class SudokuBoard {
    public int[][] board;

    public void firstFill(int n) {
        board = new int[9][9];
        Random generator = new Random();
        int k, l;
        for (int i = 0; i < n; i++) {
            do {
                k = generator.nextInt(9) + 1;
                l = generator.nextInt(9) + 1;
            } while (board[k][l] != 0);

            board[k][l] = generator.nextInt(9) + 1;
        }
    }

    public boolean checkElement(int k, int l) {

        for (int i = 0; i < 9; i++) {
            if (i != k && board[k][l] == board[i][l]) {
                return false;
            }
            if (i != l && board[k][l] == board[k][i]) {
                return false;
            }
        }
        int pom1 = (k / 3) * 3;
        int pom2 = (l / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[k][l] == board[pom1 + i][pom2 + j] && (pom1 + i != k || pom2 + j != l)) {
                    return false;
                }
            }
        }
        return true;

    }

    public void fillBoard(int k, int l) {

    }

    public static void main(String args[]) {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.board = new int[9][9];
        sudokuBoard.board[0][0] = 1;
        sudokuBoard.board[0][1] = 1;
        sudokuBoard.board[3][1] = 8;
        System.out.println(sudokuBoard.checkElement(0, 0));
        System.out.println(sudokuBoard.checkElement(3, 1));
    }
}
