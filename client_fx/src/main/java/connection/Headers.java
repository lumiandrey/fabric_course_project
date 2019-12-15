package connection;

import org.jetbrains.annotations.NotNull;
import connection.enumer.MediaTypeRequestResponce;

import java.util.Map;

public class Headers {

    public static final String KEY_CONTENT_LENGTH = "ARG_CONTENT_LENGTH";

    private static final String KEY_MEDIA_TYPE = "TYPE";
    private static final String KEY_METHOD = "ARG_HEADERS_METHOD";

    private final Map<String, String> headers;

    public Headers(Map<String, String> headers) {
        this.headers = headers;
    }


    public Map<String, String> getHeaders() {
        return headers;
    }

    public Headers addHeader(String name, String value) {
        headers.put(name, value);
        return this;
    }

    public String getHeader(@NotNull String nameHeader){
        return headers.get(nameHeader);
    }


    public Headers removeHeader(String name) {
        headers.remove(name);

        return this;
    }

    public Headers addMethod(@NotNull final String method){

        addHeader(KEY_METHOD, method);
        return this;
    }

    public Headers addContentLength(final long contentLenght){

        addHeader(KEY_CONTENT_LENGTH, String.valueOf(contentLenght));
        return this;
    }

    public long getContentLength(){

        String contentLength = getHeader(KEY_CONTENT_LENGTH);

        if(contentLength != null)
            return Long.valueOf(contentLength);
        else
            return 0;
    }

    public Headers addMediaType(@NotNull final MediaTypeRequestResponce mediaType){

        addHeader(KEY_MEDIA_TYPE, mediaType.name());
        return this;
    }

    public MediaTypeRequestResponce getMediaType(){

        String media = getHeader(KEY_MEDIA_TYPE);

        MediaTypeRequestResponce mediaType = MediaTypeRequestResponce.valueOf(media);

        return mediaType != null ? mediaType : MediaTypeRequestResponce.NO_CONTENT;
    }

    public String getMethod() {

        return getHeader(KEY_METHOD);
    }
}
