package by.bsuir.ief.system.fabric.controller;

import by.bsuir.ief.system.fabric.FXLoaderController;
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

            controller.setPrimaryStage(this.primaryStage);
            primaryStage.show();

        } catch (IOException e) {
            DialogManager.showErrorDialog(
                    "Ошибка в файле",
                    "Непредвиденная ошибка!");
        }
    }
}
