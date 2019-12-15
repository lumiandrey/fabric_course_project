package by.bsuir.ief.system.fabric.command.user;

import by.bsuir.ief.system.fabric.command.ICommand;
import by.bsuir.ief.system.fabric.command.user.entity.AutorizationRegistrationUser;
import by.bsuir.ief.system.fabric.controller.AcceptNewClient;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.repository.user.UserRepository;
import by.bsuir.ief.system.fabric.util.CommandUtil;
import by.bsuir.ief.system.fabric.util.RepositoryManager;
import by.bsuir.ief.system.fabric.util.SecurityLogger;
import com.google.gson.Gson;
import connection.CodeConnection;
import connection.server_support.Request;
import connection.server_support.Response;
import connection.server_support.response.NoContentResponseBody;
import connection.server_support.response.StringResponseBody;
import javafx.application.Platform;

public class AutorizationUserCommand implements ICommand {

    private AcceptNewClient client;

    public AutorizationUserCommand(AcceptNewClient acceptNewClient) {
        client = acceptNewClient;
    }

    @Override
    public Response execute(Request request) {

        AutorizationRegistrationUser autorizationRegistrationUser = new Gson().fromJson(new String(request.getBody().bytes()), AutorizationRegistrationUser.class);

        Response.Builder builder = new Response.Builder();

        try {

            UserRepository userRepository = RepositoryManager.getInstance().getUserRepository();

            UserEntity entity = userRepository.getPairLoginPassword(autorizationRegistrationUser.getLogin(), autorizationRegistrationUser.getPassword());

            if (entity == null) {

                builder.addMethod(request.getHeaders().getMethod())
                        .addMessage("Нет такого")
                        .addCode(CodeConnection.BAD_REQUEST)
                        .addBody(new NoContentResponseBody());
            } else if (entity.getStatus() > 0) {

                String token = SecurityLogger.getInstance().addUserToken(entity);

                Platform.runLater(() -> client.onAcceptClient(entity));

                builder.addMethod(request.getHeaders().getMethod())
                        .addHeader("X-Auth", token)
                        .addMessage("Выполнено успешно!")
                        .addCode(CodeConnection.OK)
                        .addBody(new StringResponseBody(entity.getRoleApplication().getName()));
            } else {

                builder.addMethod(request.getHeaders().getMethod())
                        .addMessage("Пользователь заблокирован")
                        .addCode(CodeConnection.BAD_REQUEST)
                        .addBody(new NoContentResponseBody());
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
