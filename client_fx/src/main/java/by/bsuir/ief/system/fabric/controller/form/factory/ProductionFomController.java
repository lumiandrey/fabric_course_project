package by.bsuir.ief.system.fabric.controller.form.factory;

import by.bsuir.ief.system.fabric.FXLoaderController;
import by.bsuir.ief.system.fabric.controller.DialogManager;
import by.bsuir.ief.system.fabric.controller.NewWindowWorking;
import by.bsuir.ief.system.fabric.controller.form.BaseFormController;
import by.bsuir.ief.system.fabric.controller.table.ChoiceTableController;
import by.bsuir.ief.system.fabric.controller.table.TableController;
import by.bsuir.ief.system.fabric.model.entity.fabric.*;
import by.bsuir.ief.system.fabric.model.storage.Repository;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommandShowError;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.component_part.ReadComponentWithProducerCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.out_going_cost.OutGoingConstListCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.out_going_dynamic.OutGoingDynamicListCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.production.CalculationOutGoingProductionCommand;
import by.bsuir.ief.system.fabric.util.ListUtil;
import by.bsuir.ief.system.fabric.util.ValidUtil;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ProductionFomController extends BaseFormController<ProductionEntity> {


    @FXML
    private Label idLabel;
    @FXML
    private TextField nameField;

    @FXML
    private TextField describeField;

    @FXML
    private Label resultCostOutGoing;

    @FXML
    private void handleClear() {
        clearField();
    }

    protected void clearField() {

        idLabel.setText("0");

        nameField.setText("");

        describeField.setText("");

        resultCostOutGoing.setText("");
    }

    @Override
    protected String isValidMessage() {
        String errorMessage = "";

        errorMessage += ValidUtil.checkFieldStringNotEmpty(
                "Название поставщика не заполнено!\n",
                nameField
        );

        return errorMessage;
    }

    @Override
    protected void initEntity(ProductionEntity entity) {
        entity.setName(nameField.getText());
        entity.setDescribe(describeField.getText());
    }

    @Override
    protected void initField(ProductionEntity entity) {
        idLabel.setText(Integer.toString(entity.getId()));

        nameField.setText(entity.getName());
        describeField.setText(entity.getDescribe());
    }

    @Override
    protected ProductionEntity create() {
        return new ProductionEntity();
    }

    @Override
    public Pane getLayout() {
        return rootPane;
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {

    }

    @FXML
    private void onAddComponentTable() {

        if (entity == null){
            entity = create();
        }

        if(entity != null) {

            Repository.getResult(new ReadComponentWithProducerCommand())
                    .subscribe(new AbstractCommandShowError<ComponentPartWithProducerEntity[]>() {
                        @Override
                        public void onNext(ComponentPartWithProducerEntity[] studentEntities) {

                            try {
                                ChoiceTableController<ComponentPartWithProducerEntity> choiceTableController = (ChoiceTableController) FXLoaderController.loadTableController("ChoiceTableView");
                                TableController<ComponentPartWithProducerEntity> studentEntityTableController = FXLoaderController.loadBusinessTableController("ComponentPartTableView");

                                choiceTableController.setController(studentEntityTableController);
                                Stage dialogStage = new Stage();

                                choiceTableController.setMultiSelect(true);

                                Scene scene = new Scene(choiceTableController.getLayout());
                                dialogStage.setScene(scene);
                                choiceTableController.setPrimaryStage(dialogStage);
                                choiceTableController.setList(ListUtil.parseArray(studentEntities));

                                dialogStage.showAndWait();

                                if (choiceTableController.isOk()) {

                                    List<ComponentPartWithProducerEntity> studentEntities1 = choiceTableController.getSelected();

                                    entity.setComponentPartEntities(studentEntities1);

                                }
                            } catch (Exception ex) {

                                DialogManager.showErrorDialog("Ошибка подгрузки данных", ex.getMessage());
                            }
                        }
                    });

        } else {
            DialogManager.showErrorDialog("Error", "Произошла ошибка студентов можно добавлять только для групп находящихся в базе!!!");
        }
    }

    @FXML
    private void onShowComponentTable(){
        if(entity != null && entity.getId() > 0) {
            try {

                Stage stage = new Stage();

                NewWindowWorking newWindowWorking = (NewWindowWorking) FXLoaderController.loadController("NewWondowWorking");

                stage.setScene(new Scene(newWindowWorking.getLayout()));

                TableController<ComponentPartWithProducerEntity> tableController = FXLoaderController.loadBusinessTableController("ComponentPartTableView");

                newWindowWorking.setTableController(tableController);
                newWindowWorking.setPrimaryStage(stage);

                Repository.getsComponentPart(tableController, entity.getId());

                stage.show();

            } catch (IOException e) {
                DialogManager.showErrorDialog("Error", "Произошла ошибка " + e.getMessage());
            }
        } else {
            DialogManager.showErrorDialog("Error", "Произошла ошибка просмотреть можно только после создания продукции!!!");
        }
    }

    @FXML
    private void onAddConstTable() {

        if (entity == null){
            entity = create();
        }

        if(entity != null) {

            Repository.getResult(new OutGoingConstListCommand())
                    .subscribe(new AbstractCommandShowError<OutGoingConstEntity[]>() {
                        @Override
                        public void onNext(OutGoingConstEntity[] studentEntities) {

                            try {
                                ChoiceTableController<OutGoingConstEntity> choiceTableController = (ChoiceTableController) FXLoaderController.loadTableController("ChoiceTableView");
                                TableController<OutGoingConstEntity> studentEntityTableController = FXLoaderController.loadBusinessTableController("OutGoingConstTableView");

                                choiceTableController.setController(studentEntityTableController);
                                Stage dialogStage = new Stage();

                                choiceTableController.setMultiSelect(true);

                                Scene scene = new Scene(choiceTableController.getLayout());
                                dialogStage.setScene(scene);
                                choiceTableController.setPrimaryStage(dialogStage);
                                choiceTableController.setList(ListUtil.parseArray(studentEntities));

                                dialogStage.showAndWait();

                                if (choiceTableController.isOk()) {

                                    List<OutGoingConstEntity> studentEntities1 = choiceTableController.getSelected();

                                    entity.setOutGoingConstEntities(studentEntities1);

                                }
                            } catch (Exception ex) {

                                DialogManager.showErrorDialog("Ошибка подгрузки данных", ex.getMessage());
                            }
                        }
                    });

        } else {
            DialogManager.showErrorDialog("Error", "Произошла ошибка студентов можно добавлять только для групп находящихся в базе!!!");
        }
    }

    @FXML
    private void onShowConstTable(){
        if(entity != null && entity.getId() > 0) {
            try {

                Stage stage = new Stage();

                NewWindowWorking newWindowWorking = (NewWindowWorking) FXLoaderController.loadController("NewWondowWorking");

                stage.setScene(new Scene(newWindowWorking.getLayout()));

                TableController<OutGoingConstEntity> tableController = FXLoaderController.loadBusinessTableController("OutGoingConstTableView");

                newWindowWorking.setTableController(tableController);
                newWindowWorking.setPrimaryStage(stage);

                Repository.getsOutGoingCost(tableController, entity.getId());

                stage.show();

            } catch (IOException e) {
                DialogManager.showErrorDialog("Error", "Произошла ошибка " + e.getMessage());
            }
        } else {
            DialogManager.showErrorDialog("Error", "Произошла ошибка просмотреть можно только после создания продукции!!!");
        }
    }

    @FXML
    private void onAddDynamicTable() {

        if (entity == null){
            entity = create();
        }

        if(entity != null) {

            Repository.getResult(new OutGoingDynamicListCommand())
                    .subscribe(new AbstractCommandShowError<OutGoingDynamicEntity[]>() {
                        @Override
                        public void onNext(OutGoingDynamicEntity[] studentEntities) {

                            try {
                                ChoiceTableController<OutGoingDynamicEntity> choiceTableController = (ChoiceTableController) FXLoaderController.loadTableController("ChoiceTableView");
                                TableController<OutGoingDynamicEntity> studentEntityTableController = FXLoaderController.loadBusinessTableController("OutGoingDynamicTableView");

                                choiceTableController.setController(studentEntityTableController);
                                Stage dialogStage = new Stage();

                                choiceTableController.setMultiSelect(true);

                                Scene scene = new Scene(choiceTableController.getLayout());
                                dialogStage.setScene(scene);
                                choiceTableController.setPrimaryStage(dialogStage);
                                choiceTableController.setList(ListUtil.parseArray(studentEntities));

                                dialogStage.showAndWait();

                                if (choiceTableController.isOk()) {

                                    List<OutGoingDynamicEntity> studentEntities1 = choiceTableController.getSelected();

                                    entity.setOutGoingDynamicEntities(studentEntities1);

                                }
                            } catch (Exception ex) {

                                DialogManager.showErrorDialog("Ошибка подгрузки данных", ex.getMessage());
                            }
                        }
                    });

        } else {
            DialogManager.showErrorDialog("Error", "Произошла ошибка студентов можно добавлять только для групп находящихся в базе!!!");
        }
    }

    @FXML
    private void onShowDynamicTable(){
        if(entity != null && entity.getId() > 0) {
            try {

                Stage stage = new Stage();

                NewWindowWorking newWindowWorking = (NewWindowWorking) FXLoaderController.loadController("NewWondowWorking");

                stage.setScene(new Scene(newWindowWorking.getLayout()));

                TableController<OutGoingDynamicEntity> tableController = FXLoaderController.loadBusinessTableController("OutGoingDynamicTableView");

                newWindowWorking.setTableController(tableController);
                newWindowWorking.setPrimaryStage(stage);

                Repository.getsOutGoingDynamic(tableController, entity.getId());

                stage.show();

            } catch (IOException e) {
                DialogManager.showErrorDialog("Error", "Произошла ошибка " + e.getMessage());
            }
        } else {
            DialogManager.showErrorDialog("Error", "Произошла ошибка просмотреть можно только после создания продукции!!!");
        }
    }

    @FXML
    private void onActiomCalculate(){
        if(entity != null && entity.getId() > 0) {
            Repository.getResult(new CalculationOutGoingProductionCommand(entity.getId()))
                    .subscribe(new AbstractCommandShowError<CalculationOutGoingProductionEntity>() {
                        @Override
                        public void onNext(CalculationOutGoingProductionEntity studentEntities) {

                            resultCostOutGoing.setText(String.format("Полные издержки по этому продукту составляют: %f", studentEntities.getTotalOutGoing()));
                        }
                    });
        } else {
            DialogManager.showErrorDialog("Error", "Произошла ошибка просмотреть можно только после создания продукции!!!");
        }
    }
}
