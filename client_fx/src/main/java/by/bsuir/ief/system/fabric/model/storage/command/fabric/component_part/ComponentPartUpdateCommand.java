package by.bsuir.ief.system.fabric.model.storage.command.fabric.component_part;

import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.ConsumerEntity;
import by.bsuir.ief.system.fabric.model.storage.ConnectToServer;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommand;
import connection.client_support.Request;
import connection.client_support.requestbody.JsonRequestBody;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import org.jetbrains.annotations.NotNull;

public class ComponentPartUpdateCommand extends AbstractCommand<Boolean> {
    @NotNull
    private final ComponentPartEntity entity;

    public ComponentPartUpdateCommand(@NotNull final ComponentPartEntity entity) {
        this.entity = entity;
    }

    @Override
    protected Request getRequest() throws Exception {
        Request.Builder builder = new Request.Builder()
                .body(new JsonRequestBody(entity))
                .addMethod("update.component.part");

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
