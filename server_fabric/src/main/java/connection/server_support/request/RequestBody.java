package connection.server_support.request;

import connection.enumer.MediaTypeRequest;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class RequestBody {

    protected final long mHeaderContentLength;

    protected RequestBody(final long mHeaderContentLength) {
        this.mHeaderContentLength = mHeaderContentLength;
    }

    /** Writes the content of this request to {@code sink}. */
    abstract void readTo(@NotNull final BufferedReader inputStream) throws IOException;

    public abstract MediaTypeRequest contentType();

    public abstract long contentLength();

    abstract public byte[] bytes();

    public void execute(@NotNull final BufferedReader inputStream) throws IOException {

       readTo(inputStream);
    }
}
