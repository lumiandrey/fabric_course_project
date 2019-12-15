package by.bsuir.ief.system.fabric.controller.form.factory;

import by.bsuir.ief.system.fabric.controller.form.BaseFormController;
import by.bsuir.ief.system.fabric.model.entity.fabric.ConsumerEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingConstEntity;
import by.bsuir.ief.system.fabric.util.ParseUtils;
import by.bsuir.ief.system.fabric.util.ValidUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class OutGoingConstFomController extends BaseFormController<OutGoingConstEntity> {

    @FXML
    private Label idLabel;
    @FXML
    private TextField nameField;
    @FXML
    private TextField costOutGoing;

    @FXML
    private void handleClear() {
        clearField();
    }

    protected void clearField() {

        idLabel.setText("0");

        nameField.setText("");

        costOutGoing.setText("0.0");
    }

    @Override
    protected String isValidMessage() {
        String errorMessage = "";

        errorMessage += ValidUtil.checkFieldStringNotEmpty(
                "Название постоянной издержки не заполнено!\n",
                nameField
        );

        errorMessage += ValidUtil.checkDoubleValid(
                costOutGoing,
                "Поле \"Стоимость постоянной издрежки\" обязательно к заполнению\n",
                "Ошибка ввода \"Стоимость постоянной издрежки\"\n"
        );

        return errorMessage;
    }

    @Override
    protected void initField(OutGoingConstEntity entity) {
        idLabel.setText(Integer.toString(entity.getId()));

        nameField.setText(entity.getName());
    }

    @Override
    protected void initEntity(OutGoingConstEntity entity) {
        entity.setName(nameField.getText());
        entity.setCost(ParseUtils.parseDouble(costOutGoing));
    }

    @Override
    protected OutGoingConstEntity create() {
        return new OutGoingConstEntity();
    }
}
