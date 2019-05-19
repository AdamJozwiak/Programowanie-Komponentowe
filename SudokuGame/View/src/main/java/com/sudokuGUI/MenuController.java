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

    ///////////////////////////////////////////Wybor poziomu trudnosci//////////////////////////////////////////////////

    @FXML
    public void latwy() {
        Latwy latwy = new Latwy();
        sudokuBoard = latwy.latwy();
        copy = latwy.getCopy();
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

    ////////////////////////////////////////////Zaladowanie ekranu gry//////////////////////////////////////////////////

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

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
