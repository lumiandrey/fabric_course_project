package connection.client_support;

import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.net.Socket;

final public class ConnectionClient {

    private InetAddress ipAddress;
    private int port;

    private Socket clientSocket;

    /**
     * Метод для выполнения запроса на сервер.
     * Вызов этого метода сихнронный и блокирует основной поток
     * @param request объект запроса с аргументами
     * @return Объект обёртка для совершения запроса.
     */
    public Call execute(@NotNull final Request request){

        return new Call(request);
    }

    ConnectionClient(Builder builder) {

    }

    public static class Builder {

        public ConnectionClient build(){
            return new ConnectionClient(this);
        }
    }
}
