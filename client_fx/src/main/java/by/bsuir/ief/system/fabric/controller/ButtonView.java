package by.bsuir.ief.system.fabric.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ButtonView implements Controller {

    @FXML
    private AnchorPane rootPane;
    private OnClick onClick;

    @FXML
    private Button button;

    @FXML
    private void onClick(){

        if(onClick != null){
            onClick.onClick();
        }
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void displayIndicatorProgress() {

    }

    @Override
    public void closeIndicatorProgress() {

    }

    public void setText(String name){
        button.setText(name);
    }

    @Override
    public Pane getLayout() {
        return rootPane;
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {

    }

    public interface OnClick {

        public void onClick();
    }
}
