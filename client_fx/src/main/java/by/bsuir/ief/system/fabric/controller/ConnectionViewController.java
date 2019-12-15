package by.bsuir.ief.system.fabric.controller;

import by.bsuir.ief.system.fabric.model.storage.ConnectToServer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class ConnectionViewController {

    @FXML
    private TextField ipAdressInput;

    @FXML
    private TextField portListenedInput;

    @FXML
    private Button connectBut;

    private Stage primaryStage;

    @FXML
    private Pane rootLayout;

    @FXML
    private void initialize() {
        ipAdressInput.setText(ConnectToServer.getIpAdress());
        portListenedInput.setText(String.valueOf(ConnectToServer.getPORT()));
    }

    @FXML
    private void handleConnectToServer()
    {
        String ipAddr = ipAdressInput.getText();
        String port = portListenedInput.getText();
        if(ipAddr.length() < 1 || port.length() < 1)
        {
            DialogManager.showErrorDialog(
                    "Ошибка ввода",
                    "Заолните настройки конфигурации соединения с сервером!!!");
        }
        else
        {
            try {
                InetAddress address = InetAddress.getByName(ipAddr);
                try {
                    ConnectToServer.setInetAddress(address);
                    ConnectToServer.setIpAdress(ipAddr);
                    ConnectToServer.setPORT(Integer.parseInt(port));

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(ConnectionViewController.class.getResource("/view/Autentification.fxml"));
                    rootLayout = (AnchorPane) loader.load();
                    Scene scene = new Scene(rootLayout);
                    primaryStage.setScene(scene);
                    AutentificationController controller = loader.getController();
                    controller.setRootLayout((AnchorPane) rootLayout);
                    controller.setPrimaryStage(primaryStage);
                    primaryStage.show();
                } catch (IOException e) {
                    DialogManager.showErrorDialog(
                            "Ошибка настройки окон",
                            "Обратитесь в отделение для настройки вашего приложения");
                }
            } catch (UnknownHostException e) {
                DialogManager.showErrorDialog(
                        "Ошибка соединения",
                        "Ошибка соединения с удалённым узлом, пожалуйста, проверьте введённый ip-адресс");
            }
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

    public void setRootLayout(Pane rootLayout) {
        this.rootLayout = rootLayout;
    }
}
