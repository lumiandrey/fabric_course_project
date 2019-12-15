package by.bsuir.ief.system.fabric.controller.table;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class ChoiceTableController<T> implements TableController<T> {

    @FXML
    private BorderPane pane;

    private Stage stage;

    private boolean isOk = false;
    private TableController<T> controller;
    private boolean isMulti = false;

    @Override
    public void initialize() {

    }

    @Override
    public T getSelect() {
        return (controller != null ? controller.getSelect() : null);
    }

    @Override
    public List<T> getSelected() {
        return (controller != null ? controller.getSelected() : null);
    }

    @Override
    public void setList(List<T> list) {

        if(controller != null)
            controller.setList(list);
    }

    public void setMultiSelect(boolean isMulti){

        if(controller != null){
            this.isMulti = isMulti;
            controller.setMultiSelect(isMulti);
        }
    }

    @Override
    public void setCallbackSelect(CallbackSelected<T> callbackSelect) {

    }

    @Override
    public void displayIndicatorProgress() {
        if(controller != null){
            controller.displayIndicatorProgress();
        }
    }

    @Override
    public void closeIndicatorProgress() {
        if(controller != null){
            controller.closeIndicatorProgress();
        }
    }

    @Override
    public Pane getLayout() {
        return pane;
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public boolean isOk() {
        return isOk;
    }

    public boolean isValid() {
        return (isMulti && controller.getSelected() != null
                && controller.getSelected().size() > 0) ||
                (!isMulti && controller.getSelect() != null);
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

    public void setController(TableController<T> controller) {
        this.controller = controller;

        pane.setCenter(controller.getLayout());
    }
}
