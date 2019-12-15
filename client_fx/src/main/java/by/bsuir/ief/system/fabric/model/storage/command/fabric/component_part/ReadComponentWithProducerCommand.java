package by.bsuir.ief.system.fabric.model.storage.command.fabric.component_part;

import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartWithProducerEntity;
import by.bsuir.ief.system.fabric.model.storage.ConnectToServer;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommand;
import com.google.gson.Gson;
import connection.client_support.Request;
import connection.client_support.requestbody.NoContentRequestBody;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;

public class ReadComponentWithProducerCommand extends AbstractCommand<ComponentPartWithProducerEntity[]> {

    @Override
    protected Request getRequest() throws Exception {
        Request.Builder builder = new Request.Builder()
                .body(new NoContentRequestBody())
                .addMethod("list.component.part.producer");

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
