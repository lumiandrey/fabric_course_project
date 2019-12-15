package by.bsuir.ief.system.fabric.controller.bottombar.factory;

import by.bsuir.ief.system.fabric.controller.CreateEditDeleteBottomController;
import by.bsuir.ief.system.fabric.controller.DialogManager;
import by.bsuir.ief.system.fabric.controller.form.FormController;
import by.bsuir.ief.system.fabric.controller.table.TableController;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingConstEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingDynamicEntity;
import by.bsuir.ief.system.fabric.model.storage.Repository;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommandShowError;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.out_going_cost.*;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.producer.ProducerCreateCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.producer.ProducerDeleteCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.producer.ProducerUpdateCommand;

public class OutGoingCostCallbackBottomBarManipulation implements CreateEditDeleteBottomController.CallbackBottomBarManipulation {
    private final TableController<OutGoingConstEntity> tableController;
    private final FormController<OutGoingConstEntity> formController;

    public OutGoingCostCallbackBottomBarManipulation(TableController<OutGoingConstEntity> tableController, FormController<OutGoingConstEntity> formController) {
        this.tableController = tableController;
        this.formController = formController;
    }

    @Override
    public void onCreate() {

        if (formController.isValid()) {
            formController.initEntity();
            Repository.getResult(new OutGoingConstCreateCommand(formController.getEntity()))
                    .subscribe(new AbstractCommandShowError<Boolean>() {
                           @Override
                public void onNext(Boolean aBoolean) {

                    DialogManager.showInfoDialog("Операция завершена успешно!", "Создание завершено успешно");
                               reLoadTableData();
                           }
            });
        }
    }

    @Override
    public void onEdit() {

        if (formController.isValid()) {
            formController.initEntity();
            Repository.getResult(new OutGoingConstUpdateCommand(formController.getEntity()))
                    .subscribe(new AbstractCommandShowError<Boolean>() {
                @Override
                public void onNext(Boolean aBoolean) {

                    DialogManager.showInfoDialog("Операция завершена успешно!", "Обновление завершено успешно");
                    reLoadTableData();
                }
            });
        }
    }

    @Override
    public void onDelete() {

        Repository.getResult(new OutGoingConstDeleteCommand(formController.getEntity().getId()))
                .subscribe(new AbstractCommandShowError<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        DialogManager.showInfoDialog("Операция завершена успешно!", "Удаление завершено успешно");
                        reLoadTableData();
                    }
                });
    }

    private void reLoadTableData() {
        Repository.getsOutGoingCost(tableController);
    }
}
