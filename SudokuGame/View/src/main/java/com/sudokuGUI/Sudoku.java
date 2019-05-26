package com.sudokuGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.ResourceBundle;

public class Sudoku extends Application {

    public static void main(final String[] args) {
        launch(args);
    }


    @Override
    public void start(final Stage primaryStage) throws Exception {
        Locale.setDefault(new Locale("pl"));
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/MainScreen.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        loader.setResources(bundle);
        StackPane stackPane = loader.load();
        Scene scene = new Scene(stackPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(bundle.getString("title.application"));
        primaryStage.show();
    }
}
