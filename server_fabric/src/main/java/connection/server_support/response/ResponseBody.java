package connection.server_support.response;

import connection.enumer.MediaTypeRequest;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.PrintStream;

public abstract class ResponseBody {

    public abstract MediaTypeRequest contentType();

    public abstract long contentLength();

    protected abstract  void writeTo(@NotNull final PrintStream outputStream) throws IOException;

    public void execute(@NotNull final PrintStream outputStream) throws IOException {

        writeTo(outputStream);
    }
}

