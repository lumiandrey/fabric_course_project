package by.bsuir.ief.system.fabric.model.storage.command.user;

import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.storage.ConnectToServer;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommand;
import org.jetbrains.annotations.NotNull;
import connection.client_support.Request;
import connection.client_support.requestbody.JsonRequestBody;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;

public class UserUpdateCommand extends AbstractCommand<Boolean> {
    @NotNull
    private final UserEntity entity;

    public UserUpdateCommand(@NotNull final UserEntity entity) {
        this.entity = entity;
    }

    @Override
    protected Request getRequest() throws Exception {
        Request.Builder builder = new Request.Builder()
                .body(new JsonRequestBody(entity))
                .addMethod("updateuser");

        builder = ConnectToServer.setConnectData(builder);

        return builder.build();
    }

    @Override
    public Observable<Boolean> execute() {
        return super.getObservable()
                .map(response -> response.isSuccessfully())
                .observeOn(JavaFxScheduler.platform());
    }
}
