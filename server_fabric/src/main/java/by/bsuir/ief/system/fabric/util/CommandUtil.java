package by.bsuir.ief.system.fabric.util;

import connection.CodeResponse;
import connection.server_support.Response;
import connection.server_support.response.NoContentResponseBody;
import org.jetbrains.annotations.NotNull;

public class CommandUtil {

    public static Response getResponce(@NotNull final String method,
                                       @NotNull @CodeResponse final  int code,
                                       @NotNull final String message) {

        return new Response.Builder()
                .addBody(new NoContentResponseBody())
                .addCode(code)
                .addMessage(message)
                .addMethod(method)
                .build();
    }
}
