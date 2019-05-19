package com.sudokuGUI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import com.sudoku.*;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SudokuController {

    private MainController mainController;

    @FXML
    private GridPane gridPane;

    //Uzupelnianie Grida
    @FXML
    public void initialize(){
        SudokuBoard sudokuBoard = MenuController.getCopy();

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++)
            {
                TextField textField = new TextField(Integer.toString(sudokuBoard.get(i,j)));
                textField.setAlignment(Pos.CENTER);
                textField.setPrefSize(55,57);
                textField.setFont(Font.font("Courier", FontWeight.BOLD, 18));

                if(textField.getText().equals("0"))
                {
                    textField.setText("");
                }

                this.gridPane.add(textField, i, j);
            }
        }
    }

    @FXML
    public void menu() {
        mainController.menuScreen();
    }

    @FXML
    public boolean solve() {
        TextField[][] textFields = new TextField[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textFields[i][j] = getCell(i, j, gridPane);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                System.out.println(MenuController.getCopy().get(i, j));
                System.out.println(Integer.parseInt(textFields[i][j].getText()));
                System.out.println();

                if(MenuController.getSudokuBoard().get(i, j) != Integer.parseInt(textFields[i][j].getText()))
                {
                    System.out.println("Przegrales");
                    return false;
                }
            }
        }
        System.out.println("Wygrales");
        return true;
    }

    @FXML
    public void exit() {
        Platform.exit();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public TextField getCell(int col, int row, GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if(GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null) {
                if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                    return (TextField) node;
                }
            }
        }
        return null;
    }
}
