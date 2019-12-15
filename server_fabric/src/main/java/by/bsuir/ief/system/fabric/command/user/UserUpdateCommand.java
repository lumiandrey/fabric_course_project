package by.bsuir.ief.system.fabric.command.user;

import by.bsuir.ief.system.fabric.command.ICommand;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.repository.BaseRepository;
import by.bsuir.ief.system.fabric.util.CommandUtil;
import by.bsuir.ief.system.fabric.util.RepositoryManager;
import by.bsuir.ief.system.fabric.util.SecurityLogger;
import com.google.gson.GsonBuilder;
import connection.CodeConnection;
import connection.server_support.Request;
import connection.server_support.Response;
import connection.server_support.response.NoContentResponseBody;

public class UserUpdateCommand implements ICommand {


    @Override
    public Response execute(Request request) {
        Response.Builder builder = new Response.Builder();

        try {

            UserEntity entity = new GsonBuilder().create().fromJson(new String(request.getBody().bytes()), UserEntity.class);

            BaseRepository<UserEntity> repository = RepositoryManager.getInstance().getRepository(UserEntity.class);

            if (repository.update(entity) == null) {

                builder.addMethod(request.getHeaders().getMethod())
                        .addMessage("Failed update entity")
                        .addCode(CodeConnection.BAD_REQUEST)
                        .addBody(new NoContentResponseBody());

            } else {

                if (entity.getStatus() < 1)
                    SecurityLogger.getInstance().deleteUser(entity);

                builder.addMethod(request.getHeaders().getMethod())
                        .addMessage("Выполнено успешно!")
                        .addCode(CodeConnection.OK)
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
