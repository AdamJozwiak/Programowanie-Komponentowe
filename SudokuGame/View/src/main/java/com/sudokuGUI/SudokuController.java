package com.sudokuGUI;

import com.myExceptions.CellErrorException;
import com.myExceptions.EmptySpaceException;
import com.myExceptions.ForbiddenCharException;
import com.myExceptions.TooLongException;
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
import myExceptions.DataException;
import myExceptions.FileException;
import sun.rmi.runtime.Log;

import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class SudokuController {

    private MainController mainController;
    private SudokuBoard sudokuBoard = new SudokuBoard(0);
    private ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");

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
        Logger.getLogger("Sudoku").warning(bundle.getString("log.sudoku.correctness"));
        TextField[][] textFields = new TextField[9][9];

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

        /////////////////////////////////Wylapywanie wyjatkow i wypisywanie logow///////////////////////////////////////
       try {
           check(textFields);

       } catch (EmptySpaceException | ForbiddenCharException | TooLongException e) {
           Logger.getLogger("Sudoku").severe(e.getMessage());
           return false;

       } catch (CellErrorException e) {
           e.printStackTrace();
           return false;
       }

        ////////////////////////Metoda sprawdzajaca poprawnosc za pomoca metody z SudokuBoard///////////////////////////
        if (sudokuBoard.checkBoard()) {
            Logger.getLogger("Sudoku").info(bundle.getString("log.correct.sudoku"));
            winnerBox();
            return true;
        }

        Logger.getLogger("Sudoku").info(bundle.getString("log.wrong.sudoku"));
        loserBox();
        return false;
    }


    //////////////////////////////////////Metoda sprawdzajaca i throwujaca wyjatki//////////////////////////////////////
    public void check(TextField[][] textFields) throws CellErrorException{
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (textFields[i][j].getText().equals("")) {
                    textFields[i][j].setText("0");
                }

    ////////////////////////////////Sprawdzanie czy w pola sa wpisane odpowiednie wartosci//////////////////////////////
                try {
                    if (textFields[i][j].getLength() > 1) {
                        errorBox(bundle.getString("cell.error.title"), bundle.getString("cell.error.range"));
                        throw new TooLongException(bundle.getString("cell.error.range"), new Exception());
                    }
                    //Jesli tak, to do SudokuBoarda jest wpisywana wartosc z TextFielda
                    sudokuBoard.set(i, j, Integer.parseInt(textFields[i][j].getText()));
                } catch (NumberFormatException e) {
                    errorBox(bundle.getString("cell.error.title"), bundle.getString("cell.error.forbidden.char"));
                    throw new ForbiddenCharException(bundle.getString("cell.error.forbidden.char"), new NumberFormatException());
                }

                if (sudokuBoard.get(i, j) == 0) {
                    errorBox(bundle.getString("cell.error.title"), bundle.getString("cell.error.empty.cell"));
                    textFields[i][j].setText("");
                    throw new EmptySpaceException(bundle.getString("cell.error.empty.cell"), new NullPointerException());
                }
            }
        }
    }

    @FXML
    public void exit() {
        Logger.getLogger("Sudoku").info(bundle.getString("log.incorrect.save"));
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
        Logger.getLogger("Sudoku").info(bundle.getString("log.saving"));
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
                try {
                    this.sudokuBoard.set(i, j, Integer.parseInt(textFields[i][j].getText()));
                } catch (NumberFormatException e) {
                    errorBox(bundle.getString("cell.error.title"), bundle.getString("cell.error.forbidden.char"));
                    return;
                }
            }
        }
        Logger.getLogger("Sudoku").info(bundle.getString("log.correct.save"));
        if(SqlAlertBox())
        {
            Logger.getLogger("Sudoku").info(bundle.getString("log.file.saved"));
            Platform.exit();
        }
        Logger.getLogger("Sudoku").warning(bundle.getString("log.incorrect.save"));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (textFields[i][j].getText().equals("0")) {
                    textFields[i][j].setText("");
                }
            }
        }
    }

    //////////////////////////////////////////////////////AlertBoxy/////////////////////////////////////////////////////

    public boolean alertBox() {
        TextInputDialog alert = new TextInputDialog();
        alert.setTitle(bundle.getString("alertbox.title"));
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("alertbox.path"));
        alert.getDialogPane().setPrefSize(380, 120);

        Optional<String> result = alert.showAndWait();
        if (result.isPresent()) {
            FileSudokuBoardDao fileSudokuBoardDao = new FileSudokuBoardDao(result.get());
            try{
                fileSudokuBoardDao.write(sudokuBoard);
            } catch (FileException e) {
                e.getMessage();
            }
            return true;
        }
        return false;
    }

    public boolean SqlAlertBox() {
        Alert sql = new Alert(Alert.AlertType.INFORMATION);
        sql.setTitle(bundle.getString("alertbox.title"));
        sql.setHeaderText("Zapisano do bazy danych");

        JdbcSudokuBoardDao jdbc = new JdbcSudokuBoardDao("Sudoku1");
        try {
            jdbc.write(sudokuBoard);
            return true;
        } catch (DataException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void errorBox(final String titleTxt, final String headerTxt) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle(titleTxt);
        error.setHeaderText(headerTxt);
        error.showAndWait();
    }

    public void winnerBox() {
        Alert win = new Alert(Alert.AlertType.CONFIRMATION);
        win.setTitle(bundle.getString("win.title"));
        win.setHeaderText(bundle.getString("win.alert"));
        win.showAndWait();
    }

    public void loserBox() {
        Alert lose = new Alert(Alert.AlertType.CONFIRMATION);
        lose.setTitle(bundle.getString("lose.title"));
        lose.setHeaderText(bundle.getString("lose.alert"));
        lose.showAndWait();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setMainController(final MainController mainController) {
        this.mainController = mainController;
    }
}
