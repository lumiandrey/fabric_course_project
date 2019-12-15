package by.bsuir.ief.system.fabric.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public interface Controller {

    @FXML
    void initialize();

    void displayIndicatorProgress();

    void closeIndicatorProgress();

    Pane getLayout();

    void setPrimaryStage(Stage primaryStage);

}
