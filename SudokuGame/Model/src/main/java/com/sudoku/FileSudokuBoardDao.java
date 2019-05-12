package com.sudoku;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private String path;

    public FileSudokuBoardDao(final String path) {
        this.path = path;
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard sudokuBoard = null;
        try (FileInputStream fileReader = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileReader)) {
            sudokuBoard = (SudokuBoard) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return sudokuBoard;
    }

    @Override
    public void write(final SudokuBoard sudokuBoard) {
        try (FileOutputStream fileWriter = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileWriter)) {
            objectOutputStream.writeObject(sudokuBoard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finalize() {

    }
}
