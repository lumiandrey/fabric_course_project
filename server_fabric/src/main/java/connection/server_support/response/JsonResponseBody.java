package connection.server_support.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connection.enumer.MediaTypeRequest;
import org.jetbrains.annotations.NotNull;

public class JsonResponseBody extends StringResponseBody {

    private static Gson gson = new GsonBuilder().setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").create();
    
    public <T> JsonResponseBody(@NotNull final T toJsonObject) {
        super(gson.toJson(toJsonObject));
    }

    @Override
    public MediaTypeRequest contentType() {
        return MediaTypeRequest.JSON;
    }
}
