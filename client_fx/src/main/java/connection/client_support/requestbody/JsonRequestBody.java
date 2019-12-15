package connection.client_support.requestbody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;
import connection.enumer.MediaTypeRequestResponce;

public class JsonRequestBody extends StringRequestBody {

    private static Gson gson = new GsonBuilder().setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz")
            .create();
    public <T> JsonRequestBody(@NotNull final T toJsonObject) {
        super(gson.toJson(toJsonObject));
    }

    @Override
    public MediaTypeRequestResponce contentType() {
        return MediaTypeRequestResponce.JSON;
    }
}
