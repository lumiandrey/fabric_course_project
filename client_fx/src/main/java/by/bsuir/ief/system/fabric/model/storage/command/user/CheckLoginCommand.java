package by.bsuir.ief.system.fabric.model.storage.command.user;

import by.bsuir.ief.system.fabric.model.storage.ConnectToServer;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommand;
import org.jetbrains.annotations.NotNull;
import connection.client_support.Request;
import connection.client_support.Response;
import connection.client_support.requestbody.StringRequestBody;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;

public class CheckLoginCommand extends AbstractCommand<Response> {

    @NotNull
    private final String login;

    public CheckLoginCommand(String login) {
        this.login = login;
    }

    @Override
    protected Request getRequest() throws Exception {
        Request.Builder builder = new Request.Builder()
                .body(new StringRequestBody(login))
                .addMethod("checklogin");

        builder = ConnectToServer.setConnectData(builder);

        return builder.build();
    }

    @Override
    public Observable<Response> execute() {
        return super.getObservable()
                .observeOn(JavaFxScheduler.platform());//результат будет возвращаться в UI поток;
    }
}
