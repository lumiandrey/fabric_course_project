package connection.server_support;

import com.google.gson.Gson;
import connection.Headers;
import connection.server_support.request.FactoryRequestBody;
import connection.server_support.request.RequestBody;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ServerHelper {

    private final Socket clientSocket;
    final Gson gson = new Gson();

    private Request request;

    public ServerHelper(@NotNull final  Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public Request getRequest() throws IOException {

        if(request == null)
            request = readRequest();

        return request;
    }

    public Request readRequest() throws IOException {

        Request.Builder builder = new Request.Builder();

        //Фаза чтения ответа от сервера
        InputStream inputStream = clientSocket.getInputStream();
        //Чтение потока байт
        BufferedReader sin = new BufferedReader(new InputStreamReader(inputStream));

        String readLine = sin.readLine();

        Map<String, String> requestHeadersMap = gson.fromJson(readLine, Map.class);

        Headers headers = new Headers(requestHeadersMap);

        builder.addHeaders(requestHeadersMap);

        RequestBody requestBody = FactoryRequestBody.create(headers.getMediaType(), headers.getContentLength());

        builder.body(requestBody);
        requestBody.execute(sin);

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return builder.build();
    }

    public void responseSend(@NotNull Response response) throws IOException {

        //Фаза отправки на сервер
        OutputStream outputStream = clientSocket.getOutputStream();

        PrintStream sout = new PrintStream (new BufferedOutputStream(outputStream), true);//для записи потока байт

        String sent = gson.toJson(response.getHeaders().getHeaders());

        sout.println(sent);

        response.getBody().execute(sout);
    }

}
