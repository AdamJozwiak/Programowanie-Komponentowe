package com.sudokuGUI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import com.sudoku.*;
import myExceptions.DataException;
import myExceptions.FileException;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuController {

    private MainController mainController;
    private static SudokuBoard sudokuBoard;
    private static SudokuBoard copy;
    private Locale pl = new Locale("pl");
    private Locale eng = new Locale("en");

    @FXML
    private javafx.scene.control.TextField textID;

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
    public void read() {
        /*FileSudokuBoardDao fileSudokuBoardDao = new FileSudokuBoardDao(this.textID.getText());
        try{
            sudokuBoard = fileSudokuBoardDao.read();
        } catch (FileException e){
            e.getMessage();
        }*/

        JdbcSudokuBoardDao jdbc = new JdbcSudokuBoardDao("Sudoku1");
        sudokuBoard = jdbc.read();
        loadSudoku();
    }

    @FXML
    public void langChange() {
        if (Locale.getDefault().equals(eng)) {
            Locale.setDefault(pl);
            mainController.initialize();
            return;
        }

        if (Locale.getDefault().equals(pl)) {
            Locale.setDefault(eng);
            mainController.initialize();
        }
    }

    @FXML
    public void credits() {
        ResourceBundle bundle = ResourceBundle.getBundle("com.resources.Resources");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("title"));
        alert.setHeaderText(bundle.getString("authors"));
        alert.showAndWait();
    }

    @FXML
    public void exit() {
        Platform.exit();
    }

    ////////////////////////////////////////////Zaladowanie ekranu gry//////////////////////////////////////////////////

    public void loadSudoku() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.messages");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/SudokuScreen.fxml"));
        loader.setResources(resourceBundle);
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

    public void setMainController(final MainController mainController) {
        this.mainController = mainController;
    }
}
