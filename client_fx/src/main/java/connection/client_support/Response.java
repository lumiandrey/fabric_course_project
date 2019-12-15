package connection.client_support;

import org.jetbrains.annotations.NotNull;
import connection.CodeConnection;
import connection.CodeResponse;
import connection.ResponseHeaders;
import connection.client_support.responsebody.ResponseBody;

import java.util.HashMap;
import java.util.Map;

public final class Response {

    @CodeResponse
    private final int code;

    private final String message;

    private final ResponseHeaders headers;

    private final ResponseBody body;

    Response(@NotNull final Builder builder) {

        this.headers = builder.headers;
        this.body = builder.body;

        this.code = builder.code;
        this.message = builder.message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ResponseHeaders getHeaders() {
        return headers;
    }

    public ResponseBody getBody() {
        return body;
    }

    @Override
    public String toString() {
        return body.toString();
    }

    public boolean isSuccessfully(){

        return code > 199 && code < 300;
    }

    static class Builder {

        @CodeResponse
        int code = CodeConnection.NON_CODE;
        String message;
        private ResponseHeaders headers = new ResponseHeaders(new HashMap<>());
        private ResponseBody body;

        public Builder addCode(@CodeResponse int code) {
            this.code = code;
            return this;
        }

        public Builder addMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Adds a header with {@code name} and {@code value}. Prefer this method for multiply-valued
         * headers like "Cookie".
         *
         * <p>Note that for some headers including {@code Content-Length} and {@code Content-Encoding},
         * OkHttp may replace {@code value} with a header derived from the request body.
         */
        public Builder addHeader(@NotNull final String name, @NotNull final String value) {
            headers.addHeader(name, value);
            return this;
        }

        Builder addHeaders(@NotNull final Map<String, String> stringStringMap){


            for(Map.Entry<String, String> stringEntry: stringStringMap.entrySet()){

                addHeader(stringEntry.getKey(), stringEntry.getValue());
            }

            return this;
        }

        Builder addheaders(@NotNull final ResponseHeaders responseHeaders){

            headers = responseHeaders;

            return this;
        }

        /** Removes all headers named {@code name} on this builder. */
        public Builder removeHeader(String name) {
            headers.removeHeader(name);
            return this;
        }

        public Builder addBody(@NotNull final ResponseBody body) {
            this.body = body;

            return this;
        }

        public Response build() {
            if (code < 0) throw new IllegalStateException("code < 0: " + code);
            if (message == null) throw new IllegalStateException("message == null");

            return new Response(this);
        }

    }

}
