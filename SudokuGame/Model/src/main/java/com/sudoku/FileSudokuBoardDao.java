package com.sudoku;

import myExceptions.FileException;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, Serializable {
    private String path;
    private FileInputStream fileReader;
    private FileOutputStream fileWriter;

    public FileSudokuBoardDao(final String path) {
        this.path = path;
    }

    @Override
    public SudokuBoard read() throws FileException{
        SudokuBoard sudokuBoard = null;
        try (FileInputStream  fileReader = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileReader)) {
            sudokuBoard = (SudokuBoard) objectInputStream.readObject();
            this.fileReader=fileReader;
        } catch (IOException | ClassNotFoundException e) {
            throw new FileException("Blad pliku", e);
        }
        return sudokuBoard;
    }

    @Override
    public void write(final SudokuBoard sudokuBoard) throws FileException{
        try (FileOutputStream fileWriter = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileWriter)) {
            this.fileWriter=fileWriter;
            objectOutputStream.writeObject(sudokuBoard);
        } catch (IOException e) {
            throw new FileException("Blad zapisu do pliku", e);
        }
    }

    @Override
    public void finalize() {
        try {
            fileReader.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
