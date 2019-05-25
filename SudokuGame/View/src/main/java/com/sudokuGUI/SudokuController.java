package com.sudokuGUI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import com.sudoku.*;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Optional;

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

                ////////////////////////////Sprawdzanie czy w pola sa wpisane odpowiednie wartosci///////////////////////////////
                try {
                    if (textFields[i][j].getLength() > 1) {
                        errorBox("Błąd komorki", "Zakres liczb do wpisania to 0-9");
                    }
                    //Jesli tak, to do SudokuBoarda jest wpisywana wartosc z TextFielda
                    sudokuBoard.set(i, j, Integer.parseInt(textFields[i][j].getText()));
                } catch (NumberFormatException e) {
                    errorBox("Błąd komorki", "Do komorek nalezy wpisywac tylko liczby calkowite");
                }

                if (sudokuBoard.get(i, j) == 0) {
                    errorBox("Błąd komórki", "Pozostawiłeś puste pola");
                    return false;
                }
            }
        }

        if (sudokuBoard.checkBoard()) {
            winnerBox();
            return true;
        }

        loserBox();
        return false;
    }

    @FXML
    public void exit() {
        Platform.exit();
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
    @FXML
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

        alertBox();
        Platform.exit();
    }

    //////////////////////////////////////////////////////AlertBoxy/////////////////////////////////////////////////////

    public void alertBox() {
        TextInputDialog alert = new TextInputDialog();
        alert.setTitle("Save Sudoku");
        alert.setHeaderText(null);
        alert.setContentText("Podaj ścieżkę do zapisu");
        alert.getDialogPane().setPrefSize(380, 120);

        Optional<String> result = alert.showAndWait();
        if (result.isPresent()) {
            FileSudokuBoardDao fileSudokuBoardDao = new FileSudokuBoardDao(result.get());
            fileSudokuBoardDao.write(sudokuBoard);
        }
    }

    public void errorBox(String titleTxt, String headerTxt) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle(titleTxt);
        error.setHeaderText(headerTxt);
        error.showAndWait();
    }

    public void winnerBox() {
        Alert win = new Alert(Alert.AlertType.CONFIRMATION);
        win.setTitle("Wygraleś!");
        win.setHeaderText("Gratulacje, poprawnie rozwiązałeś Sudoku!");
        win.showAndWait();
    }

    public void loserBox() {
        Alert lose = new Alert(Alert.AlertType.CONFIRMATION);
        lose.setTitle("Przegrałeś :(");
        lose.setHeaderText("We'll get 'em next time!");
        lose.showAndWait();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setMainController(final MainController mainController) {
        this.mainController = mainController;
    }
}
