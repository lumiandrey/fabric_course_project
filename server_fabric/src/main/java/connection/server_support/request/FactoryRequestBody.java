package connection.server_support.request;

import connection.enumer.MediaTypeRequest;
import org.jetbrains.annotations.NotNull;

public class FactoryRequestBody {

    public static RequestBody create(@NotNull final MediaTypeRequest mediaTypeRequest, final long contentLength){

        RequestBody requestBody;

        switch (mediaTypeRequest){
            case NO_CONTENT:{

                requestBody = new NoContentRequestBody(contentLength);
            } break;
            case FILE:{

                throw new RuntimeException("No impl");
            }
            case JSON:{
                requestBody = new JsonRequestBody(contentLength);
            } break;
            case STRING:{

                requestBody = new StringRequestBody(contentLength);
            } break;
            default:
                throw new RuntimeException("No impl");
        }

        return requestBody;
    }
}
