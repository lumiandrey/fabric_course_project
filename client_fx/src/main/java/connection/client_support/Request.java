package connection.client_support;

import org.jetbrains.annotations.NotNull;
import connection.Headers;
import connection.client_support.requestbody.RequestBody;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

public class Request {

    private InetAddress ipAddress;

    private int port;

    private final Headers headers;

    private final RequestBody body;

    Request(@NotNull final Builder builder) {
        this.headers = builder.headers;
        this.body = builder.body;

        this.ipAddress = builder.ipAddress;
        this.port = builder.port;

        this.headers.addMediaType(this.body.contentType());
        this.headers.addContentLength(this.body.contentLength());
    }

    public Headers getHeaders() {
        return headers;
    }

    public RequestBody getBody() {
        return body;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }

    public static class Builder {

        private InetAddress ipAddress;
        private int port;

        private Headers headers = new Headers(new HashMap<>());
        private RequestBody body;

        public Builder url(final String url, final int port) throws UnknownHostException {
            if (url == null) throw new NullPointerException("url == null");

            ipAddress = InetAddress.getByName(url);
            this.port = port;

            return this;
        }

        public Builder body(RequestBody body) {
            this.body = body;

            return this;
        }

        public Builder addMethod(@NotNull final String method){

            headers.addMethod(method);

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

        /** Removes all headers named {@code name} on this builder. */
        public Builder removeHeader(String name) {
            headers.removeHeader(name);
            return this;
        }

        public Request build(){
            if (ipAddress == null) throw new NullPointerException("url == null");
            if (body == null) throw new NullPointerException("body == null");

            return new Request(this);
        }


    }
}
