package by.bsuir.ief.system.fabric.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateEditDeleteBottomController implements Controller {

    @FXML
    private AnchorPane pane;

    private CallbackBottomBarManipulation callback;

    private boolean isCreateVisible = true;
    private boolean isUpdateVisible = true;
    private boolean isDeleteVisible = true;

    @FXML
    private Button create;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @Override
    public void initialize() {
        //pane.setBackground(Style.getBackground());
    }

    @Override
    public void displayIndicatorProgress() {

    }

    @Override
    public void closeIndicatorProgress() {

    }

    @Override
    public Pane getLayout() {
        return pane;
    }

    @FXML
    private void handleCreate(){
        if(callback != null)
            callback.onCreate();
    }

    @FXML
    private void handleEdit(){
        if(callback != null)
            callback.onEdit();
    }

    @FXML
    private void handleDelete(){
        if(callback != null)
            callback.onDelete();
    }

    public void setCreateVisible(boolean createVisible) {
        isCreateVisible = createVisible;

        create.setVisible(isCreateVisible);
    }

    public void setUpdateVisible(boolean updateVisible) {
        isUpdateVisible = updateVisible;

        update.setVisible(isUpdateVisible);
    }

    public void setDeleteVisible(boolean deleteVisible) {
        isDeleteVisible = deleteVisible;

        delete.setVisible(isUpdateVisible);
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {

    }

    public void setCallback(CallbackBottomBarManipulation callback) {
        this.callback = callback;
    }

    public interface CallbackBottomBarManipulation {
        void onCreate();
        void onEdit();
        void onDelete();
    }
}
