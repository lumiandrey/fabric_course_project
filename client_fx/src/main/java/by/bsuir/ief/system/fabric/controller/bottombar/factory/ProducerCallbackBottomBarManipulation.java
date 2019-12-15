package by.bsuir.ief.system.fabric.controller.bottombar.factory;

import by.bsuir.ief.system.fabric.controller.CreateEditDeleteBottomController;
import by.bsuir.ief.system.fabric.controller.DialogManager;
import by.bsuir.ief.system.fabric.controller.form.FormController;
import by.bsuir.ief.system.fabric.controller.table.TableController;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;
import by.bsuir.ief.system.fabric.model.storage.Repository;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommandShowError;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.producer.ProducerCreateCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.producer.ProducerDeleteCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.producer.ProducerUpdateCommand;

public class ProducerCallbackBottomBarManipulation implements CreateEditDeleteBottomController.CallbackBottomBarManipulation {
    private final TableController<ProducerEntity> tableController;
    private final FormController<ProducerEntity> formController;

    public ProducerCallbackBottomBarManipulation(TableController<ProducerEntity> tableController, FormController<ProducerEntity> formController) {
        this.tableController = tableController;
        this.formController = formController;
    }

    @Override
    public void onCreate() {

        if (formController.isValid()) {
            formController.initEntity();
            Repository.getResult(new ProducerCreateCommand(formController.getEntity()))
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
            Repository.getResult(new ProducerUpdateCommand(formController.getEntity()))
                    .subscribe(new AbstractCommandShowError<Boolean>() {
                @Override
                public void onNext(Boolean aBoolean) {

                    DialogManager.showInfoDialog("Операция завершена успешно!", "Обновление завершено успешно");
                    reloadTableData();
                }
            });
        }
    }

    @Override
    public void onDelete() {

        Repository.getResult(new ProducerDeleteCommand(formController.getEntity().getId()))
                .subscribe(new AbstractCommandShowError<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        DialogManager.showInfoDialog("Операция завершена успешно!", "Удаление завершено успешно");
                        reloadTableData();
                    }
                });
    }

    private void reloadTableData() {
        Repository.getsProducer(tableController);
    }
}
