package connection;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

final public class ResponseHeaders extends Headers {

    private static final String NAME_KEY_CODE = ResponseHeaders.class.getName() + "_CODE";
    private static final String NAME_KEY_MESSAGE = ResponseHeaders.class.getName() + "_MESSAGE";

    public ResponseHeaders(Map<String, String> headers) {
        super(headers);
    }

    public ResponseHeaders setCode(@CodeResponse final int code){
        addHeader(NAME_KEY_CODE, String.valueOf(code));

        return this;
    }

    public ResponseHeaders setMessage(@NotNull String message){

        addHeader(NAME_KEY_MESSAGE, message);
        return this;
    }

    @NotNull
    public String getMessage(){
        String message = getHeader(NAME_KEY_MESSAGE);

        return message != null ? message : "No message";
    }

    @CodeResponse
    public int getCode(){

        String code = getHeader(NAME_KEY_CODE);

        return code != null ? Integer.valueOf(code): CodeConnection.NON_CODE;
    }
}
