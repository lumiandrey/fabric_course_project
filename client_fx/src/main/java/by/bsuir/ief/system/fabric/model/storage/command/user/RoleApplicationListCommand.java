package by.bsuir.ief.system.fabric.model.storage.command.user;

import by.bsuir.ief.system.fabric.model.entity.user.RoleApplicationEntity;
import by.bsuir.ief.system.fabric.model.storage.ConnectToServer;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommand;
import com.google.gson.Gson;
import connection.client_support.Request;
import connection.client_support.requestbody.NoContentRequestBody;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;

public class RoleApplicationListCommand extends AbstractCommand<RoleApplicationEntity[]> {
    @Override
    protected Request getRequest() throws Exception {
        Request.Builder builder = new Request.Builder()
                .body(new NoContentRequestBody())
                .addMethod("getlistroleapplication");

        builder = ConnectToServer.setConnectData(builder);

        return builder.build();
    }

    @Override
    public Observable<RoleApplicationEntity[]> execute() {
        return getObservable()
                .map(response -> new Gson().fromJson(new String(response.getBody().bytes()), RoleApplicationEntity[].class))
                .observeOn(JavaFxScheduler.platform());
    }
}
