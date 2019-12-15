package by.bsuir.ief.system.fabric.controller.user;

import by.bsuir.ief.system.fabric.FXLoaderController;
import by.bsuir.ief.system.fabric.controller.Controller;
import by.bsuir.ief.system.fabric.controller.DialogManager;
import by.bsuir.ief.system.fabric.controller.form.FormController;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.storage.Repository;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommandShowError;
import by.bsuir.ief.system.fabric.model.storage.command.user.GetUserCommand;
import by.bsuir.ief.system.fabric.model.storage.command.user.UserUpdateCommand;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Arrays;

public class ProfileController implements Controller {

    @FXML
    private BorderPane borderPane;

    private FormController<UserEntity> userEntityFormController;

    @Override
    public void initialize() {

        try {

            userEntityFormController = FXLoaderController.loadFormController("UserEditFormView");

            borderPane.setLeft(userEntityFormController.getLayout());

            Repository.getResult(new GetUserCommand())
                    .subscribe(new AbstractCommandShowError<UserEntity>() {
                        @Override
                        public void onNext(UserEntity entity) {
                            userEntityFormController.setEntity(entity);
                        }
                    });

        } catch (Exception e){

            DialogManager.showErrorDialog(
                    "Ошибка в файле",
                    "Непредвиденная ошибка!\n" + Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void displayIndicatorProgress() {

    }

    @Override
    public void closeIndicatorProgress() {

    }

    @FXML
    private void onUpdateUserInformation(){

        if(userEntityFormController.isValid()) {
            userEntityFormController.initEntity();

            Repository.getResult(new UserUpdateCommand(userEntityFormController.getEntity()))
                    .subscribe(new AbstractCommandShowError<Boolean>() {
                        @Override
                        public void onNext(Boolean aBoolean) {
                            DialogManager.showInfoDialog("Операция завершена успешно!", "Обновление завершено успешно");
                        }
                    });

        }
    }

    @Override
    public Pane getLayout() {
        return borderPane;
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {
    }
}
