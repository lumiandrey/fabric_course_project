package by.bsuir.ief.system.fabric.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RegistrationFormController {


    private Stage dialogRegistrationStage;

    private Boolean okClicked;

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registration;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    void initialize() {
    }

    @FXML
    private void handleRegistration() {
        if (isInputValidUser()) {

            registration.setVisible(false);
            progressIndicator.setVisible(true);

        } else
            okClicked = false;
    }

    private Boolean isInputValidUser() {
        String errorMessage = "";
        if (loginField.getText() == null || loginField.getText().length() < 2)
            errorMessage += "Логин не заполнен!\n";
        if (passwordField.getText() == null || passwordField.getText().length() < 2)
            errorMessage += "Пароль не заполнен!\n";
        if (passwordField.getText() != null && passwordField.getText().length() > 32)
            errorMessage += "Пароль слишком длинный. Введите пароль не больше 32 символов!\n";
        if (errorMessage.length() == 0)
            return true;
        else
            DialogManager.showErrorDialog("Ошибка ввода данных регистрации", errorMessage);
        return false;
    }

    @FXML
    private void handleCancel() {
        dialogRegistrationStage.close();
    }


    public void setDialogRegistrationStage(Stage dialogRegistrationStage) {
        this.dialogRegistrationStage = dialogRegistrationStage;
    }

    public Boolean getOkClicked() {
        return okClicked;
    }

    @FXML
    private void checkTypeRegistration() {
    }
}
