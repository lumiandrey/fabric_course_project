package connection.client_support.responsebody;

import connection.enumer.MediaTypeRequestResponce;

import java.io.BufferedReader;
import java.io.IOException;

public class StringResponseBody extends ResponseBody {

    private String string;

    StringResponseBody(long mHeaderContentLength) {
        super(mHeaderContentLength);
    }

    @Override
    public MediaTypeRequestResponce contentType() {
        return MediaTypeRequestResponce.STRING;
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

    @Override
    public String toString() {
        return string;
    }
}
