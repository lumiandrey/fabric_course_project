package by.bsuir.ief.system.fabric.controller.table.user;

import by.bsuir.ief.system.fabric.controller.table.TableController;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.util.ListUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class UserTableController implements TableController<UserEntity> {

    private ObservableList<UserEntity> entityObservableList = FXCollections.observableArrayList();
    private Stage primaryStage;
    private boolean isMultiSelect;
    private TableController.CallbackSelected<UserEntity> callbackSelected = null;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private TableView<UserEntity> userTableView;

    @FXML
    private TableColumn<UserEntity, String> loginUserColumn;

    @FXML
    private TableColumn<UserEntity, String> statusUserColumn;

    @FXML
    private TableColumn<UserEntity, String> nameRoleUserColumn;

    private void initTable(){

        userTableView.getItems().clear();

        loginUserColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getLogin()));
        statusUserColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty((cellData.getValue().getStatus() != 0?"не заблокирован":"заблокирован")));
        nameRoleUserColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getRoleApplication().getName()));

        if(isMultiSelect)
            userTableView.getSelectionModel().setSelectionMode(
                    SelectionMode.MULTIPLE
            );
        userTableView.getSelectionModel().selectedItemProperty().
                addListener(((observable, oldValue, newValue) -> {
                    if(callbackSelected != null) callbackSelected.onSelectItem(newValue);
                }));

        userTableView.setItems(entityObservableList);
    }

    @Override
    public UserEntity getSelect() {
        return userTableView.getSelectionModel().getSelectedItem();
    }
    @Override
    public List<UserEntity> getSelected() {
        return userTableView.getSelectionModel().getSelectedItems();
    }

    @Override
    public void setList(List<UserEntity> list) {
        entityObservableList = ListUtil.parseListToObserv(entityObservableList, list);

        initTable();

        if(entityObservableList.size() > 0){

            closeIndicatorProgress();
        } else {

            displayIndicatorProgress();
        }
    }

    @Override
    public void setMultiSelect(boolean multiSelect) {
        isMultiSelect = multiSelect;
    }

    @Override
    public void setCallbackSelect(CallbackSelected<UserEntity> callbackSelect) {
        this.callbackSelected = callbackSelect;
    }

    @Override
    public void initialize() {

       /* rootPane.setBackground(Style.getBackground());
        userTableView.setBackground(Style.getBackground());
*/
        displayIndicatorProgress();
    }

    @Override
    public void displayIndicatorProgress() {
        userTableView.setVisible(false);
        progressIndicator.setVisible(true);
    }

    @Override
    public void closeIndicatorProgress() {
        userTableView.setVisible(true);
        progressIndicator.setVisible(false);
    }

    @Override
    public Pane getLayout() {
        return rootPane;
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {

    }
}
