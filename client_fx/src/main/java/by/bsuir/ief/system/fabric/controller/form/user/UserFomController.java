package by.bsuir.ief.system.fabric.controller.form.user;

import by.bsuir.ief.system.fabric.controller.DialogManager;
import by.bsuir.ief.system.fabric.controller.form.FormController;
import by.bsuir.ief.system.fabric.model.entity.user.RoleApplicationEntity;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.storage.Repository;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommandShowError;
import by.bsuir.ief.system.fabric.model.storage.command.user.RoleApplicationListCommand;
import by.bsuir.ief.system.fabric.util.Crypto;
import by.bsuir.ief.system.fabric.util.ListUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.Arrays;

public class UserFomController implements FormController<UserEntity> {

    ObservableList<RoleApplicationEntity> typeEntities = FXCollections.observableArrayList();
    @FXML
    private Pane rootPane;
    @FXML
    private Pane dataPane;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private Label idLabel;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<RoleApplicationEntity> roleApp;
    @FXML
    private CheckBox statusUser;
    @FXML
    private Label infoBlockStatusUser;
    private UserEntity entity = null;

    @FXML
    private void handleClear() {
        clearField();
    }


    private void clearField() {

        idLabel.setText("0");

        loginField.setText("");

        passwordField.setText("");

        if (typeEntities.size() > 0)
            roleApp.setValue(typeEntities.get(0));

        statusUser.setSelected(false);
        //statusUser.selectedProperty().setValue(user.getStatus());
        statusUser.selectedProperty().set(false);

        printStatusUser(statusUser.isSelected());
    }

    private void printStatusUser(Boolean newValue) {
        if (newValue) {
            infoBlockStatusUser.setText("Пользователь заблокирован");
        } else {
            infoBlockStatusUser.setText("Пользователь не заблокирован");
        }
    }

    @Override
    public void initEntity() {

        if (isValid()) {

            if (entity == null) {
                entity = new UserEntity();
            }
            entity.setLogin(loginField.getText());
            String pass = passwordField.getText();
            entity.setPassword(pass.equals(entity.getPassword()) ? entity.getPassword() : Crypto.sha256(passwordField.getText()));
            entity.setStatus(!statusUser.isSelected() ? 1 : 0);
            entity.setRoleApplication(roleApp.getValue());
        }

    }

    @Override
    public boolean isValid() {
        String errorMessage = "";
        if (loginField.getText() == null || loginField.getText().length() < 2)
            errorMessage += "Логин не заполнен!\n";
        if (passwordField.getText() == null || passwordField.getText().length() < 2)
            errorMessage += "Пароль не заполнен!\n";
        if (passwordField.getText() != null && passwordField.getText().length() > 64)
            errorMessage += "Пароль слишком длинный. Введите пароль не больше 64 символов!\n";
        if (errorMessage.length() == 0)
            return true;
        else
            DialogManager.showErrorDialog("Ошибка ввода данных", errorMessage);
        return false;
    }

    @Override
    public UserEntity getEntity() {
        return entity;
    }

    @Override
    public void setEntity(UserEntity entity) {
        this.entity = entity;
        if (entity != null) {

            idLabel.setText(Integer.toString(entity.getIdUser()));

            loginField.setText(entity.getLogin());

            passwordField.setText(entity.getPassword());

            roleApp.setValue(entity.getRoleApplication());

            statusUser.setSelected(entity.getStatus() != 0);
            //statusUser.selectedProperty().setValue(user.getStatus());
            statusUser.selectedProperty().set(entity.getStatus() == 0);

            printStatusUser(statusUser.isSelected());
        } else {

            clearField();
        }
    }

    @Override
    public void initialize() {

       /* rootPane.setBackground(Style.getBackground());
        dataPane.setBackground(Style.getBackground());
*/
        Repository.getResult(new RoleApplicationListCommand())
                .subscribe(new AbstractCommandShowError<RoleApplicationEntity[]>() {
                    @Override
                    public void onNext(RoleApplicationEntity[] roleApplicationEntities) {

                        typeEntities = ListUtil.parseListToObserv(typeEntities, Arrays.asList(roleApplicationEntities));
                        roleApp.setCellFactory(new Callback<ListView<RoleApplicationEntity>, ListCell<RoleApplicationEntity>>() {
                            @Override
                            public ListCell<RoleApplicationEntity> call(ListView<RoleApplicationEntity> param) {
                                final ListCell<RoleApplicationEntity> cell = new ListCell<RoleApplicationEntity>() {

                                    @Override
                                    protected void updateItem(RoleApplicationEntity t, boolean bln) {
                                        super.updateItem(t, bln);

                                        if (t != null) {
                                            setText(t.getName());
                                        } else {
                                            setText(null);
                                        }
                                    }

                                };

                                return cell;
                            }
                        });
                        roleApp.setConverter(new StringConverter<RoleApplicationEntity>() {
                            @Override
                            public String toString(RoleApplicationEntity t) {
                                return t.getName();
                            }

                            @Override
                            public RoleApplicationEntity fromString(String string) {
                                return null;
                            }
                        });

                        roleApp.setItems(typeEntities);
                        roleApp.getSelectionModel().selectFirst();
                        roleApp.setValue(typeEntities.get(0));
                    }
                });
    }

    @Override
    public void displayIndicatorProgress() {
        dataPane.setVisible(false);
        progressIndicator.setVisible(true);
    }

    @Override
    public void closeIndicatorProgress() {
        dataPane.setVisible(true);
        progressIndicator.setVisible(false);
    }

    @Override
    public Pane getLayout() {
        return rootPane;
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {

    }
}
