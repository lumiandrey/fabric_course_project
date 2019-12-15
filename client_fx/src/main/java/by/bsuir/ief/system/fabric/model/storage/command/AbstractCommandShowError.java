package by.bsuir.ief.system.fabric.model.storage.command;

import by.bsuir.ief.system.fabric.controller.DialogManager;

public abstract class AbstractCommandShowError<T> extends AbstractObserver<T> {

    @Override
    public void onError(Throwable throwable) {
        DialogManager.showErrorDialog("Error", "Произошла ошибка " + throwable.getMessage());
    }
}
