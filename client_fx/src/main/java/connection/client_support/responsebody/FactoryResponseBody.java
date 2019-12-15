package connection.client_support.responsebody;

import org.jetbrains.annotations.NotNull;
import connection.enumer.MediaTypeRequestResponce;

public class FactoryResponseBody {


    public static ResponseBody create(@NotNull final MediaTypeRequestResponce mediaType, final long contentLength){

        ResponseBody responseBody;

        switch (mediaType){
            case NO_CONTENT:{

                responseBody = new NoContentResponceBody(contentLength);
            } break;
            case FILE:{

                throw new RuntimeException("No impl");
            }
            case JSON:{

                responseBody = new JsonResponseBody(contentLength);
            } break;
            case STRING:{

                responseBody = new StringResponseBody(contentLength);
            }break;
            default:
                throw new RuntimeException("No impl");
        }

        return responseBody;
    }
}
