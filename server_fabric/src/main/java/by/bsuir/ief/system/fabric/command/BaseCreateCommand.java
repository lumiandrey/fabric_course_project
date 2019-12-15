package by.bsuir.ief.system.fabric.command;

import by.bsuir.ief.system.fabric.util.CommandUtil;
import com.google.gson.GsonBuilder;
import connection.CodeConnection;
import connection.server_support.Request;
import connection.server_support.Response;
import connection.server_support.response.NoContentResponseBody;

abstract public class BaseCreateCommand<T> implements ICommand {

    public abstract Class<T> getType();


    protected abstract void onCreate(T entity) throws Exception;

    @Override
    public Response execute(Request request) {

        Response.Builder builder = new Response.Builder();

        try {

            T entity = new GsonBuilder().setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz")
                    .create().fromJson(new String(request.getBody().bytes()), getType());

            onCreate(entity);

            builder.addMethod(request.getHeaders().getMethod())
                    .addMessage("Выполнено успешно!")
                    .addCode(CodeConnection.OK)
                    .addBody(new NoContentResponseBody());

        } catch (Exception e) {

            e.printStackTrace();

            return CommandUtil.getResponce(
                    request.getHeaders().getMethod(),
                    CodeConnection.INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }

        return builder.build();
    }

}
