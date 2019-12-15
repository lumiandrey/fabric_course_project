package by.bsuir.ief.system.fabric.controller;

import by.bsuir.ief.system.fabric.ServerThreadWork;
import by.bsuir.ief.system.fabric.model.entity.user.RoleApplicationEntity;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.util.RepositoryManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunServerSettingController implements AcceptNewClient {

    private Stage primaryStage;

    private BorderPane rootLayout;

    private ObservableList<String> strings = FXCollections.observableArrayList();
    private ObservableList<UserEntity> userEntities = FXCollections.observableArrayList();
    private List<UserEntity> userEntityList = new ArrayList<>();
    private List<String> stringArrayList = new ArrayList<>();

    @FXML
    private Label ipAdress;

    @FXML
    private Label portListening;

    @FXML
    private Label ActiveClient;

    @FXML
    private ListView<String> stringListView;

    @FXML
    private ListView<UserEntity> userEntityListView;

    private String ipAddres;

    private Integer portListen;

    public static ObservableList<UserEntity> parseListTypeUserEntity(
            ObservableList<UserEntity> observableList,
            List<UserEntity> list
    ) {
        if (observableList.size() > 0) {
            observableList.clear();
            observableList = null;
        }
        observableList = FXCollections.observableArrayList();
        if (list != null) {
            observableList.setAll(list);

        }
        return observableList;
    }

    public static ObservableList<String> parseListString(
            ObservableList<String> observableList,
            List<String> list
    ) {
        if (observableList.size() > 0) {
            observableList.clear();
            observableList = null;
        }
        observableList = FXCollections.observableArrayList();
        if (list != null) {
            observableList.setAll(list);

        }
        return observableList;
    }

    @FXML
    private void initialize() {
        ActiveClient.setText(String.valueOf(0));
        portListening.setText(String.valueOf(0));
        ipAdress.setText("127.0.0.1");
    }

    @FXML
    public void actionNewClient() {
        ActiveClient.setText(String.valueOf(ServerThreadWork.getCountUser()));
        portListening.setText(String.valueOf(ServerThreadWork.getNumberPort()));
        ipAdress.setText(ServerThreadWork.getIpAddres());
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    public String getIpAddres() {
        return ipAddres;
    }

    public void setIpAddres(String ipAddres) {
        this.ipAddres = ipAddres;
        this.ipAdress.setText(this.ipAddres);
    }

    public Integer getPortListen() {
        return portListen;
    }

    public void setPortListen(Integer portListen) {
        this.portListen = portListen;
        this.portListening.setText(String.valueOf(this.portListen));
    }

    @Override
    public void onActionClient(String string) {

        if (!string.isEmpty()) {
            stringArrayList.add(string);

            strings = parseListString(strings, stringArrayList);

            stringListView.getItems().clear();
            stringListView.setCellFactory(new Callback<>() {

                @Override
                public ListCell<String> call(ListView<String> param) {
                    ListCell<String> cell = new ListCell<String>() {

                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                setText(item);
                            }
                        }
                    };
                    return cell;
                }
            });

            stringArrayList.add(string);
            stringListView.setItems(strings);
        }
    }

    @Override
    public void onAcceptClient(UserEntity entity) {

        userEntityList.add(entity);

        userEntities = parseListTypeUserEntity(userEntities, userEntityList);

        initUserListView();
    }

    @Override
    public void onRemoveClient(UserEntity entity) {

        userEntityList.remove(entity);

        userEntities = parseListTypeUserEntity(userEntities, userEntityList);

        initUserListView();
    }

    private void initUserListView() {
        userEntityListView.getItems().clear();

        userEntityListView.setCellFactory(new Callback<ListView<UserEntity>, ListCell<UserEntity>>() {

            @Override
            public ListCell<UserEntity> call(ListView<UserEntity> param) {
                ListCell<UserEntity> cell = new ListCell<UserEntity>() {

                    @Override
                    protected void updateItem(UserEntity item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getIdUser() + " " + item.getLogin() + ' ' + item.getRoleApplication().getName());
                        }
                    }
                };
                return cell;
            }
        });

        userEntityListView.setItems(userEntities);
    }

    @FXML
    private void onBackup() {

        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "TXT files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        final File file = fileChooser.showSaveDialog(primaryStage);

        new Thread(() -> {

            if (file != null) {

                try {
                    File local = file;
                    // Make sure it has the correct extension
                    if (!local.getPath().endsWith(".json")) {
                        local = new File(local.getPath() + ".json");

                    }

                    Gson gson = new GsonBuilder().setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").create();

                    JsonArray jsonArray = new JsonArray();

                    Map<String, List> map = new HashMap<>();

                    List<RoleApplicationEntity> roleApplicationEntities = RepositoryManager.getInstance().getUserRoleApplicationRepository().read();
                    map.put("roleApplicationEntities", roleApplicationEntities);

                    List<UserEntity> userEntities = RepositoryManager.getInstance().getUserRepository().read();
                    map.put("userEntities", userEntities);


                    try (FileWriter fileWriter = new FileWriter(local)) {
                        fileWriter.write(gson.toJson(map));
                    } catch (IOException e) {
                        DialogManager.showErrorDialog("Ошибка при записи в файл", "Ошибка при записи в файл " + e.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

}
