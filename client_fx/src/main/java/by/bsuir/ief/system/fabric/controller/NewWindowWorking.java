package by.bsuir.ief.system.fabric.controller;

import by.bsuir.ief.system.fabric.controller.form.FormController;
import by.bsuir.ief.system.fabric.controller.table.TableController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewWindowWorking implements Controller {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private BorderPane borderPane;

    private TableController tableController;
    private FormController formController;
    private BottomBarController bottomBarController;

    private Stage stage;

    @Override
    public void initialize() {

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

    @FXML
    private void close(){

        stage.close();
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void setTableController(TableController tableController) {
        this.tableController = tableController;

        borderPane.setCenter(tableController.getLayout());
    }

    public void setFormController(FormController formController) {
        this.formController = formController;

        borderPane.setLeft(formController.getLayout());
    }

    public void setBottomBarController(BottomBarController bottomBarController) {
        this.bottomBarController = bottomBarController;

        borderPane.setBottom(bottomBarController.getLayout());
    }
}
