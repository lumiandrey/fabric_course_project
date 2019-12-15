package by.bsuir.ief.system.fabric.controller.table.factory;

import by.bsuir.ief.system.fabric.controller.table.BaseTableController;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProductionEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ProductionTableController extends BaseTableController<ProductionEntity> {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<ProductionEntity> producerEntityTableView;

    @FXML
    private TableColumn<ProductionEntity, String> nameProducerColumn;

    @Override
    protected TableView<ProductionEntity> getTableView() {
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
