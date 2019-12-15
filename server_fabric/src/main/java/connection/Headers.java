package connection;

import connection.enumer.MediaTypeRequest;
import org.jetbrains.annotations.NotNull;

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

    public Headers addMediaType(@NotNull final MediaTypeRequest mediaTypeRequest){

        addHeader(KEY_MEDIA_TYPE, mediaTypeRequest.name());
        return this;
    }

    public MediaTypeRequest getMediaType(){

        String media = getHeader(KEY_MEDIA_TYPE);

        MediaTypeRequest mediaTypeRequest = MediaTypeRequest.valueOf(media);

        return mediaTypeRequest != null ? mediaTypeRequest : MediaTypeRequest.NO_CONTENT;
    }

    public String getMethod() {

        return getHeader(KEY_METHOD);
    }
}
