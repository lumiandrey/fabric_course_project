package connection.server_support;

import connection.Headers;
import connection.server_support.request.RequestBody;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private final Headers headers;

    private final RequestBody body;

    Request(@NotNull final Builder builder) {
        this.headers = builder.headers;
        this.body = builder.body;

        this.headers.addMediaType(this.body.contentType());
    }

    public Headers getHeaders() {
        return headers;
    }

    public RequestBody getBody() {
        return body;
    }

    public static class Builder {

        private Headers headers = new Headers(new HashMap<>());
        private RequestBody body;


        public Builder body(RequestBody body) {
            this.body = body;

            return this;
        }
        /**
         * Adds a header with {@code name} and {@code value}. Prefer this method for multiply-valued
         * headers like "Cookie".
         *
         * <p>Note that for some headers including {@code Content-Length} and {@code Content-Encoding},
         * may replace {@code value} with a header derived from the request body.
         */
        public Builder addHeader(String name, String value) {
            headers.addHeader(name, value);

            return this;
        }

        Builder addHeaders(@NotNull Map<String, String> stringStringMap){


            for(Map.Entry<String, String> stringEntry: stringStringMap.entrySet()){

                addHeader(stringEntry.getKey(), stringEntry.getValue());
            }

            return this;
        }

        /** Removes all headers named {@code name} on this builder. */
        public Builder removeHeader(String name) {
            headers.removeHeader(name);
            return this;
        }

        public Request build(){
            if (body == null) throw new NullPointerException("body == null");

            return new Request(this);
        }


    }
}
