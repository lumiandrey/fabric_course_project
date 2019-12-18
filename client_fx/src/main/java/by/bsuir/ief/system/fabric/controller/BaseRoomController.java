package by.bsuir.ief.system.fabric.controller;

import by.bsuir.ief.system.fabric.FXLoaderController;
import by.bsuir.ief.system.fabric.controller.bottombar.factory.*;
import by.bsuir.ief.system.fabric.controller.bottombar.user.UserCallbackBottomBarManipulation;
import by.bsuir.ief.system.fabric.controller.form.FormController;
import by.bsuir.ief.system.fabric.controller.table.TableController;
import by.bsuir.ief.system.fabric.model.entity.fabric.*;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.storage.ConnectToServer;
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

public class BaseRoomController implements Controller {

    protected Stage primaryStage;

    @FXML
    protected BorderPane pane;

    @FXML
    protected AnchorPane centerPane;

    @FXML
    @Override
    public void initialize() {

    }

    @Override
    public void displayIndicatorProgress() {

    }

    @Override
    public void closeIndicatorProgress() {

    }

    @Override
    public Pane getLayout() {
        return pane;
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    protected void clearPane(){

        pane.setLeft(null);
        pane.setRight(null);
        pane.setCenter(null);
        pane.setBottom(null);
    }

    @FXML
    private void handleExit(){
        try {
            Repository.getResult(new ExitCommand()).subscribe(new AbstractObserver<Boolean>() {
                @Override
                public void onNext(Boolean aBoolean) {

                }

                @Override
                public void onError(Throwable throwable) {

                }
            });

            FXMLLoader loader = FXLoaderController.load("ConnectionView");

            ConnectionViewController controller = loader.getController();

            Scene scene = new Scene(controller.getRootLayout());
            primaryStage.setScene(scene);
            ConnectToServer.setToken("");

            controller.setPrimaryStage(this.primaryStage);
            primaryStage.show();

        } catch (IOException e) {
            DialogManager.showErrorDialog(
                    "Ошибка в файле",
                    "Непредвиденная ошибка!");
        }
    }



    @FXML
    private void handleWorkProducer(){
        clearPane();

        try {
            TableController<ProducerEntity> tableController = FXLoaderController.loadBusinessTableController("ProducerTableView");

            FormController<ProducerEntity> formController = FXLoaderController.loadBusinessFormController("ProducerEditFormView");

            BottomBarController bottomBarController = (BottomBarController) FXLoaderController.loadController("BottomBar");

            CreateEditDeleteBottomController createEditDeleteBottomController = (CreateEditDeleteBottomController) FXLoaderController.loadController("CreateEditDeleteBottomBarView");

            createEditDeleteBottomController.setCallback(new ProducerCallbackBottomBarManipulation(tableController, formController));
            bottomBarController.setAdminPane(createEditDeleteBottomController.getLayout());

            pane.setBottom(bottomBarController.getLayout());

            Repository.getsProducer(tableController);

            tableController.setCallbackSelect(formController::setEntity);

            pane.setCenter(tableController.getLayout());
            pane.setLeft(formController.getLayout());

        } catch (IOException e) {
            e.printStackTrace();
            DialogManager.showErrorDialog("Error", "Произошла ошибка " + e.getMessage());
        }
    }

    @FXML
    private void handleWorkConsumer(){
        clearPane();

        try {
            TableController<ConsumerEntity> tableController = FXLoaderController.loadBusinessTableController("ConsumerTableView");

            FormController<ConsumerEntity> formController = FXLoaderController.loadBusinessFormController("ConsumerEditFormView");

            BottomBarController bottomBarController = (BottomBarController) FXLoaderController.loadController("BottomBar");

            CreateEditDeleteBottomController createEditDeleteBottomController = (CreateEditDeleteBottomController) FXLoaderController.loadController("CreateEditDeleteBottomBarView");

            createEditDeleteBottomController.setCallback(new ConsumerCallbackBottomBarManipulation(tableController, formController));
            bottomBarController.setAdminPane(createEditDeleteBottomController.getLayout());

            pane.setBottom(bottomBarController.getLayout());

            Repository.getsConsumer(tableController);

            tableController.setCallbackSelect(formController::setEntity);

            pane.setCenter(tableController.getLayout());
            pane.setLeft(formController.getLayout());

        } catch (IOException e) {
            e.printStackTrace();
            DialogManager.showErrorDialog("Error", "Произошла ошибка " + e.getMessage());
        }
    }

    @FXML
    private void handleWorkOutGoingConst(){
        clearPane();

        try {
            TableController<OutGoingConstEntity> tableController = FXLoaderController.loadBusinessTableController("OutGoingConstTableView");

            FormController<OutGoingConstEntity> formController = FXLoaderController.loadBusinessFormController("OutGoingConstEditFormView");

            BottomBarController bottomBarController = (BottomBarController) FXLoaderController.loadController("BottomBar");

            CreateEditDeleteBottomController createEditDeleteBottomController = (CreateEditDeleteBottomController) FXLoaderController.loadController("CreateEditDeleteBottomBarView");

            createEditDeleteBottomController.setCallback(new OutGoingCostCallbackBottomBarManipulation(tableController, formController));
            bottomBarController.setAdminPane(createEditDeleteBottomController.getLayout());

            pane.setBottom(bottomBarController.getLayout());

            Repository.getsOutGoingCost(tableController);

            tableController.setCallbackSelect(formController::setEntity);

            pane.setCenter(tableController.getLayout());
            pane.setLeft(formController.getLayout());

        } catch (IOException e) {
            e.printStackTrace();
            DialogManager.showErrorDialog("Error", "Произошла ошибка " + e.getMessage());
        }
    }

    @FXML
    private void handleWorkOutGoingDynamic(){
        clearPane();

        try {
            TableController<OutGoingDynamicEntity> tableController = FXLoaderController.loadBusinessTableController("OutGoingDynamicTableView");

            FormController<OutGoingDynamicEntity> formController = FXLoaderController.loadBusinessFormController("OutGoingDynamicEditFormView");

            BottomBarController bottomBarController = (BottomBarController) FXLoaderController.loadController("BottomBar");

            CreateEditDeleteBottomController createEditDeleteBottomController = (CreateEditDeleteBottomController) FXLoaderController.loadController("CreateEditDeleteBottomBarView");

            createEditDeleteBottomController.setCallback(new OutGoingDynamicCallbackBottomBarManipulation(tableController, formController));
            bottomBarController.setAdminPane(createEditDeleteBottomController.getLayout());

            pane.setBottom(bottomBarController.getLayout());

            Repository.getsOutGoingDynamic(tableController);

            tableController.setCallbackSelect(formController::setEntity);

            pane.setCenter(tableController.getLayout());
            pane.setLeft(formController.getLayout());

        } catch (IOException e) {
            e.printStackTrace();
            DialogManager.showErrorDialog("Error", "Произошла ошибка " + e.getMessage());
        }
    }

    @FXML
    private void handleWorkComponent(){
        clearPane();

        try {
            TableController<ComponentPartWithProducerEntity> tableController = FXLoaderController.loadBusinessTableController("ComponentPartTableView");

            FormController<ComponentPartWithProducerEntity> formController = FXLoaderController.loadBusinessFormController("ComponentPartEditFormView");

            BottomBarController bottomBarController = (BottomBarController) FXLoaderController.loadController("BottomBar");

            CreateEditDeleteBottomController createEditDeleteBottomController = (CreateEditDeleteBottomController) FXLoaderController.loadController("CreateEditDeleteBottomBarView");

            createEditDeleteBottomController.setCallback(new ComponentPartCallbackBottomBarManipulation(tableController, formController));
            bottomBarController.setAdminPane(createEditDeleteBottomController.getLayout());

            pane.setBottom(bottomBarController.getLayout());

            Repository.getsComponentPart(tableController);

            tableController.setCallbackSelect(formController::setEntity);

            pane.setCenter(tableController.getLayout());
            pane.setLeft(formController.getLayout());

        } catch (IOException e) {
            e.printStackTrace();
            DialogManager.showErrorDialog("Error", "Произошла ошибка " + e.getMessage());
        }
    }

    @FXML
    private void handleWorkProduction(){
        clearPane();

        try {
            TableController<ProductionEntity> tableController = FXLoaderController.loadBusinessTableController("ProductionTableView");

            FormController<ProductionEntity> formController = FXLoaderController.loadBusinessFormController("ProductionEditFormView");

            BottomBarController bottomBarController = (BottomBarController) FXLoaderController.loadController("BottomBar");

            CreateEditDeleteBottomController createEditDeleteBottomController = (CreateEditDeleteBottomController) FXLoaderController.loadController("CreateEditDeleteBottomBarView");

            createEditDeleteBottomController.setCallback(new ProductionCallbackBottomBarManipulation(tableController, formController));
            bottomBarController.setAdminPane(createEditDeleteBottomController.getLayout());

            pane.setBottom(bottomBarController.getLayout());

            Repository.getsProduction(tableController);

            tableController.setCallbackSelect(formController::setEntity);

            pane.setCenter(tableController.getLayout());
            pane.setLeft(formController.getLayout());

        } catch (IOException e) {
            e.printStackTrace();
            DialogManager.showErrorDialog("Error", "Произошла ошибка " + e.getMessage());
        }
    }
}
