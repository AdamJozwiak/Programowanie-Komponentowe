package com.sudokuGUI;

import com.sudoku.FileSudokuBoardDao;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AlertBoxController {

    private MainController mainController;
    private SudokuController sudokuController;

    @FXML
    private TextField text;

    public void saveFile() {
        FileSudokuBoardDao fileSudokuBoardDao = new FileSudokuBoardDao(text.getText());
        fileSudokuBoardDao.write(sudokuController.getSudokuBoard());
        Platform.exit();
    }

    public void setMainController(final MainController mainController) {
        this.mainController = mainController;
    }
}
