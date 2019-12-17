package by.bsuir.ief.system.fabric.controller.form;

import by.bsuir.ief.system.fabric.controller.DialogManager;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

abstract public class BaseFormController<T> implements FormController<T> {

    @FXML
    protected Pane rootPane;
    @FXML
    protected Pane dataPane;
    @FXML
    protected ProgressIndicator progressIndicator;

    protected Stage primaryStage;

    protected T entity = null;

    protected abstract String isValidMessage();

    protected abstract T create();

    protected abstract void clearField();

    protected abstract void initField(T entity);

    protected abstract void initEntity(T entity);

    @Override
    public void initialize() {

    }

    @Override
    public void initEntity() {

        if (isValid()) {

            if (entity == null) {
                entity = create();
            }
            initEntity(entity);
        }
    }

    @Override
    public boolean isValid() {
        String errorMessage = isValidMessage();

        if (errorMessage.length() == 0)
            return true;
        else
            DialogManager.showErrorDialog("Ошибка ввода данных", errorMessage);
        return false;
    }

    @Override
    public void setEntity(T entity) {
        this.entity = entity;
        if (entity != null) {

            initField(entity);

        } else {

            clearField();
        }
    }

    @Override
    public T getEntity() {
        return entity;
    }

    @Override
    public Pane getLayout() {
        return rootPane;
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
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
}
