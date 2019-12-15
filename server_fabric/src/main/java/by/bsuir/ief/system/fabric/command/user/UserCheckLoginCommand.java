package by.bsuir.ief.system.fabric.command.user;


import by.bsuir.ief.system.fabric.command.ICommand;
import by.bsuir.ief.system.fabric.model.repository.user.UserRepository;
import by.bsuir.ief.system.fabric.util.CommandUtil;
import by.bsuir.ief.system.fabric.util.RepositoryManager;
import connection.CodeConnection;
import connection.server_support.Request;
import connection.server_support.Response;
import connection.server_support.response.NoContentResponseBody;

public class UserCheckLoginCommand implements ICommand {

    @Override
    public Response execute(Request request) {

        Response.Builder builder = new Response.Builder();

        try {
            String checkLogin = new String(request.getBody().bytes());

            UserRepository userRepository = RepositoryManager.getInstance().getUserRepository();

            if (userRepository.checkLogin(checkLogin)) {

                builder.addMethod(request.getHeaders().getMethod())
                        .addMessage("Логин занят!")
                        .addCode(CodeConnection.BAD_REQUEST)
                        .addBody(new NoContentResponseBody());

            } else {

                builder.addMethod(request.getHeaders().getMethod())
                        .addMessage("Логин свободен")
                        .addCode(CodeConnection.OK_MESSAGE)
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
