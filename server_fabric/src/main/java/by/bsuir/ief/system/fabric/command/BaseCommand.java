package by.bsuir.ief.system.fabric.command;

import by.bsuir.ief.system.fabric.util.CommandUtil;
import connection.CodeConnection;
import connection.server_support.Request;
import connection.server_support.Response;
import connection.server_support.response.JsonResponseBody;
import connection.server_support.response.NoContentResponseBody;

abstract public class BaseCommand<T> implements ICommand {

    @Override
    public Response execute(Request request) {

        Response.Builder builder = new Response.Builder();

        try {

            T entity = getResponse();

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

    protected abstract T getResponse() throws Exception;
}
