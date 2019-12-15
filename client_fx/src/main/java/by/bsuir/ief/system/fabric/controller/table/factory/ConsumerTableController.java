package by.bsuir.ief.system.fabric.controller.table.factory;

import by.bsuir.ief.system.fabric.controller.table.BaseTableController;
import by.bsuir.ief.system.fabric.model.entity.fabric.ConsumerEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class ConsumerTableController extends BaseTableController<ConsumerEntity> {

    private Stage primaryStage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<ConsumerEntity> tableView;

    @FXML
    private TableColumn<ConsumerEntity, String> nameProducerColumn;

    @Override
    protected TableView<ConsumerEntity> getTableView() {
        return tableView;
    }

    @Override
    protected void initColumn() {
        nameProducerColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));
    }

    @Override
    public ConsumerEntity getSelect() {
        return tableView.getSelectionModel().getSelectedItem();
    }
    @Override
    public List<ConsumerEntity> getSelected() {
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
