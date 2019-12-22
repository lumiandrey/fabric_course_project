package by.bsuir.ief.system.fabric.controller;

import by.MainServer;
import by.bsuir.ief.system.fabric.ServerThreadWork;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;

public class ConnectionSettingController {

    @FXML
    private TextField inputPortListining;

    private Stage primaryStage;

    @FXML
    private CheckBox checkLocalHost;

    @FXML
    private void initialize() {
        inputPortListining.setText(String.valueOf(ServerThreadWork.getNumberPort()));
    }

    @FXML
    private void handleCheckLocalHost() {

    }

    @FXML
    private void handleRunServer() {

        String port = inputPortListining.getText();

        if (port.length() < 1) {
            DialogManager.showErrorDialog(
                    "Ошибка ввода",
                    "Заолните настройки конфигурации соединения с сервером!!!");
        } else {
            try {
                if (checkLocalHost.isSelected())
                    ServerThreadWork.setInetAddress(null);
                else
                    ServerThreadWork.setInetAddress(InetAddress.getLocalHost());

                ServerThreadWork.setNumberPort(Integer.parseInt(port));
                ThreadGroup server = new ThreadGroup("serverGo");
                MainServer.setThreadGroup(server);
                ServerThreadWork serverThreadWork = new ServerThreadWork(server);
                MainServer.setThreadServer(serverThreadWork.getThread());

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/RunServerSetting.fxml"));
                BorderPane rootLayout = loader.load();
                Scene scene = new Scene(rootLayout);
                primaryStage.setScene(scene);
                RunServerSettingController controller = loader.getController();
                ServerThreadWork.setRunServerSettingController(controller);
                controller.setRootLayout(rootLayout);
                controller.setPrimaryStage(primaryStage);
                controller.setIpAddres(serverThreadWork.getIpAdressServer());
                controller.setPortListen(ServerThreadWork.getNumberPort());
                primaryStage.show();

            } catch (NumberFormatException e) {
                DialogManager.showErrorDialog(
                        "Ошибка ввода",
                        "Заолните настройки конфигурации соединения с сервером!!!");
            } catch (IOException e) {
                DialogManager.showErrorDialog(
                        "Ошибка настройки окон",
                        "Зовите системного администратора!");
            }
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


}
