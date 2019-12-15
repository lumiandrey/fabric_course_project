package by.bsuir.ief.system.fabric.command;

import by.bsuir.ief.system.fabric.util.CommandUtil;
import org.jetbrains.annotations.NotNull;
import connection.server_support.Request;
import connection.server_support.Response;

public class ErrorCommand implements ICommand {

    private final int code;

    private final String message;

    public ErrorCommand(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Response execute(@NotNull final Request request) {

        return CommandUtil.getResponce(
                request.getHeaders().getMethod(),
                code,
                message);
    }
}
