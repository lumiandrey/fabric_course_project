package connection.server_support.response;

import connection.enumer.MediaTypeRequest;

import java.io.PrintStream;

public class NoContentResponseBody extends ResponseBody {

    @Override
    public MediaTypeRequest contentType() {
        return MediaTypeRequest.NO_CONTENT;
    }

    @Override
    public long contentLength() {
        return 0;
    }

    @Override
    protected void writeTo(PrintStream outputStream) {

    }
}
