package connection.server_support.request;

import connection.enumer.MediaTypeRequest;

import java.io.BufferedReader;
import java.io.IOException;

final public class NoContentRequestBody extends RequestBody {

    NoContentRequestBody(long mHeaderContentLength) {
        super(mHeaderContentLength);
    }

    @Override
    void readTo(BufferedReader inputStream) throws IOException {
        // TODO: stub
    }

    @Override
    public MediaTypeRequest contentType() {
        return MediaTypeRequest.NO_CONTENT;
    }

    @Override
    public long contentLength() {
        return 0;
    }

    @Override
    public byte[] bytes() {
        return new byte[0];
    }

}
