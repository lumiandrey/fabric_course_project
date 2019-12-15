package by.bsuir.ief.system.fabric.controller;

import by.bsuir.ief.system.fabric.controller.form.FormController;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditControllerImpl<T> implements EditController<T> {

    @FXML
    private BorderPane pane;

    private boolean isOk = false;
    private FormController<T> formController;
    private Stage stage;


    @FXML
    public void initialize() {

    }

    @Override
    public void displayIndicatorProgress() {

    }

    @Override
    public void closeIndicatorProgress() {

    }

    public void setFormController(FormController<T> formController) {

        this.formController = formController;
        pane.setCenter(formController.getLayout());
    }

    public void setEntity(T entity) {

        formController.setEntity(entity);
    }

    public boolean isValid() {
        return formController.isValid();
    }

    public boolean isOk() {
        return isOk;
    }

    public T getEntity() {
        return formController.getEntity();
    }

    public Pane getLayout() {
        return pane;
    }

    public void setPrimaryStage(Stage primaryStage) {

        this.stage = primaryStage;
    }

    @FXML
    private void handleOk(){

        if(isValid()){
            isOk = true;
            stage.close();
        }
    }

    @FXML
    private void handleCancel(){
        stage.close();
    }

    @Override
    public void init() {
        if(formController != null){
            formController.initEntity();
        }
    }
}
