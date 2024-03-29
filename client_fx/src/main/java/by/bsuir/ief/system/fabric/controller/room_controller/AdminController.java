package by.bsuir.ief.system.fabric.controller.room_controller;

import by.bsuir.ief.system.fabric.FXLoaderController;
import by.bsuir.ief.system.fabric.controller.BaseRoomController;
import by.bsuir.ief.system.fabric.controller.BottomBarController;
import by.bsuir.ief.system.fabric.controller.CreateEditDeleteBottomController;
import by.bsuir.ief.system.fabric.controller.DialogManager;
import by.bsuir.ief.system.fabric.controller.bottombar.factory.*;
import by.bsuir.ief.system.fabric.controller.bottombar.user.UserCallbackBottomBarManipulation;
import by.bsuir.ief.system.fabric.controller.form.FormController;
import by.bsuir.ief.system.fabric.controller.table.TableController;
import by.bsuir.ief.system.fabric.model.entity.fabric.*;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.storage.Repository;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractObserver;
import by.bsuir.ief.system.fabric.model.storage.command.ExitCommand;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController extends BaseRoomController {

    private Stage primaryStage;

    @FXML
    private BorderPane pane;

    @FXML
    private AnchorPane centerPane;

    @FXML
    private void handleWorkUsers(){

        clearPane();

        try {
            TableController<UserEntity> tableController = FXLoaderController.loadTableController("UserTableView");

            FormController<UserEntity> formController = FXLoaderController.loadFormController("UserEditFormView");

            BottomBarController bottomBarController = (BottomBarController) FXLoaderController.loadController("BottomBar");

            CreateEditDeleteBottomController createEditDeleteBottomController = (CreateEditDeleteBottomController) FXLoaderController.loadController("CreateEditDeleteBottomBarView");

            createEditDeleteBottomController.setCallback(new UserCallbackBottomBarManipulation(tableController, formController));
            bottomBarController.setAdminPane(createEditDeleteBottomController.getLayout());

            pane.setBottom(bottomBarController.getLayout());

            Repository.getsUser(tableController);

            tableController.setCallbackSelect(formController::setEntity);

            pane.setCenter(tableController.getLayout());
            pane.setLeft(formController.getLayout());

        } catch (IOException e) {
            DialogManager.showErrorDialog("Error", "Произошла ошибка " + e.getMessage());
        }
    }
}
