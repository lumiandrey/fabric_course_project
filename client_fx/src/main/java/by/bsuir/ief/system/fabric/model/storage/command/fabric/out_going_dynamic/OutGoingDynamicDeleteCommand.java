package by.bsuir.ief.system.fabric.model.storage.command.fabric.out_going_dynamic;


import by.bsuir.ief.system.fabric.model.storage.ConnectToServer;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommand;
import connection.client_support.Request;
import connection.client_support.requestbody.StringRequestBody;
import io.reactivex.Observable;

public class OutGoingDynamicDeleteCommand extends AbstractCommand<Boolean> {

    private final int id;

    public OutGoingDynamicDeleteCommand(int id) {
        this.id = id;
    }

    @Override
    protected Request getRequest() throws Exception {
        Request.Builder builder = new Request.Builder()
                .body(new StringRequestBody(String.valueOf(id)))
                .addMethod("delete.out.going.dynamic.id");

        builder = ConnectToServer.setConnectData(builder);

        return builder.build();
    }

    @Override
    public Observable<Boolean> execute() {
        return null;
    }
}
