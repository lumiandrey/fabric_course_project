package by.bsuir.ief.system.fabric.controller.table.factory;

import by.bsuir.ief.system.fabric.controller.DialogManager;
import by.bsuir.ief.system.fabric.controller.table.BaseTableController;
import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartWithProducerEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingConstEntity;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ComponentPartTableController extends BaseTableController<ComponentPartWithProducerEntity> {

    private Stage primaryStage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<ComponentPartWithProducerEntity> tableView;

    @FXML
    private TableColumn<ComponentPartWithProducerEntity, String> nameColumn;

    @FXML
    private TableColumn<ComponentPartWithProducerEntity, Double> price;

    @FXML
    private TableColumn<ComponentPartWithProducerEntity, String> nameProducer;

    @Override
    protected TableView<ComponentPartWithProducerEntity> getTableView() {
        return tableView;
    }

    @Override
    protected void initColumn() {
        nameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));

        price.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCost()));

        nameProducer.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getProducer() != null ?
                                cellData.getValue().getProducer().getName() != null ?
                                        cellData.getValue().getProducer().getName() : ""
                                : ""
                        ));
    }

    @Override
    public ComponentPartWithProducerEntity getSelect() {
        return tableView.getSelectionModel().getSelectedItem();
    }
    @Override
    public List<ComponentPartWithProducerEntity> getSelected() {
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
