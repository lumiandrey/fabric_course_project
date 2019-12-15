package by.bsuir.ief.system.fabric.model.storage.command.fabric.component_part;

import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartWithProducerEntity;
import by.bsuir.ief.system.fabric.model.storage.ConnectToServer;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommand;
import com.google.gson.Gson;
import connection.client_support.Request;
import connection.client_support.requestbody.NoContentRequestBody;
import connection.client_support.requestbody.StringRequestBody;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;

public class ReadComponentWithProducerByProductCommand extends AbstractCommand<ComponentPartWithProducerEntity[]> {

    private final int id;

    public ReadComponentWithProducerByProductCommand(int id) {
        this.id = id;
    }

    @Override
    protected Request getRequest() throws Exception {
        Request.Builder builder = new Request.Builder()
                .body(new StringRequestBody(String.valueOf(id)))
                .addMethod("list.component.part.production");

        builder = ConnectToServer.setConnectData(builder);

        return builder.build();
    }

    @Override
    public Observable<ComponentPartWithProducerEntity[]> execute() {
        return super.getObservable()
                .map(response -> new Gson().fromJson(new String(response.getBody().bytes()), ComponentPartWithProducerEntity[].class))
                .observeOn(JavaFxScheduler.platform());
    }
}
