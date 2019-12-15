package by.bsuir.ief.system.fabric.controller.form.factory;

import by.bsuir.ief.system.fabric.controller.DialogManager;
import by.bsuir.ief.system.fabric.controller.form.BaseFormController;
import by.bsuir.ief.system.fabric.controller.form.FormController;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;
import by.bsuir.ief.system.fabric.model.entity.user.RoleApplicationEntity;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.storage.Repository;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommandShowError;
import by.bsuir.ief.system.fabric.model.storage.command.user.RoleApplicationListCommand;
import by.bsuir.ief.system.fabric.util.Crypto;
import by.bsuir.ief.system.fabric.util.ListUtil;
import by.bsuir.ief.system.fabric.util.Style;
import by.bsuir.ief.system.fabric.util.ValidUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.Arrays;

public class ProducerFomController extends BaseFormController<ProducerEntity> {


    @FXML
    private Label idLabel;
    @FXML
    private TextField nameField;
    private ProducerEntity entity = null;

    @FXML
    private void handleClear() {
        clearField();
    }

    protected void clearField() {

        idLabel.setText("0");

        nameField.setText("");
    }

    @Override
    protected String isValidMessage() {
        String errorMessage = "";

        errorMessage += ValidUtil.checkFieldStringNotEmpty(
                "Название поставщика не заполнено!\n",
                nameField
        );

        return errorMessage;
    }

    @Override
    protected void initEntity(ProducerEntity entity) {
        entity.setName(nameField.getText());
    }

    @Override
    protected void initField(ProducerEntity entity) {
        idLabel.setText(Integer.toString(entity.getId()));

        nameField.setText(entity.getName());
    }

    @Override
    protected ProducerEntity create() {
        return new ProducerEntity();
    }

    @Override
    public Pane getLayout() {
        return rootPane;
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {

    }
}
