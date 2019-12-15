package by.bsuir.ief.system.fabric.controller.bottombar.factory;

import by.bsuir.ief.system.fabric.controller.CreateEditDeleteBottomController;
import by.bsuir.ief.system.fabric.controller.DialogManager;
import by.bsuir.ief.system.fabric.controller.form.FormController;
import by.bsuir.ief.system.fabric.controller.table.TableController;
import by.bsuir.ief.system.fabric.model.entity.fabric.ConsumerEntity;
import by.bsuir.ief.system.fabric.model.storage.Repository;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommandShowError;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.consumer.ConsumerCreateCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.consumer.ConsumerDeleteCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.consumer.ConsumerUpdateCommand;

public class ConsumerCallbackBottomBarManipulation implements CreateEditDeleteBottomController.CallbackBottomBarManipulation {
    private final TableController<ConsumerEntity> tableController;
    private final FormController<ConsumerEntity> formController;

    public ConsumerCallbackBottomBarManipulation(TableController<ConsumerEntity> tableController, FormController<ConsumerEntity> formController) {
        this.tableController = tableController;
        this.formController = formController;
    }

    @Override
    public void onCreate() {

        if (formController.isValid()) {
            formController.initEntity();
            Repository.getResult(new ConsumerCreateCommand(formController.getEntity()))
                    .subscribe(new AbstractCommandShowError<Boolean>() {
                           @Override
                public void onNext(Boolean aBoolean) {

                    DialogManager.showInfoDialog("Операция завершена успешно!", "Создание завершено успешно");
                    reloadTableData();
                }
            });
        }
    }

    @Override
    public void onEdit() {

        if (formController.isValid()) {
            formController.initEntity();
            Repository.getResult(new ConsumerUpdateCommand(formController.getEntity()))
                    .subscribe(new AbstractCommandShowError<Boolean>() {
                @Override
                public void onNext(Boolean aBoolean) {

                    DialogManager.showInfoDialog("Операция завершена успешно!", "Обновление завершено успешно");
                    reloadTableData();
                }
            });
        }
    }

    private void reloadTableData() {
        Repository.getsConsumer(tableController);
    }

    @Override
    public void onDelete() {

        Repository.getResult(new ConsumerDeleteCommand(formController.getEntity().getId()))
                .subscribe(new AbstractCommandShowError<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        DialogManager.showInfoDialog("Операция завершена успешно!", "Удаление завершено успешно");
                        reloadTableData();
                    }
                });
    }
}
