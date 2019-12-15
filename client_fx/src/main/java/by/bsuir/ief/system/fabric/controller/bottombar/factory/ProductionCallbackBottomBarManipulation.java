package by.bsuir.ief.system.fabric.controller.bottombar.factory;

import by.bsuir.ief.system.fabric.controller.CreateEditDeleteBottomController;
import by.bsuir.ief.system.fabric.controller.DialogManager;
import by.bsuir.ief.system.fabric.controller.form.FormController;
import by.bsuir.ief.system.fabric.controller.table.TableController;
import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartWithProducerEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProductionEntity;
import by.bsuir.ief.system.fabric.model.storage.Repository;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommandShowError;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.component_part.ComponentPartCreateCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.component_part.ComponentPartDeleteCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.component_part.ComponentPartUpdateCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.production.ProductionCreateCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.production.ProductionDeleteCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.production.ProductionUpdateCommand;

public class ProductionCallbackBottomBarManipulation implements CreateEditDeleteBottomController.CallbackBottomBarManipulation {
    private final TableController<ProductionEntity> tableController;
    private final FormController<ProductionEntity> formController;

    public ProductionCallbackBottomBarManipulation(TableController<ProductionEntity> tableController, FormController<ProductionEntity> formController) {
        this.tableController = tableController;
        this.formController = formController;
    }

    @Override
    public void onCreate() {

        if (formController.isValid()) {
            formController.initEntity();
            Repository.getResult(new ProductionCreateCommand(formController.getEntity()))
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
            Repository.getResult(new ProductionUpdateCommand(formController.getEntity()))
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


        Repository.getResult(new ProductionDeleteCommand(formController.getEntity().getId()))
                .subscribe(new AbstractCommandShowError<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        DialogManager.showInfoDialog("Операция завершена успешно!", "Удаление завершено успешно");
                        reLoadTableData();
                    }
                });
    }

    private void reLoadTableData() {
        Repository.getsProduction(tableController);
    }
}
