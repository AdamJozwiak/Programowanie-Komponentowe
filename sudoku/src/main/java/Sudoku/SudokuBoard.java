package Sudoku;

import java.util.Random;

public class SudokuBoard {
    public int[][] board;

    public int rand() {
        Random generator = new Random();
        return generator.nextInt(9) + 1;
    }

    public void firstFill(int n) {
        board = new int[9][9];
        int k, l;
        for (int i = 0; i < n; i++) {
            do {
                k = rand() - 1;
                l = rand() - 1;
            } while (board[k][l] != 0);

            board[k][l] = rand();
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

    public boolean fillBoard(int k, int l) {
        if (l == 9) {
            return true;
        }

        int k2 = k + 1;
        int l2 = l;

        if (k2 == 9)
        {
            k2 = 0;
            l2++;
        }

        if (board[k][l] == 0) {
            int pom = rand();
            int i = 0;

            do {
                do {
                    board[k][l] = pom;
                    pom = pom % 9 + 1;
                } while (!checkElement(k, l) && ++i != 9);

                if (i == 9 & !checkElement(k, l)) {
                    board[k][l] = 0;
                    return false;
                }
            } while (!fillBoard(k2, l2));
            return true;
        }
        return fillBoard(k2,l2);
    }

    public static void main(String args[]) {

        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.board = new int [9][9];
        //sudokuBoard.firstFill(10);
        sudokuBoard.board[0][3] = 7;
        sudokuBoard.board[0][5] = 4;
        sudokuBoard.board[0][7] = 9;
        sudokuBoard.board[0][8] = 3;
        sudokuBoard.board[1][1] = 3;
        sudokuBoard.board[1][2] = 9;
        sudokuBoard.board[1][3] = 5;
        sudokuBoard.board[1][4] = 1;
        sudokuBoard.board[1][8] = 8;
        sudokuBoard.board[2][3] = 8;
        sudokuBoard.board[2][4] = 3;
        sudokuBoard.board[2][7] = 6;
        sudokuBoard.board[3][0] = 7;
        sudokuBoard.board[3][1] = 8;
        sudokuBoard.board[3][2] = 1;
        sudokuBoard.board[4][7] = 5;
        sudokuBoard.board[6][1] = 5;
        sudokuBoard.board[6][2] = 3;
        sudokuBoard.board[6][7] = 8;
        sudokuBoard.board[7][0] = 6;
        sudokuBoard.board[7][1] = 5;
        sudokuBoard.board[7][5] = 5;
        sudokuBoard.board[7][6] = 4;
        sudokuBoard.board[7][7] = 7;
        sudokuBoard.fillBoard(0,0);

        for(int i= 0; i<9;i++)
        {
            for(int j= 0; j<9; j++)
            {
                System.out.print(sudokuBoard.board[i][j]);
            }
            System.out.println();
        }
    }
}
