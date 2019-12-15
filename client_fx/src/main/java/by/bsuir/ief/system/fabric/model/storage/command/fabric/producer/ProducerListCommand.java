package by.bsuir.ief.system.fabric.model.storage.command.fabric.producer;

import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.storage.ConnectToServer;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommand;
import com.google.gson.Gson;
import connection.client_support.Request;
import connection.client_support.requestbody.NoContentRequestBody;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;

public class ProducerListCommand extends AbstractCommand<ProducerEntity[]> {

    @Override
    protected Request getRequest() throws Exception {
        Request.Builder builder = new Request.Builder()
                .body(new NoContentRequestBody())
                .addMethod("list.producer");

        builder = ConnectToServer.setConnectData(builder);

        return builder.build();
    }

    @Override
    public Observable<ProducerEntity[]> execute() {
        return super.getObservable()
                .map(response -> new Gson().fromJson(new String(response.getBody().bytes()), ProducerEntity[].class))
                .observeOn(JavaFxScheduler.platform());
    }
}
