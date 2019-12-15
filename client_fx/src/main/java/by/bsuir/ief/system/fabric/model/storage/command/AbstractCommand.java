package by.bsuir.ief.system.fabric.model.storage.command;

import connection.client_support.ConnectionClient;
import connection.client_support.Request;
import connection.client_support.Response;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public abstract class AbstractCommand<T> implements ICommand<T> {

    protected abstract Request getRequest() throws Exception;

    protected Observable<Response> getObservable() {

        return Observable.create((ObservableOnSubscribe<Response>) observableEmitter -> {

            ConnectionClient client = new ConnectionClient.Builder().build();

            Response response = client.execute(getRequest()).execute();

            if(response.isSuccessfully()){

                System.out.println(response.getBody().toString().toString());
                observableEmitter.onNext(response);
            } else {

                observableEmitter.onError(new Exception(response.getMessage()));
            }

            observableEmitter.onComplete();
        }).subscribeOn(Schedulers.io());//Запросы будут происходить в отдельном потоке
    }
}
