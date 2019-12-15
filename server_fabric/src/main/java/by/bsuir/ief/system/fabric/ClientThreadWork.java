package by.bsuir.ief.system.fabric;

import by.bsuir.ief.system.fabric.command.user.AutorizationUserCommand;
import by.bsuir.ief.system.fabric.controller.AcceptNewClient;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.util.AccessDeniedException;
import by.bsuir.ief.system.fabric.util.CommandUtil;
import by.bsuir.ief.system.fabric.util.SecurityLogger;
import connection.CodeConnection;
import connection.server_support.Request;
import connection.server_support.Response;
import connection.server_support.ServerHelper;
import javafx.application.Platform;

import java.io.IOException;
import java.net.Socket;

public class ClientThreadWork implements Runnable {
    private final Socket socketCLient;
    private final ServerHelper serverHelper;
    private AcceptNewClient acceptNewClient;

    public ClientThreadWork(Socket socketClient, AcceptNewClient acceptNewClient) throws IOException {
        this.socketCLient = socketClient;

        serverHelper = new ServerHelper(socketClient);

        this.acceptNewClient = acceptNewClient;
    }

    @Override
    public void run() {

        try {

            Request request = serverHelper.readRequest();

            final String method = request.getHeaders().getMethod();

            Platform.runLater(() -> acceptNewClient.onActionClient(method + " " + socketCLient.toString()));
            String token = request.getHeaders().getHeader(SecurityLogger.X_AUTH);

            Response response = null;

            if("exit".equals(method)){

                UserEntity entity = SecurityLogger.getInstance().getUser(token);

                Platform.runLater(() -> acceptNewClient.onRemoveClient(entity));

                SecurityLogger.getInstance().removeTokenUser(token);

                response = CommandUtil.getResponce("exit", CodeConnection.OK, "Exit successfully");
            } else if("registration".equals(method)) {

                response = FactoryJsonCommand.generate(method).execute(request);

            } else {

                if (token != null && token.length() > 0) {

                    if (SecurityLogger.getInstance().isAccess(token)) {
                        response = FactoryJsonCommand.generate(method).execute(request);
                    } else {

                        response = CommandUtil.getResponce(method, CodeConnection.NOT_ACCEPTABLE, "This token not valid!!! Access denied!!!");
                    }

                } else if ("autorization".equals(method)) {

                    response = new AutorizationUserCommand(acceptNewClient).execute(request);
                } else {

                    response = CommandUtil.getResponce(method, CodeConnection.BAD_REQUEST, "Token is not valid!!!");
                }

            }
            serverHelper.responseSend(response);

        } catch (IOException e) {

            e.printStackTrace();
        } catch (AccessDeniedException e) {
            try {
                serverHelper.responseSend(CommandUtil.getResponce("Error!!!!", CodeConnection.BAD_REQUEST, e.getMessage()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {

            try {
                socketCLient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
