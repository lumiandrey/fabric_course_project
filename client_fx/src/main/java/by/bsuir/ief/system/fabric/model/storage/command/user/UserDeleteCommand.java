package by.bsuir.ief.system.fabric.model.storage.command.user;


import by.bsuir.ief.system.fabric.model.storage.ConnectToServer;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommand;
import connection.client_support.Request;
import connection.client_support.requestbody.StringRequestBody;
import io.reactivex.Observable;

public class UserDeleteCommand extends AbstractCommand<Boolean> {

    private final int id;

    public UserDeleteCommand(int id) {
        this.id = id;
    }

    @Override
    protected Request getRequest() throws Exception {
        Request.Builder builder = new Request.Builder()
                .body(new StringRequestBody(String.valueOf(id)))
                .addMethod("deleteuser");

        builder = ConnectToServer.setConnectData(builder);

        return builder.build();
    }

    @Override
    public Observable<Boolean> execute() {
        return null;
    }
}
