package connection;

public class CodeConnection {

    /**
     * 1xx Informational
     */
    public static final int NON_CODE = 100;
    /**
     * Код ответа, который прошёл успешно.
     */
    public static final int OK = 200;
    /**
     * 4xx Client Error.
     */
    public static final int BAD_REQUEST = 400;
    public static final int FORBIDDEN = 403;
    public static final int NOT_ACCEPTABLE = 406;
    public static final int UNSUPPORTED_MEDIA_TYPE = 415;
    /**
     * 5xx Server Error.
     */
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int SERVICE_UNAVAILABLE = 503;
}
