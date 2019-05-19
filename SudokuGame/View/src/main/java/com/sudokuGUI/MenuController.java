package com.sudokuGUI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import com.sudoku.*;
import java.io.IOException;

public class MenuController {

    private MainController mainController;
    private static SudokuBoard sudokuBoard;
    private static SudokuBoard copy;

    //Przyciski
    @FXML
    public void latwy() {
        Latwy latwy = new Latwy();
        copy = latwy.latwy();
        sudokuBoard = latwy.getSudokuBoard();
        loadSudoku();
    }

    @FXML
    public void sredni() {
        Sredni sredni = new Sredni();
        sudokuBoard = sredni.sredni();
        copy = sredni.getCopy();
        loadSudoku();
    }

    @FXML
    public void trudny() {
        Trudny trudny = new Trudny();
        sudokuBoard = trudny.trudny();
        copy = trudny.getCopy();
        loadSudoku();
    }

    @FXML
    public void exit() {
        Platform.exit();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void loadSudoku() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/SudokuScreen.fxml"));

        try {
            Pane pane = loader.load();
            mainController.setScreen(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SudokuController sudokuController = loader.getController();
        sudokuController.setMainController(mainController);
    }

    public static SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    public static SudokuBoard getCopy() {
        return copy;
    }
}
