package by.bsuir.ief.system.fabric.command;

import by.bsuir.ief.system.fabric.model.repository.BaseRepository;
import by.bsuir.ief.system.fabric.util.CommandUtil;
import by.bsuir.ief.system.fabric.util.RepositoryManager;
import connection.CodeConnection;
import connection.server_support.Request;
import connection.server_support.Response;
import connection.server_support.response.NoContentResponseBody;

public abstract class GenericDeleteEntityByIdCommand<T> implements ICommand {

    protected abstract Class<T> getType();

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

            BaseRepository<T> repository = RepositoryManager.getInstance().getRepository(getType());

            boolean result = repository.delete(id);

            if (result) {

                builder.addMethod(request.getHeaders().getMethod())
                        .addMessage("Выполнено успешно!")
                        .addCode(CodeConnection.OK)
                        .addBody(new NoContentResponseBody());

            } else {

                builder.addMethod(request.getHeaders().getMethod())
                        .addMessage("Записи не найдено!")
                        .addCode(CodeConnection.NOT_ACCEPTABLE)
                        .addBody(new NoContentResponseBody());
            }

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
