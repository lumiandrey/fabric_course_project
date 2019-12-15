package by.bsuir.ief.system.fabric;

import by.bsuir.ief.system.fabric.controller.ConnectionViewController;
import by.bsuir.ief.system.fabric.controller.DialogManager;
import by.bsuir.ief.system.fabric.model.storage.Repository;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractObserver;
import by.bsuir.ief.system.fabric.model.storage.command.ExitCommand;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientRunning extends Application {

    private Stage primaryStage;

    public static void main(String[]args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;


        try {

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

    /**
     * This method is called when the application should stop, and provides a
     * convenient place to prepare for application exit and destroy resources.
     *
     * <p>
     * The implementation of this method provided by the Application class does nothing.
     * </p>
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     */
    @Override
    public void stop() throws Exception {
        super.stop();

        Repository.getResult(new ExitCommand()).subscribe(new AbstractObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
