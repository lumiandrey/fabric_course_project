package connection.client_support.responsebody;

import org.jetbrains.annotations.NotNull;
import connection.enumer.MediaTypeRequestResponce;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class ResponseBody {

    protected ResponseBody(long mHeaderContentLength) {
        this.mHeaderContentLength = mHeaderContentLength;
    }

    static public ResponseBody createSelf(long contentLength){
        throw new RuntimeException("No implement method");
    }

    public abstract MediaTypeRequestResponce contentType();

    protected final long mHeaderContentLength;

    protected abstract  void readTo(@NotNull final BufferedReader inputStream) throws IOException;

    public abstract long contentLength();

    abstract public byte[] bytes();

    public void execute(@NotNull final BufferedReader inputStream) throws IOException {

        readTo(inputStream);
    }
}

