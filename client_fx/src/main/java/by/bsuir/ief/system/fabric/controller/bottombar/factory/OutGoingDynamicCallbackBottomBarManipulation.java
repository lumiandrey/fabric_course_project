package by.bsuir.ief.system.fabric.controller.bottombar.factory;

import by.bsuir.ief.system.fabric.controller.CreateEditDeleteBottomController;
import by.bsuir.ief.system.fabric.controller.DialogManager;
import by.bsuir.ief.system.fabric.controller.form.FormController;
import by.bsuir.ief.system.fabric.controller.table.TableController;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingDynamicEntity;
import by.bsuir.ief.system.fabric.model.storage.Repository;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommandShowError;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.out_going_dynamic.*;

public class OutGoingDynamicCallbackBottomBarManipulation implements CreateEditDeleteBottomController.CallbackBottomBarManipulation {
    private final TableController<OutGoingDynamicEntity> tableController;
    private final FormController<OutGoingDynamicEntity> formController;

    public OutGoingDynamicCallbackBottomBarManipulation(TableController<OutGoingDynamicEntity> tableController, FormController<OutGoingDynamicEntity> formController) {
        this.tableController = tableController;
        this.formController = formController;
    }

    @Override
    public void onCreate() {

        if (formController.isValid()) {
            formController.initEntity();
            Repository.getResult(new OutGoingDynamicCreateCommand(formController.getEntity()))
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
            Repository.getResult(new OutGoingDynamicUpdateCommand(formController.getEntity()))
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

        Repository.getResult(new OutGoingDynamicDeleteCommand(formController.getEntity().getId()))
                .subscribe(new AbstractCommandShowError<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        DialogManager.showInfoDialog("Операция завершена успешно!", "Удаление завершено успешно");
                        reloadTableData();
                    }
                });
    }

    private void reloadTableData() {
        Repository.getsOutGoingDynamic(tableController);
    }
}
