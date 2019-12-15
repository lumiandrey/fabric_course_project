package by.bsuir.ief.system.fabric.command.user;

import by.bsuir.ief.system.fabric.command.ICommand;
import by.bsuir.ief.system.fabric.command.user.entity.AutorizationRegistrationUser;
import by.bsuir.ief.system.fabric.model.RoleApp;
import by.bsuir.ief.system.fabric.model.entity.user.RoleApplicationEntity;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.repository.user.RoleApplicationRepository;
import by.bsuir.ief.system.fabric.model.repository.user.UserRepository;
import by.bsuir.ief.system.fabric.util.CommandUtil;
import by.bsuir.ief.system.fabric.util.RepositoryManager;
import com.google.gson.Gson;
import connection.CodeConnection;
import connection.server_support.Request;
import connection.server_support.Response;
import connection.server_support.response.NoContentResponseBody;

public class RegistrationCommand implements ICommand {

    @Override
    public Response execute(Request request) {

        AutorizationRegistrationUser autorizationRegistrationUser =
                new Gson().fromJson(new String(request.getBody().bytes()), AutorizationRegistrationUser.class);

        Response.Builder builder = new Response.Builder();

        try {

            UserRepository userRepository = RepositoryManager.getInstance().getUserRepository();

            UserEntity entity = userRepository.getPairLoginPassword(autorizationRegistrationUser.getLogin(), autorizationRegistrationUser.getPassword());

            System.out.println("read login pass " + entity);

            RoleApplicationRepository roleApplicationRepository = RepositoryManager.getInstance().getUserRoleApplicationRepository();

            RoleApplicationEntity roleApplicationEntity = roleApplicationRepository.getRegistrationRole(RoleApp.USER.name());

            if (entity != null || roleApplicationEntity == null) {

                builder.addMethod(request.getHeaders().getMethod())
                        .addMessage("Ошибка регистрации")
                        .addCode(CodeConnection.BAD_REQUEST)
                        .addBody(new NoContentResponseBody());
            } else {

                entity = new UserEntity();

                entity.setStatus(0);
                entity.setLogin(autorizationRegistrationUser.getLogin());
                entity.setPassword(autorizationRegistrationUser.getPassword());
                entity.setRoleApplication(roleApplicationEntity);

                long idUser = RepositoryManager.getInstance().getUserRepository().create(entity);

                if (idUser > 0) {
                    builder.addMethod(request.getHeaders().getMethod())
                            .addMessage("Выполнено успешно!")
                            .addCode(CodeConnection.OK)
                            .addBody(new NoContentResponseBody());
                } else {
                    builder.addMethod(request.getHeaders().getMethod())
                            .addMessage("Ошибка регистрации")
                            .addCode(CodeConnection.BAD_REQUEST)
                            .addBody(new NoContentResponseBody());
                }
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
