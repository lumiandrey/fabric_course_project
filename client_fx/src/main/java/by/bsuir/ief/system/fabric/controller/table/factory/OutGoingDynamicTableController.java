package by.bsuir.ief.system.fabric.controller.table.factory;

import by.bsuir.ief.system.fabric.controller.table.BaseTableController;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingConstEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingDynamicEntity;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class OutGoingDynamicTableController extends BaseTableController<OutGoingDynamicEntity> {

    private Stage primaryStage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<OutGoingDynamicEntity> tableView;

    @FXML
    private TableColumn<OutGoingDynamicEntity, String> nameColumn;

    @FXML
    private TableColumn<OutGoingDynamicEntity, Double> priceOutGoing;

    @Override
    protected TableView<OutGoingDynamicEntity> getTableView() {
        return tableView;
    }

    @Override
    protected void initColumn() {
        nameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));

        priceOutGoing.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCost()));

    }

    @Override
    public OutGoingDynamicEntity getSelect() {
        return tableView.getSelectionModel().getSelectedItem();
    }
    @Override
    public List<OutGoingDynamicEntity> getSelected() {
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
