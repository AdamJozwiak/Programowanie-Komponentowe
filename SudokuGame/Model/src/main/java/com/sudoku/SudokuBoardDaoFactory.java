package com.sudoku;

public class SudokuBoardDaoFactory {

    public Dao getFileDao(final String path) {
        return new FileSudokuBoardDao(path);
    }
}
