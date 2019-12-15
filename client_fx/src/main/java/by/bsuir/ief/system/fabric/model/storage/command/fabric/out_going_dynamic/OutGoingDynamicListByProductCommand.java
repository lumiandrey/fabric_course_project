package by.bsuir.ief.system.fabric.model.storage.command.fabric.out_going_dynamic;

import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingDynamicEntity;
import by.bsuir.ief.system.fabric.model.storage.ConnectToServer;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommand;
import com.google.gson.Gson;
import connection.client_support.Request;
import connection.client_support.requestbody.NoContentRequestBody;
import connection.client_support.requestbody.StringRequestBody;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;

public class OutGoingDynamicListByProductCommand extends AbstractCommand<OutGoingDynamicEntity[]> {

    private final int id;

    public OutGoingDynamicListByProductCommand(int id) {
        this.id = id;
    }

    @Override
    protected Request getRequest() throws Exception {
        Request.Builder builder = new Request.Builder()
                .body(new StringRequestBody(String.valueOf(id)))
                .addMethod("list.out.going.dynamic.product.id");

        builder = ConnectToServer.setConnectData(builder);

        return builder.build();
    }

    @Override
    public Observable<OutGoingDynamicEntity[]> execute() {
        return super.getObservable()
                .map(response -> new Gson().fromJson(new String(response.getBody().bytes()), OutGoingDynamicEntity[].class))
                .observeOn(JavaFxScheduler.platform());
    }
}
