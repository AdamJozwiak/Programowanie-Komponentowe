package com.sudokuGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class MenuController {

    private MainController mainController;

    @FXML
    public void latwy(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/SudokuScreen.fxml"));
        Pane pane = null;
        
        try{
            pane = loader.load();
        }catch (IOException e) {
            e.printStackTrace();
        }
        mainController.setScreen(pane);
    }

    @FXML
    public void sredni(){

    }

    @FXML
    public void trudny(){

    }

    @FXML
    public void exit(){

    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
