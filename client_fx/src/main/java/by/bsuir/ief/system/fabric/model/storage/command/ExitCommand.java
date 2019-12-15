package by.bsuir.ief.system.fabric.model.storage.command;

import by.bsuir.ief.system.fabric.model.storage.ConnectToServer;
import connection.client_support.Request;
import connection.client_support.Response;
import connection.client_support.requestbody.NoContentRequestBody;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;

public class ExitCommand extends AbstractCommand<Boolean> {

    @Override
    protected Request getRequest() throws Exception {
        Request.Builder builder = new Request.Builder()
                .body(new NoContentRequestBody())
                .addMethod("exit");

        builder = ConnectToServer.setConnectData(builder);

        return builder.build();
    }

    @Override
    public Observable<Boolean> execute() {
        return super.getObservable()
                .map(Response::isSuccessfully)
                .doOnNext(aBoolean -> ConnectToServer.setToken(""))
                .observeOn(JavaFxScheduler.platform());
    }
}
