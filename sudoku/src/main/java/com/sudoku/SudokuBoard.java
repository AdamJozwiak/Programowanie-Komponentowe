package com.sudoku;

import java.util.Random;


public class SudokuBoard {

    SudokuField[][] sudokuField;

    public SudokuBoard(int n) {
        sudokuField = new SudokuField[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuField[i][j] = new SudokuField();
            }
        }

        int w, k;
        for (int i = 0; i < n; i++) {
            do {
                w = rand() - 1;
                k = rand() - 1;
            } while (sudokuField[w][k].getFieldValue() != 0);

            do {
                sudokuField[w][k].setFieldValue(rand());
            } while (!checkBoard());
        }
    }

    public int rand() {
        Random generator = new Random();
        return generator.nextInt(9) + 1;
    }


    public int get(int w, int k) {
        return sudokuField[w][k].getFieldValue();
    }

    public void set(int w, int k, int value) {
        sudokuField[w][k].setFieldValue(value);
    }

    public boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            if (!getRow(i).verify()) {
                return false;
            }
            if (!getColumn(i).verify()) {
                return false;
            }
        }
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!getBox(i, j).verify()) {
                    return false;
                }
            }
        }
        return true;
    }

    public SudokuRow getRow(int y) {
        SudokuRow sudokuRow = new SudokuRow(sudokuField[y]);
        return sudokuRow;
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] column = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            column[i] = sudokuField[i][x];
        }
        SudokuColumn sudokuColumn = new SudokuColumn(column);
        return sudokuColumn;
    }

    public SudokuBox getBox(int x, int y) {
        SudokuField[] box = new SudokuField[9];
        int pom1 = (x / 3) * 3;
        int pom2 = (y / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                box[3 * i + j] = sudokuField[pom1 + i][pom2 + j];
            }
        }
        SudokuBox sudokuBox = new SudokuBox(box);
        return sudokuBox;
    }

    public static void main(final String[] args) {

        SudokuBoard sudokuBoard = new SudokuBoard(10);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudokuBoard.get(i, j) + " ");
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
                System.out.print(sudokuBoard.get(i, j) + " ");
            }
            System.out.println();

        }
    }
}