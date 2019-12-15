package connection.client_support;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import connection.ResponseHeaders;
import connection.client_support.responsebody.FactoryResponseBody;
import connection.client_support.responsebody.ResponseBody;

import java.io.*;
import java.net.Socket;
import java.util.Map;

/**
 * Класс для совершения заросов к серверу со стороны клиента
 */
final public class Call {

    private final Request request;
    private final Gson gson;

    private Socket clientSocket;



    Call(@NotNull final Request request) {

        this.request = request;
        gson = new Gson();
    }

    public Response execute() throws IOException {

        clientSocket = new Socket(request.getIpAddress(), request.getPort());

        //Фаза отправки на сервер
        OutputStream outputStream = clientSocket.getOutputStream();
        //для записи потока байт
        PrintStream sout = new PrintStream (new BufferedOutputStream(outputStream), true);

        String sent = gson.toJson(request.getHeaders().getHeaders());

        sout.println(sent);

        request.getBody().execute(sout);

        //Фаза чтения ответа от сервера
        InputStream inputStream = clientSocket.getInputStream();
        //Чтение потока байт
        BufferedReader sin = new BufferedReader(new InputStreamReader(inputStream));

        String headers = sin.readLine();

        Map<String, String> responseHeadersMap = gson.fromJson(headers, Map.class);

        Response.Builder responseBuilder = new Response.Builder();

        responseBuilder.addHeaders(responseHeadersMap);

        ResponseHeaders responseHeaders= new ResponseHeaders(responseHeadersMap);

        ResponseBody responseBody = FactoryResponseBody.create(responseHeaders.getMediaType(), responseHeaders.getContentLength());

        responseBuilder.addCode(responseHeaders.getCode());
        responseBuilder.addMessage(responseHeaders.getMessage());

        responseBody.execute(sin);
        responseBuilder.addBody(responseBody);

        clientSocket.close();

        return responseBuilder.build();
    }

}
