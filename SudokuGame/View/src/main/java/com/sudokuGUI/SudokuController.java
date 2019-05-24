package com.sudokuGUI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import com.sudoku.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;

public class SudokuController {

    private MainController mainController;
    private SudokuBoard sudokuBoard = new SudokuBoard(0);

    @FXML
    private GridPane gridPane;

    /////////////////////////////////////////////Uzupelnianie Grida/////////////////////////////////////////////////////
    @FXML
    public void initialize() {
        SudokuBoard sudokuBoard = MenuController.getSudokuBoard();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField(Integer.toString(sudokuBoard.get(i, j)));
                textField.setAlignment(Pos.CENTER);
                textField.setPrefSize(55, 57);
                textField.setFont(Font.font("Courier", FontWeight.BOLD, 18));

                if (textField.getText().equals("0")) {
                    textField.setText("");
                }

                /////////////////////////////////////////////Dodawanie do Grida/////////////////////////////////////////////////////
                this.gridPane.add(textField, i, j);
            }
        }
    }

    @FXML
    public void menu() {
        mainController.menuScreen();
    }

    ///////////////////////////////Sprawdzanie, czy wypelnione sudoku jest poprawne/////////////////////////////////////
    @FXML
    public boolean solve() {
        TextField[][] textFields = new TextField[9][9];
        SudokuBoard sudokuBoard = new SudokuBoard(0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textFields[i][j] = getCell(i, j, gridPane);
            }
        }

        ///////////////////////////////Metoda sprawdzajaca poprawnosc za pomoca kopii///////////////////////////////////

        /*for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (MenuController.getCopy().get(i, j) != Integer.parseInt(textFields[i][j].getText())) {
                    System.out.println("Przegrales");
                    return false;
                }
            }
        }
        System.out.println("Wygrales");
        return true;*/

        ////////////////////////Metoda sprawdzajaca poprawnosc za pomoca metody z SudokuBoard///////////////////////////

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (textFields[i][j].getText().equals("")) {
                    textFields[i][j].setText("0");
                }
                sudokuBoard.set(i, j, Integer.parseInt(textFields[i][j].getText()));

                if (sudokuBoard.get(i, j) == 0) {
                    System.out.println("Przegrales");
                    return false;
                }
            }
        }

        if (sudokuBoard.checkBoard()) {
            System.out.println("Wygrales");
            return true;
        }

        System.out.println("Przegrales");
        return false;
    }

    @FXML
    public void exit() {
        Platform.exit();
    }

    public void setMainController(final MainController mainController) {
        this.mainController = mainController;
    }

    //////////////////////////////////Wyciąganie pojedynczych TextFieldow z GridPane////////////////////////////////////
    public TextField getCell(int col, int row, final GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null) {
                if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                    return (TextField) node;
                }
            }
        }
        return null;
    }

    ////////////////////////////////////////////Zapisywanie do pliku////////////////////////////////////////////////////
    public void save() {
        TextField[][] textFields = new TextField[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textFields[i][j] = getCell(i, j, gridPane);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (textFields[i][j].getText().equals("")) {
                    textFields[i][j].setText("0");
                }
                this.sudokuBoard.set(i, j, Integer.parseInt(textFields[i][j].getText()));
            }
        }

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/AlertBox.fxml"));

        try {
            Pane pane = loader.load();
            mainController.setScreen(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AlertBoxController alertBoxController = loader.getController();
        alertBoxController.setMainController(mainController);
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }
}
