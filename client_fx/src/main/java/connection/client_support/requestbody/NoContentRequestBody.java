package connection.client_support.requestbody;

import connection.enumer.MediaTypeRequestResponce;

import java.io.IOException;
import java.io.PrintStream;

final public class NoContentRequestBody  extends RequestBody {


    @Override
    void writeTo(PrintStream outputStream) throws IOException {
        // TODO: stub
    }

    @Override
    public MediaTypeRequestResponce contentType() {
        return MediaTypeRequestResponce.NO_CONTENT;
    }

    @Override
    public long contentLength() {
        return 0;
    }
}
