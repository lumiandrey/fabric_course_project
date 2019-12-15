package by.bsuir.ief.system.fabric.model.storage.command.fabric.producer;

import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.storage.ConnectToServer;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommand;
import connection.client_support.Request;
import connection.client_support.requestbody.JsonRequestBody;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import org.jetbrains.annotations.NotNull;

public class ProducerCreateCommand extends AbstractCommand<Boolean> {

    @NotNull
    private final ProducerEntity entity;

    public ProducerCreateCommand(@NotNull final ProducerEntity entity) {
        this.entity = entity;
    }

    @Override
    protected Request getRequest() throws Exception {
        Request.Builder builder = new Request.Builder()
                .body(new JsonRequestBody(entity))
                .addMethod("add.producer");

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
