package by.bsuir.ief.system.fabric.model.storage.command;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class AbstractObserver<T> implements Observer<T> {

    private Disposable disposable;

    @Override
    public void onSubscribe(Disposable disposable) {
        this.disposable = disposable;
    }

    @Override
    public void onComplete() {
        if(disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
