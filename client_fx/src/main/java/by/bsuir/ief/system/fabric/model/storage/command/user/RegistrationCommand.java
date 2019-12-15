package by.bsuir.ief.system.fabric.model.storage.command.user;

import by.bsuir.ief.system.fabric.model.entity.user.AutorizationUser;
import by.bsuir.ief.system.fabric.model.storage.ConnectToServer;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommand;
import org.jetbrains.annotations.NotNull;
import connection.client_support.Request;
import connection.client_support.requestbody.JsonRequestBody;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;

public class RegistrationCommand extends AbstractCommand<Boolean> {

    private final AutorizationUser autorizationUser;

    public RegistrationCommand(@NotNull final AutorizationUser autorizationUser) {
        this.autorizationUser = autorizationUser;
    }

    @Override
    protected Request getRequest() throws Exception {
        Request.Builder builder = new Request.Builder()
                .body(new JsonRequestBody(autorizationUser))
                .addMethod("registration");

        builder = ConnectToServer.setConnectData(builder);

        return builder.build();
    }

    @Override
    public Observable<Boolean> execute() {
        return super.getObservable()
                .map(response -> response.isSuccessfully())
                .observeOn(JavaFxScheduler.platform());//результат будет возвращаться в UI поток;
    }
}
