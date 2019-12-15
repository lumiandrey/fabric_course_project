package by.bsuir.ief.system.fabric.command;

import by.bsuir.ief.system.fabric.util.CommandUtil;
import connection.CodeConnection;
import connection.server_support.Request;
import connection.server_support.Response;
import connection.server_support.response.JsonResponseBody;
import connection.server_support.response.NoContentResponseBody;

abstract public class BaseIdArgCommand<T> implements ICommand {

    @Override
    public Response execute(Request request) {

        int id;

        try {
            id = Integer.valueOf(new String(request.getBody().bytes()));
        } catch (Exception e) {

            return CommandUtil.getResponce(
                    request.getHeaders().getMethod(),
                    CodeConnection.BAD_REQUEST,
                    e.getMessage());
        }

        Response.Builder builder = new Response.Builder();

        try {

            T entity = get(id);

            if (entity == null) {

                builder.addMethod(request.getHeaders().getMethod())
                        .addMessage("Нет такого")
                        .addCode(CodeConnection.BAD_REQUEST)
                        .addBody(new NoContentResponseBody());
            } else {

                builder.addMethod(request.getHeaders().getMethod())
                        .addMessage("Выполнено успешно")
                        .addCode(CodeConnection.OK)
                        .addBody(new JsonResponseBody(entity));
            }

        } catch (Exception e) {

            return CommandUtil.getResponce(
                    request.getHeaders().getMethod(),
                    CodeConnection.INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }

        return builder.build();
    }

    protected abstract T get(int id) throws Exception;
}
