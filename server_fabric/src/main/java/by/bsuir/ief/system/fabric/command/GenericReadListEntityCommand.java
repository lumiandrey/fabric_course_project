package by.bsuir.ief.system.fabric.command;

import by.bsuir.ief.system.fabric.model.repository.BaseRepository;
import by.bsuir.ief.system.fabric.util.CommandUtil;
import by.bsuir.ief.system.fabric.util.RepositoryManager;
import connection.CodeConnection;
import connection.server_support.Request;
import connection.server_support.Response;
import connection.server_support.response.JsonResponseBody;

import java.util.List;

public abstract class GenericReadListEntityCommand<T> implements ICommand {

    public abstract Class<T> getType();

    @Override
    public Response execute(Request request) {

        Response.Builder builder = new Response.Builder();

        try {

            BaseRepository<T> repository = RepositoryManager.getInstance().getRepository(getType());

            List<T> list = repository.read();

            builder.addMethod(request.getHeaders().getMethod())
                    .addMessage("Выполнено успешно!")
                    .addCode(CodeConnection.OK)
                    .addBody(new JsonResponseBody(list));

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
