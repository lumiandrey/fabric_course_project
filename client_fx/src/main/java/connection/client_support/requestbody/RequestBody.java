package connection.client_support.requestbody;

import org.jetbrains.annotations.NotNull;
import connection.enumer.MediaTypeRequestResponce;

import java.io.IOException;
import java.io.PrintStream;

public abstract class RequestBody {

    /** Writes the content of this request to {@code sink}. */
    abstract void writeTo(@NotNull final PrintStream outputStream) throws IOException;

    public abstract MediaTypeRequestResponce contentType();

    public abstract long contentLength();

    public void execute(@NotNull final PrintStream outputStream) throws IOException {

        writeTo(outputStream);
    }
}
