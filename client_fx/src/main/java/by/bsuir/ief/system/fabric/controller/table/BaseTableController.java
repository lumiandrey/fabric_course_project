package by.bsuir.ief.system.fabric.controller.table;

import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;
import by.bsuir.ief.system.fabric.util.ListUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.lang.ref.WeakReference;
import java.util.List;

abstract public class BaseTableController<T> implements TableController<T> {

    private WeakReference<Stage> primaryStage;

    protected ObservableList<T> entityObservableList = FXCollections.observableArrayList();

    protected boolean isMultiSelect;

    protected CallbackSelected<T> callbackSelected = null;

    @FXML
    protected ProgressIndicator progressIndicator;

    abstract protected TableView<T> getTableView();

    abstract protected void initColumn();

    @Override
    public void setMultiSelect(boolean multiSelect) {
        isMultiSelect = multiSelect;
    }

    @Override
    public void initialize() {

        displayIndicatorProgress();
    }

    protected void initTable(){

        TableView<T> tableView = getTableView();

        tableView.getItems().clear();

        initColumn();

        if(isMultiSelect)
            tableView.getSelectionModel().setSelectionMode(
                    SelectionMode.MULTIPLE
            );
        tableView.getSelectionModel().selectedItemProperty().
                addListener(((observable, oldValue, newValue) -> {
                    if(callbackSelected != null) callbackSelected.onSelectItem(newValue);
                }));

        tableView.setItems(entityObservableList);
    }


    @Override
    public void setCallbackSelect(CallbackSelected<T> callbackSelect) {
        this.callbackSelected = callbackSelect;
    }

    @Override
    public void setList(List<T> list) {
        entityObservableList = ListUtil.parseListToObserv(entityObservableList, list);

        initTable();

        if(entityObservableList.size() > 0){

            closeIndicatorProgress();
        } else {

            displayIndicatorProgress();
        }
    }

    @Override
    public void displayIndicatorProgress() {
        getTableView().setVisible(false);
        progressIndicator.setVisible(true);
    }

    @Override
    public void closeIndicatorProgress() {
        getTableView().setVisible(true);
        progressIndicator.setVisible(false);
    }

    @Override
    public T getSelect() {
        return getTableView().getSelectionModel().getSelectedItem();
    }

    @Override
    public List<T> getSelected() {
        return getTableView().getSelectionModel().getSelectedItems();
    }


    @Override
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = new WeakReference<>(primaryStage);
    }
}
