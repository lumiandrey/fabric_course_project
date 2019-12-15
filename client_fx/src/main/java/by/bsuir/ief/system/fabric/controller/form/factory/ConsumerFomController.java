package by.bsuir.ief.system.fabric.controller.form.factory;

import by.bsuir.ief.system.fabric.controller.form.BaseFormController;
import by.bsuir.ief.system.fabric.model.entity.fabric.ConsumerEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;
import by.bsuir.ief.system.fabric.util.Style;
import by.bsuir.ief.system.fabric.util.ValidUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ConsumerFomController extends BaseFormController<ConsumerEntity> {

    @FXML
    private Label idLabel;
    @FXML
    private TextField nameField;

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
                "Название потребителя не заполнено!\n",
                nameField
        );

        return errorMessage;
    }

    @Override
    protected void initField(ConsumerEntity entity) {
        idLabel.setText(Integer.toString(entity.getId()));

        nameField.setText(entity.getName());
    }

    @Override
    protected void initEntity(ConsumerEntity entity) {
        entity.setName(nameField.getText());
    }

    @Override
    protected ConsumerEntity create() {
        return new ConsumerEntity();
    }
}
