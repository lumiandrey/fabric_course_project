package by.bsuir.ief.system.fabric.command;

import connection.server_support.Request;
import connection.server_support.Response;
import org.jetbrains.annotations.NotNull;

public interface ICommand {

    Response execute(@NotNull final Request request);
}
