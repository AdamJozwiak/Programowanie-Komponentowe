package com.sudokuGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private StackPane stackPane;

    @FXML
    public void initialize() {
        menuScreen();
    }

    ///////////////////////////////////Zaladowanie ekranu menu z pliku fxml/////////////////////////////////////////////

    public void menuScreen() {
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/MenuScreen.fxml"));
        loader.setResources(bundle);
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MenuController menuController = loader.getController();
        menuController.setMainController(this);
        setScreen(pane);
    }

    ///////////////////////////////////////////////Zmiana ekranu////////////////////////////////////////////////////////

    public void setScreen(final Pane pane) {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(pane);
    }
}
