package by.bsuir.ief.system.fabric.controller.table.factory;

import by.bsuir.ief.system.fabric.controller.table.BaseTableController;
import by.bsuir.ief.system.fabric.model.entity.fabric.ConsumerEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingConstEntity;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class OutGoingConstTableController extends BaseTableController<OutGoingConstEntity> {

    private Stage primaryStage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<OutGoingConstEntity> tableView;

    @FXML
    private TableColumn<OutGoingConstEntity, String> nameColumn;

    @FXML
    private TableColumn<OutGoingConstEntity, Double> priceOutGoing;

    @Override
    protected TableView<OutGoingConstEntity> getTableView() {
        return tableView;
    }

    @Override
    protected void initColumn() {
        nameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));

        priceOutGoing.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCost()));

    }

    @Override
    public OutGoingConstEntity getSelect() {
        return tableView.getSelectionModel().getSelectedItem();
    }
    @Override
    public List<OutGoingConstEntity> getSelected() {
        return tableView.getSelectionModel().getSelectedItems();
    }

    @Override
    public Pane getLayout() {
        return rootPane;
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {

    }
}
