package by.bsuir.ief.system.fabric.controller.form.factory;

import by.bsuir.ief.system.fabric.FXLoaderController;
import by.bsuir.ief.system.fabric.controller.DialogManager;
import by.bsuir.ief.system.fabric.controller.form.BaseFormController;
import by.bsuir.ief.system.fabric.controller.table.ChoiceTableController;
import by.bsuir.ief.system.fabric.controller.table.TableController;
import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartWithProducerEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingConstEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;
import by.bsuir.ief.system.fabric.model.storage.Repository;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommandShowError;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.producer.ProducerListCommand;
import by.bsuir.ief.system.fabric.util.ListUtil;
import by.bsuir.ief.system.fabric.util.ParseUtils;
import by.bsuir.ief.system.fabric.util.ValidUtil;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ComponentPartFomController extends BaseFormController<ComponentPartWithProducerEntity> {

    @FXML
    private Label idLabel;
    @FXML
    private TextField nameField;
    @FXML
    private TextField costOutGoing;

    @FXML
    private Label nameProducer;

    @FXML
    private void handleClear() {
        clearField();
    }

    protected void clearField() {

        idLabel.setText("0");

        nameField.setText("");

        costOutGoing.setText("0.0");

        nameProducer.setText("");
    }

    @Override
    protected String isValidMessage() {
        String errorMessage = "";

        errorMessage += ValidUtil.checkFieldStringNotEmpty(
                "Название комплектующего не заполнено!\n",
                nameField
        );

        errorMessage += ValidUtil.checkDoubleValid(
                costOutGoing,
                "Поле \"Стоимость комплектующего\" обязательно к заполнению\n",
                "Ошибка ввода \"Стоимость комплектующего\"\n"
        );

        if (super.entity.getIdProducer() < 0){
            errorMessage += "Вы не выбрали поставщика! это являается обязательным параметром для добавления/редактирования комплектующего\n";
        }

        return errorMessage;
    }

    @Override
    protected void initField(ComponentPartWithProducerEntity entity) {
        idLabel.setText(Integer.toString(entity.getId()));

        nameField.setText(entity.getName());

        costOutGoing.setText(String.valueOf(entity.getCost()));

        nameProducer.setText(
                entity.getProducer() != null ?
                        entity.getProducer().getName() != null ?
                                entity.getProducer().getName() : ""
                        : "");
    }

    @Override
    protected void initEntity(ComponentPartWithProducerEntity entity) {
        entity.setName(nameField.getText());
        entity.setCost(ParseUtils.parseDouble(costOutGoing));
    }

    @Override
    protected ComponentPartWithProducerEntity create() {
        return new ComponentPartWithProducerEntity();
    }

    @FXML
    private void onProducer(){

        Repository.getResult(new ProducerListCommand())
                .subscribe(new AbstractCommandShowError<ProducerEntity[]>() {
                    @Override
                    public void onNext(ProducerEntity[] courseEntities) {

                        try {
                            ChoiceTableController<ProducerEntity> choiceTableController = (ChoiceTableController) FXLoaderController.loadTableController("ChoiceTableView");
                            TableController<ProducerEntity> courseEntityTableController = FXLoaderController.loadBusinessTableController("ProducerTableView");

                            choiceTableController.setController(courseEntityTableController);
                            Stage dialogStage = new Stage();

                            Scene scene = new Scene(choiceTableController.getLayout());
                            dialogStage.setScene(scene);
                            choiceTableController.setPrimaryStage(dialogStage);
                            choiceTableController.setMultiSelect(false);
                            choiceTableController.setList(ListUtil.parseArray(courseEntities));

                            dialogStage.showAndWait();

                            if(choiceTableController.isOk()){

                                ProducerEntity producerEntity = choiceTableController.getSelect();
                                entity = entity != null ? entity : create();

                                nameProducer.setText(producerEntity.getName());

                                entity.setIdProducer(producerEntity.getId());
                            }
                        } catch (Exception ex){

                            DialogManager.showErrorDialog("Ошибка подгрузки данных", ex.getMessage());
                        }
                    }
                });
    }
}
