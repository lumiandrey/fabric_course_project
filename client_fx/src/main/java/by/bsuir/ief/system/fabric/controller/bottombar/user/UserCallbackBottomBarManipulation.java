package by.bsuir.ief.system.fabric.controller.bottombar.user;

import by.bsuir.ief.system.fabric.controller.CreateEditDeleteBottomController;
import by.bsuir.ief.system.fabric.controller.DialogManager;
import by.bsuir.ief.system.fabric.controller.form.FormController;
import by.bsuir.ief.system.fabric.controller.table.TableController;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.storage.Repository;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommandShowError;
import by.bsuir.ief.system.fabric.model.storage.command.user.CreateUserCommand;
import by.bsuir.ief.system.fabric.model.storage.command.user.UserDeleteCommand;
import by.bsuir.ief.system.fabric.model.storage.command.user.UserUpdateCommand;

public class UserCallbackBottomBarManipulation implements CreateEditDeleteBottomController.CallbackBottomBarManipulation {
    private final TableController<UserEntity> tableController;
    private final FormController<UserEntity> formController;

    public UserCallbackBottomBarManipulation(TableController<UserEntity> tableController, FormController<UserEntity> formController) {
        this.tableController = tableController;
        this.formController = formController;
    }

    @Override
    public void onCreate() {

        if (formController.isValid()) {
            formController.initEntity();
            Repository.getResult(new CreateUserCommand(formController.getEntity()))
                    .subscribe(new AbstractCommandShowError<Boolean>() {
                           @Override
                public void onNext(Boolean aBoolean) {

                    DialogManager.showInfoDialog("Операция завершена успешно!", "Создание завершено успешно");
                    Repository.getsUser(tableController);
                }
            });
        }
    }

    @Override
    public void onEdit() {

        if (formController.isValid()) {
            formController.initEntity();
            Repository.getResult(new UserUpdateCommand(formController.getEntity()))
                    .subscribe(new AbstractCommandShowError<Boolean>() {
                @Override
                public void onNext(Boolean aBoolean) {

                    DialogManager.showInfoDialog("Операция завершена успешно!", "Обновление завершено успешно");
                    Repository.getsUser(tableController);
                }
            });
        }
    }

    @Override
    public void onDelete() {

        Repository.getResult(new UserDeleteCommand(formController.getEntity().getIdUser()))
                .subscribe(new AbstractCommandShowError<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        DialogManager.showInfoDialog("Операция завершена успешно!", "Удаление завершено успешно");
                        Repository.getsUser(tableController);
                    }
                });
    }
}
