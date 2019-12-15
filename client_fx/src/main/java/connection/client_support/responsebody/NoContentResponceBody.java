package connection.client_support.responsebody;

import connection.enumer.MediaTypeRequestResponce;

import java.io.BufferedReader;

public class NoContentResponceBody extends ResponseBody {

    NoContentResponceBody(long mHeaderContentLength) {
        super(mHeaderContentLength);
    }

    @Override
    public MediaTypeRequestResponce contentType() {
        return MediaTypeRequestResponce.NO_CONTENT;
    }

    @Override
    protected void readTo(BufferedReader inputStream) {

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
