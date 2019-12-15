package by.bsuir.ief.system.fabric.controller;


import by.bsuir.ief.system.fabric.FXLoaderController;
import by.bsuir.ief.system.fabric.controller.room_controller.AdminController;
import by.bsuir.ief.system.fabric.model.entity.user.AutorizationUser;
import by.bsuir.ief.system.fabric.model.storage.Repository;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractObserver;
import by.bsuir.ief.system.fabric.model.storage.command.user.AutorizationCommand;
import by.bsuir.ief.system.fabric.util.Crypto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AutentificationController {

    @FXML
    private Button singIn;

    @FXML
    private Button registration;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginField;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private Pane data;

    private Stage primaryStage;

    private AnchorPane rootLayout;

    public AutentificationController()
    {
        this.loginField = new TextField();
        this.passwordField = new PasswordField();
        this.registration = new Button();
        this.singIn = new Button();
    }

    @FXML
    public void handleSingIn() throws IOException {
        Boolean checkInput = false;
        String login = loginField.getText();
        String password = passwordField.getText();

        if (login.length() < 1)
            DialogManager.showErrorDialog(
                    "Ошибка ввода логина",
                    "Проверьте ввод и повторите попытку");
        else if (password.length() < 1)
            DialogManager.showErrorDialog(
                    "Ошибка ввода пароля",
                    "Проверьте ввод и повторите попытку");
        else
            checkInput = true;
        if (checkInput) {

            password = Crypto.sha256(password);
            String finalPassword = password;

            Repository.getResult(new AutorizationCommand(new AutorizationUser(login, finalPassword)))
                    .subscribe(new AbstractObserver<String>() {
                        @Override
                        public void onNext(String s) {

                            try {
                                switch (s) {
                                    case "ADMIN": {

                                        SignInRoomAdmin();

                                    }
                                    break;
                                    case "USER": {

                                        SignInRoomSpetialist();
                                    }
                                    break;
                                    default: {
                                        DialogManager.showWarningDialog("Что-то пошло не так.",
                                                "Запрос прошёл успешно, но что-то пошло не так и программа не может работать с этой комбинацией");
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            DialogManager.showErrorDialog("Ошибка при авторизации", throwable.getMessage());
                        }
                    });
        }
    }

    private void SignInRoomAdmin() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AutentificationController.class.getResource("/view/AdminRoom.fxml"));
            BorderPane borderPaneLayout = loader.load();
            Scene scene = new Scene(borderPaneLayout);
            primaryStage.setScene(scene);

            AdminController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            primaryStage.show();
        } catch (IOException e) {
            DialogManager.showErrorDialog(
                    "Ошибка в файле",
                    "Непредвиденная ошибка!");
            throw e;
        }
    }

    private void SignInRoomSpetialist() throws IOException {
        try {

            Controller controller = FXLoaderController.loadController("UserRoomView");
            Scene scene = new Scene(controller.getLayout());
            primaryStage.setScene(scene);

            controller.setPrimaryStage(primaryStage);

            primaryStage.show();
        } catch (IOException e) {
            DialogManager.showErrorDialog(
                    "Ошибка в файле",
                    "Непредвиденная ошибка!");
            throw e;
        }
    }

    @FXML
    public void handleRegistration()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/RegistrationForm.fxml"));
        try {
            AnchorPane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            RegistrationFormController controller = loader.getController();
            controller.setDialogRegistrationStage(dialogStage);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Pane getRootLayout() {
        return rootLayout;
    }

    public void setRootLayout(AnchorPane rootLayout) {
        this.rootLayout =  rootLayout;
    }
}
