package by.bsuir.ief.system.fabric.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BottomBarController implements Controller {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane searchPane;

    @FXML
    private AnchorPane adminPane;

    @Override
    public void initialize() {

       /* rootPane.setBackground(Style.getBackground());
        adminPane.setBackground(Style.getBackground());*/
    }

    @Override
    public void displayIndicatorProgress() {

    }

    @Override
    public void closeIndicatorProgress() {

    }

    @Override
    public Pane getLayout() {
        return rootPane;
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {

    }

    public void setSearchPane(Pane pane){
        searchPane.getChildren().add(pane);
    }

    public void setAdminPane(Pane pane){
        adminPane.getChildren().add(pane);
    }
}
