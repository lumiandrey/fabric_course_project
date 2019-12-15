package connection.server_support.request;

import connection.enumer.MediaTypeRequest;

public class JsonRequestBody extends StringRequestBody {

    private String jsonRead;

    protected JsonRequestBody(long mHeaderContentLength) {
        super(mHeaderContentLength);
    }

    @Override
    public MediaTypeRequest contentType() {
        return MediaTypeRequest.JSON;
    }

}
