package by.bsuir.ief.system.fabric.controller.table.factory;

import by.bsuir.ief.system.fabric.controller.table.BaseTableController;
import by.bsuir.ief.system.fabric.controller.table.TableController;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;
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

public class ProducerTableController extends BaseTableController<ProducerEntity> {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<ProducerEntity> producerEntityTableView;

    @FXML
    private TableColumn<ProducerEntity, String> nameProducerColumn;

    @Override
    protected TableView<ProducerEntity> getTableView() {
        return producerEntityTableView;
    }

    @Override
    protected void initColumn() {
        nameProducerColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));
    }

    @Override
    public Pane getLayout() {
        return rootPane;
    }

}
