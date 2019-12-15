package connection.server_support.request;

import connection.enumer.MediaTypeRequest;

import java.io.BufferedReader;
import java.io.IOException;

public class StringRequestBody extends RequestBody {

    private String string;

    StringRequestBody(long mHeaderContentLength) {
        super(mHeaderContentLength);
    }

    @Override
    public MediaTypeRequest contentType() {
        return MediaTypeRequest.STRING;
    }

    @Override
    protected void readTo(BufferedReader inputStream) throws IOException {

        string = inputStream.readLine();
    }

    @Override
    public long contentLength() {
        return string.length();
    }

    @Override
    public byte[] bytes() {
        return string.getBytes();
    }
}
