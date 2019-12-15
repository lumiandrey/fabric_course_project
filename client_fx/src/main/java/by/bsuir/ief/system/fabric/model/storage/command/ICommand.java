package by.bsuir.ief.system.fabric.model.storage.command;

import io.reactivex.Observable;

public interface ICommand<T> {

    Observable<T> execute();
}
