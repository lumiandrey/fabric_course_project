package by.bsuir.ief.system.fabric.model.storage.command.user;

import by.bsuir.ief.system.fabric.model.entity.user.AutorizationUser;
import by.bsuir.ief.system.fabric.model.storage.ConnectToServer;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommand;
import org.jetbrains.annotations.NotNull;
import connection.client_support.Request;
import connection.client_support.requestbody.JsonRequestBody;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;

public class AutorizationCommand extends AbstractCommand<String> {

    private final AutorizationUser autorizationUser;

    public AutorizationCommand(@NotNull AutorizationUser autorizationUser) {
        this.autorizationUser = autorizationUser;
    }

    @Override
    protected Request getRequest() throws Exception {

        Request.Builder builder = new Request.Builder()
                .body(new JsonRequestBody(autorizationUser))
                .addMethod("autorization");

        builder = ConnectToServer.setConnectData(builder);

        return builder.build();
    }

    @Override
    public Observable<String> execute() {
        return super.getObservable()
                .doOnNext(response -> {
                    ConnectToServer.setToken(response.getHeaders().getHeader("X-Auth"));
                })
                .map(response -> new String(response.getBody().bytes()))
                .observeOn(JavaFxScheduler.platform());//результат будет возвращаться в UI поток;
    }
}
