package connection.client_support.responsebody;

import connection.enumer.MediaTypeRequestResponce;

public class JsonResponseBody extends StringResponseBody {

    protected JsonResponseBody(long mHeaderContentLength) {
        super(mHeaderContentLength);
    }

    @Override
    public MediaTypeRequestResponce contentType() {
        return MediaTypeRequestResponce.JSON;
    }
}
