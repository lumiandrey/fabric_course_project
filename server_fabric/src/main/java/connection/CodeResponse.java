package connection;

import java.lang.annotation.Retention;

import static connection.CodeConnection.*;
import static java.lang.annotation.RetentionPolicy.CLASS;

@IntDef({NON_CODE, OK, BAD_REQUEST, FORBIDDEN, NOT_ACCEPTABLE,
        UNSUPPORTED_MEDIA_TYPE, INTERNAL_SERVER_ERROR, SERVICE_UNAVAILABLE})
@Retention(CLASS)
public @interface CodeResponse {

}
