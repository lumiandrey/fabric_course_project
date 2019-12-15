package by.bsuir.ief.system.fabric.controller.form.factory;

import by.bsuir.ief.system.fabric.controller.form.BaseFormController;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingConstEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingDynamicEntity;
import by.bsuir.ief.system.fabric.util.ParseUtils;
import by.bsuir.ief.system.fabric.util.ValidUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class OutGoingDynamicFomController extends BaseFormController<OutGoingDynamicEntity> {

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
                "Название переменной издержки не заполнено!\n",
                nameField
        );

        errorMessage += ValidUtil.checkDoubleValid(
                costOutGoing,
                "Поле \"Стоимость переменной издрежки\" обязательно к заполнению\n",
                "Ошибка ввода \"Стоимость переменной издрежки\"\n"
        );

        return errorMessage;
    }

    @Override
    protected void initField(OutGoingDynamicEntity entity) {
        idLabel.setText(Integer.toString(entity.getId()));

        nameField.setText(entity.getName());
    }

    @Override
    protected void initEntity(OutGoingDynamicEntity entity) {
        entity.setName(nameField.getText());
        entity.setCost(ParseUtils.parseDouble(costOutGoing));
    }

    @Override
    protected OutGoingDynamicEntity create() {
        return new OutGoingDynamicEntity();
    }
}
