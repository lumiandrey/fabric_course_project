package by.bsuir.ief.system.fabric.model.storage.command;

public class EmptyObserver<T> extends AbstractObserver<T> {
    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
