package by.bsuir.ief.system.fabric.command.user;

import by.bsuir.ief.system.fabric.command.ICommand;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.util.CommandUtil;
import by.bsuir.ief.system.fabric.util.SecurityLogger;
import connection.CodeConnection;
import connection.server_support.Request;
import connection.server_support.Response;
import connection.server_support.response.JsonResponseBody;
import connection.server_support.response.NoContentResponseBody;

public class GetUserCommand implements ICommand {

    @Override
    public Response execute(Request request) {
        Response.Builder builder = new Response.Builder();

        try {

            UserEntity entity = SecurityLogger.getInstance().
                    getUser(request.getHeaders().getHeader(SecurityLogger.X_AUTH));

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
}
