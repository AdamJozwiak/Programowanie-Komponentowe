package com.sudoku;

public class SudokuBoardDaoFactory {

    public Dao getFileDao(final String path) {
        return new FileSudokuBoardDao(path);
    }

    public Dao getBaseDao(final String tableName)
    {
        return new JdbcSudokuBoardDao(tableName);
    }
}
